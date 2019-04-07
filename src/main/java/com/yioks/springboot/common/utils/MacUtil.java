package com.yioks.springboot.common.utils;

import org.apache.shiro.codec.CodecSupport;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;

public class MacUtil extends CodecSupport {

  // region hmac
  private static byte[] hmac(byte[] data, byte[] key, String algorithm) {
    try {
      Key keys = new SecretKeySpec(key, algorithm);
      Mac mac = Mac.getInstance(algorithm);
      mac.init(keys);
      return mac.doFinal(data);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
      return new byte[0];
    }
  }


  // endregion



  // region hmacMd5


  public static byte[] hmacMd5(byte[] data, byte[] key) {
    return hmac(data, key, "hmacMd5");
  }

  public static byte[] hmacMd5(char[] data, char[] key) {
    return hmacMd5(toBytes(data), toBytes(key));
  }

  public static byte[] hmacMd5(char[] data, byte[] key) {
    return hmacMd5(toBytes(data), key);
  }


  public static byte[] hmacMd5(char[] data, char[] key, String encoding) {
    return hmacMd5(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacMd5(char[] data, String encoding, byte[] key) {
    return hmacMd5(toBytes(data, encoding), key);
  }


  public static byte[] hmacMd5(String data, String key) {
    return hmacMd5(toBytes(data), toBytes(key));
  }

  public static byte[] hmacMd5(String data, byte[] key) {
    return hmacMd5(toBytes(data), key);
  }

  public static byte[] hmacMd5(String data, String key, String encoding) {
    return hmacMd5(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacMd5(String data, String encoding, byte[] key) {
    return hmacMd5(toBytes(data, encoding), key);
  }


  public byte[] hmacMd5(InputStream data, InputStream key) {
    return hmacMd5(toBytes(data), toBytes(key));
  }


  public byte[] hmacMd5(InputStream data, byte[] key) {
    return hmacMd5(toBytes(data), key);
  }

  public byte[] hmacMd5(File data, File key) {
    return hmacMd5(toBytes(data), toBytes(key));
  }

  public byte[] hmacMd5(File data, byte[] key) {
    return hmacMd5(toBytes(data), key);
  }

  // endregion


  // region hmacMd5Hex


  public static String hmacMd5Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacMd5(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacMd5Hex(char[] data, char[] key) {
    return hmacMd5Hex(toBytes(data), toBytes(key));
  }

  public static String hmacMd5Hex(char[] data, byte[] key) {
    return hmacMd5Hex(toBytes(data), key);
  }


  public static String hmacMd5Hex(char[] data, char[] key, String encoding) {
    return hmacMd5Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacMd5Hex(char[] data, String encoding, byte[] key) {
    return hmacMd5Hex(toBytes(data, encoding), key);
  }


  public static String hmacMd5Hex(String data, String key) {
    return hmacMd5Hex(toBytes(data), toBytes(key));
  }

  public static String hmacMd5Hex(String data, byte[] key) {
    return hmacMd5Hex(toBytes(data), key);
  }

  public static String hmacMd5Hex(String data, String key, String encoding) {
    return hmacMd5Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacMd5Hex(String data, String encoding, byte[] key) {
    return hmacMd5Hex(toBytes(data, encoding), key);
  }


  public String hmacMd5Hex(InputStream data, InputStream key) {
    return hmacMd5Hex(toBytes(data), toBytes(key));
  }


  public String hmacMd5Hex(InputStream data, byte[] key) {
    return hmacMd5Hex(toBytes(data), key);
  }

  public String hmacMd5Hex(File data, File key) {
    return hmacMd5Hex(toBytes(data), toBytes(key));
  }

  public String hmacMd5Hex(File data, byte[] key) {
    return hmacMd5Hex(toBytes(data), key);
  }

  // endregion


  // region hmacMd5B64


  public static String hmacMd5B64(byte[] data, byte[] key) {
    byte[] bytes = hmacMd5(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacMd5B64(char[] data, char[] key) {
    return hmacMd5B64(toBytes(data), toBytes(key));
  }

  public static String hmacMd5B64(char[] data, byte[] key) {
    return hmacMd5B64(toBytes(data), key);
  }


  public static String hmacMd5B64(char[] data, char[] key, String encoding) {
    return hmacMd5B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacMd5B64(char[] data, String encoding, byte[] key) {
    return hmacMd5B64(toBytes(data, encoding), key);
  }


  public static String hmacMd5B64(String data, String key) {
    return hmacMd5B64(toBytes(data), toBytes(key));
  }

  public static String hmacMd5B64(String data, byte[] key) {
    return hmacMd5B64(toBytes(data), key);
  }

  public static String hmacMd5B64(String data, String key, String encoding) {
    return hmacMd5B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacMd5B64(String data, String encoding, byte[] key) {
    return hmacMd5B64(toBytes(data, encoding), key);
  }


  public String hmacMd5B64(InputStream data, InputStream key) {
    return hmacMd5B64(toBytes(data), toBytes(key));
  }


  public String hmacMd5B64(InputStream data, byte[] key) {
    return hmacMd5B64(toBytes(data), key);
  }

  public String hmacMd5B64(File data, File key) {
    return hmacMd5B64(toBytes(data), toBytes(key));
  }

  public String hmacMd5B64(File data, byte[] key) {
    return hmacMd5B64(toBytes(data), key);
  }


  // endregion


  // region hmacSha1

  public static byte[] hmacSha1(byte[] data, byte[] key) {
    return hmac(data, key, "hmacSha1");
  }

  public static byte[] hmacSha1(char[] data, char[] key) {
    return hmacSha1(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha1(char[] data, byte[] key) {
    return hmacSha1(toBytes(data), key);
  }


  public static byte[] hmacSha1(char[] data, char[] key, String encoding) {
    return hmacSha1(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacSha1(char[] data, String encoding, byte[] key) {
    return hmacSha1(toBytes(data, encoding), key);
  }


  public static byte[] hmacSha1(String data, String key) {
    return hmacSha1(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha1(String data, byte[] key) {
    return hmacSha1(toBytes(data), key);
  }

  public static byte[] hmacSha1(String data, String key, String encoding) {
    return hmacSha1(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacSha1(String data, String encoding, byte[] key) {
    return hmacSha1(toBytes(data, encoding), key);
  }


  public byte[] hmacSha1(InputStream data, InputStream key) {
    return hmacSha1(toBytes(data), toBytes(key));
  }


  public byte[] hmacSha1(InputStream data, byte[] key) {
    return hmacSha1(toBytes(data), key);
  }

  public byte[] hmacSha1(File data, File key) {
    return hmacSha1(toBytes(data), toBytes(key));
  }

  public byte[] hmacSha1(File data, byte[] key) {
    return hmacSha1(toBytes(data), key);
  }

  // endregion


  // region hmacSha1Hex



  public static String hmacSha1Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacSha1(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacSha1Hex(char[] data, char[] key) {
    return hmacSha1Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha1Hex(char[] data, byte[] key) {
    return hmacSha1Hex(toBytes(data), key);
  }


  public static String hmacSha1Hex(char[] data, char[] key, String encoding) {
    return hmacSha1Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha1Hex(char[] data, String encoding, byte[] key) {
    return hmacSha1Hex(toBytes(data, encoding), key);
  }


  public static String hmacSha1Hex(String data, String key) {
    return hmacSha1Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha1Hex(String data, byte[] key) {
    return hmacSha1Hex(toBytes(data), key);
  }

  public static String hmacSha1Hex(String data, String key, String encoding) {
    return hmacSha1Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha1Hex(String data, String encoding, byte[] key) {
    return hmacSha1Hex(toBytes(data, encoding), key);
  }


  public String hmacSha1Hex(InputStream data, InputStream key) {
    return hmacSha1Hex(toBytes(data), toBytes(key));
  }


  public String hmacSha1Hex(InputStream data, byte[] key) {
    return hmacSha1Hex(toBytes(data), key);
  }

  public String hmacSha1Hex(File data, File key) {
    return hmacSha1Hex(toBytes(data), toBytes(key));
  }

  public String hmacSha1Hex(File data, byte[] key) {
    return hmacSha1Hex(toBytes(data), key);
  }


  // endregion


  // region hmacSha1B64


  public static String hmacSha1B64(byte[] data, byte[] key) {
    byte[] bytes = hmacSha1(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacSha1B64(char[] data, char[] key) {
    return hmacSha1B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha1B64(char[] data, byte[] key) {
    return hmacSha1B64(toBytes(data), key);
  }


  public static String hmacSha1B64(char[] data, char[] key, String encoding) {
    return hmacSha1B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha1B64(char[] data, String encoding, byte[] key) {
    return hmacSha1B64(toBytes(data, encoding), key);
  }


  public static String hmacSha1B64(String data, String key) {
    return hmacSha1B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha1B64(String data, byte[] key) {
    return hmacSha1B64(toBytes(data), key);
  }

  public static String hmacSha1B64(String data, String key, String encoding) {
    return hmacSha1B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha1B64(String data, String encoding, byte[] key) {
    return hmacSha1B64(toBytes(data, encoding), key);
  }


  public String hmacSha1B64(InputStream data, InputStream key) {
    return hmacSha1B64(toBytes(data), toBytes(key));
  }


  public String hmacSha1B64(InputStream data, byte[] key) {
    return hmacSha1B64(toBytes(data), key);
  }

  public String hmacSha1B64(File data, File key) {
    return hmacSha1B64(toBytes(data), toBytes(key));
  }

  public String hmacSha1B64(File data, byte[] key) {
    return hmacSha1B64(toBytes(data), key);
  }

  // endregion


  // region hmacSha224


  public static byte[] hmacSha224(byte[] data, byte[] key) {
    return hmac(data, key, "hmacSha224");
  }

  public static byte[] hmacSha224(char[] data, char[] key) {
    return hmacSha224(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha224(char[] data, byte[] key) {
    return hmacSha224(toBytes(data), key);
  }


  public static byte[] hmacSha224(char[] data, char[] key, String encoding) {
    return hmacSha224(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacSha224(char[] data, String encoding, byte[] key) {
    return hmacSha224(toBytes(data, encoding), key);
  }


  public static byte[] hmacSha224(String data, String key) {
    return hmacSha224(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha224(String data, byte[] key) {
    return hmacSha224(toBytes(data), key);
  }

  public static byte[] hmacSha224(String data, String key, String encoding) {
    return hmacSha224(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacSha224(String data, String encoding, byte[] key) {
    return hmacSha224(toBytes(data, encoding), key);
  }


  public byte[] hmacSha224(InputStream data, InputStream key) {
    return hmacSha224(toBytes(data), toBytes(key));
  }


  public byte[] hmacSha224(InputStream data, byte[] key) {
    return hmacSha224(toBytes(data), key);
  }

  public byte[] hmacSha224(File data, File key) {
    return hmacSha224(toBytes(data), toBytes(key));
  }

  public byte[] hmacSha224(File data, byte[] key) {
    return hmacSha224(toBytes(data), key);
  }

  // endregion


  // region hmacSha224Hex


  public static String hmacSha224Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacSha224(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacSha224Hex(char[] data, char[] key) {
    return hmacSha224Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha224Hex(char[] data, byte[] key) {
    return hmacSha224Hex(toBytes(data), key);
  }


  public static String hmacSha224Hex(char[] data, char[] key, String encoding) {
    return hmacSha224Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha224Hex(char[] data, String encoding, byte[] key) {
    return hmacSha224Hex(toBytes(data, encoding), key);
  }


  public static String hmacSha224Hex(String data, String key) {
    return hmacSha224Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha224Hex(String data, byte[] key) {
    return hmacSha224Hex(toBytes(data), key);
  }

  public static String hmacSha224Hex(String data, String key, String encoding) {
    return hmacSha224Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha224Hex(String data, String encoding, byte[] key) {
    return hmacSha224Hex(toBytes(data, encoding), key);
  }


  public String hmacSha224Hex(InputStream data, InputStream key) {
    return hmacSha224Hex(toBytes(data), toBytes(key));
  }


  public String hmacSha224Hex(InputStream data, byte[] key) {
    return hmacSha224Hex(toBytes(data), key);
  }

  public String hmacSha224Hex(File data, File key) {
    return hmacSha224Hex(toBytes(data), toBytes(key));
  }

  public String hmacSha224Hex(File data, byte[] key) {
    return hmacSha224Hex(toBytes(data), key);
  }

  // endregion


  // region hmacSha224B64


  public static String hmacSha224B64(byte[] data, byte[] key) {
    byte[] bytes = hmacSha224(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacSha224B64(char[] data, char[] key) {
    return hmacSha224B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha224B64(char[] data, byte[] key) {
    return hmacSha224B64(toBytes(data), key);
  }


  public static String hmacSha224B64(char[] data, char[] key, String encoding) {
    return hmacSha224B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha224B64(char[] data, String encoding, byte[] key) {
    return hmacSha224B64(toBytes(data, encoding), key);
  }


  public static String hmacSha224B64(String data, String key) {
    return hmacSha224B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha224B64(String data, byte[] key) {
    return hmacSha224B64(toBytes(data), key);
  }

  public static String hmacSha224B64(String data, String key, String encoding) {
    return hmacSha224B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha224B64(String data, String encoding, byte[] key) {
    return hmacSha224B64(toBytes(data, encoding), key);
  }


  public String hmacSha224B64(InputStream data, InputStream key) {
    return hmacSha224B64(toBytes(data), toBytes(key));
  }


  public String hmacSha224B64(InputStream data, byte[] key) {
    return hmacSha224B64(toBytes(data), key);
  }

  public String hmacSha224B64(File data, File key) {
    return hmacSha224B64(toBytes(data), toBytes(key));
  }

  public String hmacSha224B64(File data, byte[] key) {
    return hmacSha224B64(toBytes(data), key);
  }


  // endregion


  // region hmacSha256


  public static byte[] hmacSha256(byte[] data, byte[] key) {
    return hmac(data, key, "hmacSha256");
  }

  public static byte[] hmacSha256(char[] data, char[] key) {
    return hmacSha256(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha256(char[] data, byte[] key) {
    return hmacSha256(toBytes(data), key);
  }


  public static byte[] hmacSha256(char[] data, char[] key, String encoding) {
    return hmacSha256(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacSha256(char[] data, String encoding, byte[] key) {
    return hmacSha256(toBytes(data, encoding), key);
  }


  public static byte[] hmacSha256(String data, String key) {
    return hmacSha256(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha256(String data, byte[] key) {
    return hmacSha256(toBytes(data), key);
  }

  public static byte[] hmacSha256(String data, String key, String encoding) {
    return hmacSha256(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacSha256(String data, String encoding, byte[] key) {
    return hmacSha256(toBytes(data, encoding), key);
  }


  public byte[] hmacSha256(InputStream data, InputStream key) {
    return hmacSha256(toBytes(data), toBytes(key));
  }


  public byte[] hmacSha256(InputStream data, byte[] key) {
    return hmacSha256(toBytes(data), key);
  }

  public byte[] hmacSha256(File data, File key) {
    return hmacSha256(toBytes(data), toBytes(key));
  }

  public byte[] hmacSha256(File data, byte[] key) {
    return hmacSha256(toBytes(data), key);
  }

  // endregion


  // region hmacSha256Hex


  public static String hmacSha256Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacSha256(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacSha256Hex(char[] data, char[] key) {
    return hmacSha256Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha256Hex(char[] data, byte[] key) {
    return hmacSha256Hex(toBytes(data), key);
  }


  public static String hmacSha256Hex(char[] data, char[] key, String encoding) {
    return hmacSha256Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha256Hex(char[] data, String encoding, byte[] key) {
    return hmacSha256Hex(toBytes(data, encoding), key);
  }


  public static String hmacSha256Hex(String data, String key) {
    return hmacSha256Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha256Hex(String data, byte[] key) {
    return hmacSha256Hex(toBytes(data), key);
  }

  public static String hmacSha256Hex(String data, String key, String encoding) {
    return hmacSha256Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha256Hex(String data, String encoding, byte[] key) {
    return hmacSha256Hex(toBytes(data, encoding), key);
  }


  public String hmacSha256Hex(InputStream data, InputStream key) {
    return hmacSha256Hex(toBytes(data), toBytes(key));
  }


  public String hmacSha256Hex(InputStream data, byte[] key) {
    return hmacSha256Hex(toBytes(data), key);
  }

  public String hmacSha256Hex(File data, File key) {
    return hmacSha256Hex(toBytes(data), toBytes(key));
  }

  public String hmacSha256Hex(File data, byte[] key) {
    return hmacSha256Hex(toBytes(data), key);
  }

  // endregion


  // region hmacSha256B64


  public static String hmacSha256B64(byte[] data, byte[] key) {
    byte[] bytes = hmacSha256(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacSha256B64(char[] data, char[] key) {
    return hmacSha256B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha256B64(char[] data, byte[] key) {
    return hmacSha256B64(toBytes(data), key);
  }


  public static String hmacSha256B64(char[] data, char[] key, String encoding) {
    return hmacSha256B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha256B64(char[] data, String encoding, byte[] key) {
    return hmacSha256B64(toBytes(data, encoding), key);
  }


  public static String hmacSha256B64(String data, String key) {
    return hmacSha256B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha256B64(String data, byte[] key) {
    return hmacSha256B64(toBytes(data), key);
  }

  public static String hmacSha256B64(String data, String key, String encoding) {
    return hmacSha256B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha256B64(String data, String encoding, byte[] key) {
    return hmacSha256B64(toBytes(data, encoding), key);
  }


  public String hmacSha256B64(InputStream data, InputStream key) {
    return hmacSha256B64(toBytes(data), toBytes(key));
  }


  public String hmacSha256B64(InputStream data, byte[] key) {
    return hmacSha256B64(toBytes(data), key);
  }

  public String hmacSha256B64(File data, File key) {
    return hmacSha256B64(toBytes(data), toBytes(key));
  }

  public String hmacSha256B64(File data, byte[] key) {
    return hmacSha256B64(toBytes(data), key);
  }


  // endregion


  // region hmacSha384


  public static byte[] hmacSha384(byte[] data, byte[] key) {
    return hmac(data, key, "hmacSha384");
  }

  public static byte[] hmacSha384(char[] data, char[] key) {
    return hmacSha384(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha384(char[] data, byte[] key) {
    return hmacSha384(toBytes(data), key);
  }


  public static byte[] hmacSha384(char[] data, char[] key, String encoding) {
    return hmacSha384(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacSha384(char[] data, String encoding, byte[] key) {
    return hmacSha384(toBytes(data, encoding), key);
  }


  public static byte[] hmacSha384(String data, String key) {
    return hmacSha384(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha384(String data, byte[] key) {
    return hmacSha384(toBytes(data), key);
  }

  public static byte[] hmacSha384(String data, String key, String encoding) {
    return hmacSha384(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacSha384(String data, String encoding, byte[] key) {
    return hmacSha384(toBytes(data, encoding), key);
  }


  public byte[] hmacSha384(InputStream data, InputStream key) {
    return hmacSha384(toBytes(data), toBytes(key));
  }


  public byte[] hmacSha384(InputStream data, byte[] key) {
    return hmacSha384(toBytes(data), key);
  }

  public byte[] hmacSha384(File data, File key) {
    return hmacSha384(toBytes(data), toBytes(key));
  }

  public byte[] hmacSha384(File data, byte[] key) {
    return hmacSha384(toBytes(data), key);
  }

  // endregion


  // region hmacSha384Hex


  public static String hmacSha384Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacSha384(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacSha384Hex(char[] data, char[] key) {
    return hmacSha384Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha384Hex(char[] data, byte[] key) {
    return hmacSha384Hex(toBytes(data), key);
  }


  public static String hmacSha384Hex(char[] data, char[] key, String encoding) {
    return hmacSha384Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha384Hex(char[] data, String encoding, byte[] key) {
    return hmacSha384Hex(toBytes(data, encoding), key);
  }


  public static String hmacSha384Hex(String data, String key) {
    return hmacSha384Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha384Hex(String data, byte[] key) {
    return hmacSha384Hex(toBytes(data), key);
  }

  public static String hmacSha384Hex(String data, String key, String encoding) {
    return hmacSha384Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha384Hex(String data, String encoding, byte[] key) {
    return hmacSha384Hex(toBytes(data, encoding), key);
  }


  public String hmacSha384Hex(InputStream data, InputStream key) {
    return hmacSha384Hex(toBytes(data), toBytes(key));
  }


  public String hmacSha384Hex(InputStream data, byte[] key) {
    return hmacSha384Hex(toBytes(data), key);
  }

  public String hmacSha384Hex(File data, File key) {
    return hmacSha384Hex(toBytes(data), toBytes(key));
  }

  public String hmacSha384Hex(File data, byte[] key) {
    return hmacSha384Hex(toBytes(data), key);
  }

  // endregion


  // region hmacSha384B64


  public static String hmacSha384B64(byte[] data, byte[] key) {
    byte[] bytes = hmacSha384(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacSha384B64(char[] data, char[] key) {
    return hmacSha384B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha384B64(char[] data, byte[] key) {
    return hmacSha384B64(toBytes(data), key);
  }


  public static String hmacSha384B64(char[] data, char[] key, String encoding) {
    return hmacSha384B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha384B64(char[] data, String encoding, byte[] key) {
    return hmacSha384B64(toBytes(data, encoding), key);
  }


  public static String hmacSha384B64(String data, String key) {
    return hmacSha384B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha384B64(String data, byte[] key) {
    return hmacSha384B64(toBytes(data), key);
  }

  public static String hmacSha384B64(String data, String key, String encoding) {
    return hmacSha384B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha384B64(String data, String encoding, byte[] key) {
    return hmacSha384B64(toBytes(data, encoding), key);
  }


  public String hmacSha384B64(InputStream data, InputStream key) {
    return hmacSha384B64(toBytes(data), toBytes(key));
  }


  public String hmacSha384B64(InputStream data, byte[] key) {
    return hmacSha384B64(toBytes(data), key);
  }

  public String hmacSha384B64(File data, File key) {
    return hmacSha384B64(toBytes(data), toBytes(key));
  }

  public String hmacSha384B64(File data, byte[] key) {
    return hmacSha384B64(toBytes(data), key);
  }


  // endregion



  // region hmacSha512


  public static byte[] hmacSha512(byte[] data, byte[] key) {
    return hmac(data, key, "hmacSha512");
  }

  public static byte[] hmacSha512(char[] data, char[] key) {
    return hmacSha512(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha512(char[] data, byte[] key) {
    return hmacSha512(toBytes(data), key);
  }


  public static byte[] hmacSha512(char[] data, char[] key, String encoding) {
    return hmacSha512(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static byte[] hmacSha512(char[] data, String encoding, byte[] key) {
    return hmacSha512(toBytes(data, encoding), key);
  }


  public static byte[] hmacSha512(String data, String key) {
    return hmacSha512(toBytes(data), toBytes(key));
  }

  public static byte[] hmacSha512(String data, byte[] key) {
    return hmacSha512(toBytes(data), key);
  }

  public static byte[] hmacSha512(String data, String key, String encoding) {
    return hmacSha512(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static byte[] hmacSha512(String data, String encoding, byte[] key) {
    return hmacSha512(toBytes(data, encoding), key);
  }


  public byte[] hmacSha512(InputStream data, InputStream key) {
    return hmacSha512(toBytes(data), toBytes(key));
  }


  public byte[] hmacSha512(InputStream data, byte[] key) {
    return hmacSha512(toBytes(data), key);
  }

  public byte[] hmacSha512(File data, File key) {
    return hmacSha512(toBytes(data), toBytes(key));
  }

  public byte[] hmacSha512(File data, byte[] key) {
    return hmacSha512(toBytes(data), key);
  }

  // endregion


  // region hmacSha512Hex


  public static String hmacSha512Hex(byte[] data, byte[] key) {
    byte[] bytes = hmacSha512(data, key);
    return DatatypeConverter.printHexBinary(bytes);
  }

  public static String hmacSha512Hex(char[] data, char[] key) {
    return hmacSha512Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha512Hex(char[] data, byte[] key) {
    return hmacSha512Hex(toBytes(data), key);
  }


  public static String hmacSha512Hex(char[] data, char[] key, String encoding) {
    return hmacSha512Hex(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha512Hex(char[] data, String encoding, byte[] key) {
    return hmacSha512Hex(toBytes(data, encoding), key);
  }


  public static String hmacSha512Hex(String data, String key) {
    return hmacSha512Hex(toBytes(data), toBytes(key));
  }

  public static String hmacSha512Hex(String data, byte[] key) {
    return hmacSha512Hex(toBytes(data), key);
  }

  public static String hmacSha512Hex(String data, String key, String encoding) {
    return hmacSha512Hex(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha512Hex(String data, String encoding, byte[] key) {
    return hmacSha512Hex(toBytes(data, encoding), key);
  }


  public String hmacSha512Hex(InputStream data, InputStream key) {
    return hmacSha512Hex(toBytes(data), toBytes(key));
  }


  public String hmacSha512Hex(InputStream data, byte[] key) {
    return hmacSha512Hex(toBytes(data), key);
  }

  public String hmacSha512Hex(File data, File key) {
    return hmacSha512Hex(toBytes(data), toBytes(key));
  }

  public String hmacSha512Hex(File data, byte[] key) {
    return hmacSha512Hex(toBytes(data), key);
  }

  // endregion


  // region hmacSha512B64


  public static String hmacSha512B64(byte[] data, byte[] key) {
    byte[] bytes = hmacSha512(data, key);
    return DatatypeConverter.printBase64Binary(bytes);
  }

  public static String hmacSha512B64(char[] data, char[] key) {
    return hmacSha512B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha512B64(char[] data, byte[] key) {
    return hmacSha512B64(toBytes(data), key);
  }


  public static String hmacSha512B64(char[] data, char[] key, String encoding) {
    return hmacSha512B64(toBytes(data, encoding), toBytes(key, encoding));
  }


  public static String hmacSha512B64(char[] data, String encoding, byte[] key) {
    return hmacSha512B64(toBytes(data, encoding), key);
  }


  public static String hmacSha512B64(String data, String key) {
    return hmacSha512B64(toBytes(data), toBytes(key));
  }

  public static String hmacSha512B64(String data, byte[] key) {
    return hmacSha512B64(toBytes(data), key);
  }

  public static String hmacSha512B64(String data, String key, String encoding) {
    return hmacSha512B64(toBytes(data, encoding), toBytes(key, encoding));
  }

  public static String hmacSha512B64(String data, String encoding, byte[] key) {
    return hmacSha512B64(toBytes(data, encoding), key);
  }


  public String hmacSha512B64(InputStream data, InputStream key) {
    return hmacSha512B64(toBytes(data), toBytes(key));
  }


  public String hmacSha512B64(InputStream data, byte[] key) {
    return hmacSha512B64(toBytes(data), key);
  }

  public String hmacSha512B64(File data, File key) {
    return hmacSha512B64(toBytes(data), toBytes(key));
  }

  public String hmacSha512B64(File data, byte[] key) {
    return hmacSha512B64(toBytes(data), key);
  }


  // endregion


}
