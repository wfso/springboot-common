package com.yioks.springboot.common.config.restTemplateHttpClientConfig;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;

@Configuration
public class RestTemplateHttpClientConfig {

  private static final Logger logger = LoggerFactory.getLogger(RestTemplateHttpClientConfig.class);

  private RestTemplate buildRestTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
    RestTemplate result = new RestTemplate(httpComponentsClientHttpRequestFactory);
    int index = -1;
    for (int i = 0; i < result.getMessageConverters().size(); i++) {
      HttpMessageConverter converter = result.getMessageConverters().get(i);
      if (converter instanceof StringHttpMessageConverter) {
        index = i;
        break;
      }
    }
    //  处理默认字符集导致中文乱码的问题
    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    if (index > 0) {
      result.getMessageConverters().set(index, stringHttpMessageConverter);
    } else {
      result.getMessageConverters().add(stringHttpMessageConverter);
    }
    return result;
  }

  /**
   * 创建支持 HTTPS 的 HTTP Client
   * 参考： http://blog.csdn.net/ychau/article/details/53905886
   *
   * @return CloseableHttpClient
   */
  @Bean("closeableHttpClient")
  public CloseableHttpClient closeableHttpClient() {
    CloseableHttpClient client = null;
    try {
      HttpClientBuilder b = HttpClientBuilder.create();

      // setup a Trust Strategy that allows all certificates.
      SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
      b.setSSLContext(sslContext);

      // don't check Hostnames, either.
      //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
      HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

      // here's the special part:
      //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
      //      -- and create a Registry, to register it.
      SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
      Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
        .register("http", PlainConnectionSocketFactory.getSocketFactory())
        .register("https", sslSocketFactory)
        .build();

      // now, we create connection-manager using our Registry.
      //      -- allows multi-threaded use
      PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
      connMgr.setMaxTotal(200);
      connMgr.setDefaultMaxPerRoute(100);
      b.setConnectionManager(connMgr);

      // finally, build the HttpClient;
      //      -- done!
      client = b.build();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return client;
  }

  @Bean("httpComponentsClientHttpRequestFactory")
  public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(@Autowired @Qualifier("closeableHttpClient") CloseableHttpClient closeableHttpClient) {
    return new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
  }


  @Bean("restTemplate")
  public RestTemplate restTemplate(@Autowired @Qualifier("httpComponentsClientHttpRequestFactory") HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
    return buildRestTemplate(httpComponentsClientHttpRequestFactory);
  }

}
