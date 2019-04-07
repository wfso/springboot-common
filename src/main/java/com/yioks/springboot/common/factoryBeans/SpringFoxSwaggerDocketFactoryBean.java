package com.yioks.springboot.common.factoryBeans;

import org.springframework.beans.factory.FactoryBean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

public class SpringFoxSwaggerDocketFactoryBean implements FactoryBean<Docket> {

  private String basePackage;
  private String groupName;
  private Map<String, String> httpHeaderParams;
  private Map<String, String> httpBodyParams;
  private Map<String, String> httpQueryParams;
  private Map<String, String> httpCookieParams;

  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName) {
    this.basePackage = basePackage;
    this.groupName = groupName;
  }

  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName, Map<String, String> httpHeaderParams) {
    this(basePackage, groupName);
    this.httpHeaderParams = httpHeaderParams;
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams
    , Map<String, String> httpBodyParams) {
    this(basePackage, groupName, httpHeaderParams);
    this.httpBodyParams = httpBodyParams;
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams
    , Map<String, String> httpBodyParams
    , Map<String, String> httpQueryParams) {
    this(basePackage, groupName, httpHeaderParams, httpBodyParams);
    this.httpQueryParams = httpQueryParams;
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams
    , Map<String, String> httpBodyParams
    , Map<String, String> httpQueryParams
    , Map<String, String> httpCookieParams) {
    this(basePackage, groupName, httpHeaderParams, httpBodyParams, httpQueryParams);
    this.httpCookieParams = httpCookieParams;
  }

  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName, String... params) {
    this(basePackage, groupName);
    this.httpHeaderParams = new TreeMap<>();
    for (int i = 0; i < params.length; i++) {
      this.httpHeaderParams.put(params[i], params[i]);
    }
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams, String... params) {
    this(basePackage, groupName, httpHeaderParams);
    this.httpBodyParams = new TreeMap<>();
    for (int i = 0; i < params.length; i++) {
      this.httpBodyParams.put(params[i], params[i]);
    }
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams
    , Map<String, String> httpBodyParams, String... params) {
    this(basePackage, groupName, httpHeaderParams, httpBodyParams);
    this.httpQueryParams = new TreeMap<>();
    for (int i = 0; i < params.length; i++) {
      this.httpQueryParams.put(params[i], params[i]);
    }
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , Map<String, String> httpHeaderParams
    , Map<String, String> httpBodyParams
    , Map<String, String> httpQueryParams, String... params) {
    this(basePackage, groupName, httpHeaderParams, httpBodyParams, httpQueryParams);
    this.httpCookieParams = new TreeMap<>();
    for (int i = 0; i < params.length; i++) {
      this.httpCookieParams.put(params[i], params[i]);
    }
  }


  public SpringFoxSwaggerDocketFactoryBean(String basePackage, String groupName
    , List<String> headerParams
    , List<String> bodyParams
    , List<String> queryParams
    , List<String> cookieParams) {

    if (headerParams != null) {
      this.httpHeaderParams = new TreeMap<>();
      headerParams.forEach(str -> {
        this.httpHeaderParams.put(str, str);
      });
    }

    if (bodyParams != null) {
      this.httpBodyParams = new TreeMap<>();
      bodyParams.forEach(str -> {
        this.httpBodyParams.put(str, str);
      });
    }

    if (queryParams != null) {
      this.httpQueryParams = new TreeMap<>();
      queryParams.forEach(str -> {
        this.httpQueryParams.put(str, str);
      });
    }

    if (cookieParams != null) {
      this.httpCookieParams = new TreeMap<>();
      cookieParams.forEach(str -> {
        this.httpCookieParams.put(str, str);
      });
    }
    
  }

  @Override
  public Docket getObject() throws Exception {
    //  参照 http://blog.csdn.net/u014044812/article/details/71473226 增加 Token 参数
    List<Parameter> pars = new ArrayList<>();
    if (httpHeaderParams != null) {
      httpHeaderParams.keySet().forEach(str -> {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(str)
          .description(httpHeaderParams.get(str))
          .modelRef(new ModelRef("string"))
          .parameterType("header")
          .required(false)
          .build();
        pars.add(tokenPar.build());
      });
    }


    if (httpBodyParams != null) {
      httpBodyParams.keySet().forEach(str -> {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(str)
          .description(httpBodyParams.get(str))
          .modelRef(new ModelRef("string"))
          .parameterType("body")
          .required(false)
          .build();
        pars.add(tokenPar.build());
      });
    }


    if (httpQueryParams != null) {
      httpQueryParams.keySet().forEach(str -> {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(str)
          .description(httpQueryParams.get(str))
          .modelRef(new ModelRef("string"))
          .parameterType("query")
          .required(false)
          .build();
        pars.add(tokenPar.build());
      });
    }


    if (httpCookieParams != null) {
      httpCookieParams.keySet().forEach(str -> {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(str)
          .description(httpCookieParams.get(str))
          .modelRef(new ModelRef("string"))
          .parameterType("cookie")
          .required(false)
          .build();
        pars.add(tokenPar.build());
      });
    }


    return new Docket(DocumentationType.SWAGGER_2)
      .groupName(groupName)
      .apiInfo(apiInfo())
      .select()
      .apis(basePackage(this.basePackage))
      .paths(PathSelectors.any())
      .build()
      .globalOperationParameters(pars);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("API")
      .description("接口")
      .version("1.0")
      .build();
  }


  @Override
  public Class<?> getObjectType() {
    return Docket.class;
  }
}
