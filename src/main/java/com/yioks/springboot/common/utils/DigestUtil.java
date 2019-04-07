package com.yioks.springboot.common.utils;

import org.apache.shiro.codec.CodecSupport;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil extends CodecSupport {


  // region digest

  private static byte[] digest(byte[] data, String algorithm) {
    try {
      MessageDigest digest = MessageDigest.getInstance(algorithm);
      return digest.digest(data);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return new byte[0];
    }
  }

  // endregion

  // region md2

  public static byte[] md2(byte[] bytes) {
    return digest(bytes, "md2");
  }

  public static byte[] md2(char[] chars) {
    return md2(toBytes(chars));
  }


  public static byte[] md2(char[] chars, String encoding) {
    return md2(toBytes(chars, encoding));
  }

  public static byte[] md2(String string) {
    return md2(toBytes(string));
  }


  public static byte[] md2(String string, String encoding) {
    return md2(toBytes(string, encoding));
  }

  public byte[] md2(InputStream inputStream) {
    return md2(toBytes(inputStream));
  }

  public byte[] md2(File file) {
    return md2(toBytes(file));
  }

  // endregion


  // region md2Hex

  public static String md2Hex(byte[] bytes) {
    byte[] rbytes = md2(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String md2Hex(char[] chars) {
    return md2Hex(toBytes(chars));
  }


  public static String md2Hex(char[] chars, String encoding) {
    return md2Hex(toBytes(chars, encoding));
  }

  public static String md2Hex(String string) {
    return md2Hex(toBytes(string));
  }


  public static String md2Hex(String string, String encoding) {
    return md2Hex(toBytes(string, encoding));
  }

  public String md2Hex(InputStream inputStream) {
    return md2Hex(toBytes(inputStream));
  }

  public String md2Hex(File file) {
    return md2Hex(toBytes(file));
  }

  // endregion


  // region md2B64

  public static String md2B64(byte[] bytes) {
    byte[] rbytes = md2(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String md2B64(char[] chars) {
    return md2B64(toBytes(chars));
  }


  public static String md2B64(char[] chars, String encoding) {
    return md2B64(toBytes(chars, encoding));
  }

  public static String md2B64(String string) {
    return md2B64(toBytes(string));
  }


  public static String md2B64(String string, String encoding) {
    return md2B64(toBytes(string, encoding));
  }

  public String md2B64(InputStream inputStream) {
    return md2B64(toBytes(inputStream));
  }

  public String md2B64(File file) {
    return md2B64(toBytes(file));
  }

  // endregion


  // region md5

  public static byte[] md5(byte[] bytes) {
    return digest(bytes, "md5");
  }

  public static byte[] md5(char[] chars) {
    return md5(toBytes(chars));
  }


  public static byte[] md5(char[] chars, String encoding) {
    return md5(toBytes(chars, encoding));
  }


  public static byte[] md5(String string) {
    return md5(toBytes(string));
  }

  public static byte[] md5(String string, String encoding) {
    return md5(toBytes(string, encoding));
  }


  public byte[] md5(InputStream inputStream) {
    return md5(toBytes(inputStream));
  }


  public byte[] md5(File file) {
    return md5(toBytes(file));
  }

  // endregion


  // region md5Hex

  public static String md5Hex(byte[] bytes) {
    byte[] rbytes = md5(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String md5Hex(char[] chars) {
    return md5Hex(toBytes(chars));
  }


  public static String md5Hex(char[] chars, String encoding) {
    return md5Hex(toBytes(chars, encoding));
  }


  public static String md5Hex(String string) {
    return md5Hex(toBytes(string));
  }

  public static String md5Hex(String string, String encoding) {
    return md5Hex(toBytes(string, encoding));
  }


  public String md5Hex(InputStream inputStream) {
    return md5Hex(toBytes(inputStream));
  }


  public String md5Hex(File file) {
    return md5Hex(toBytes(file));
  }

  // endregion


  // region md5B64

  public static String md5B64(byte[] bytes) {
    byte[] rbytes = md5(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String md5B64(char[] chars) {
    return md5B64(toBytes(chars));
  }


  public static String md5B64(char[] chars, String encoding) {
    return md5B64(toBytes(chars, encoding));
  }


  public static String md5B64(String string) {
    return md5B64(toBytes(string));
  }

  public static String md5B64(String string, String encoding) {
    return md5B64(toBytes(string, encoding));
  }


  public String md5B64(InputStream inputStream) {
    return md5B64(toBytes(inputStream));
  }


  public String md5B64(File file) {
    return md5B64(toBytes(file));
  }

  // endregion


  // region sha1

  public static byte[] sha1(byte[] bytes) {
    return digest(bytes, "sha-1");
  }

  public static byte[] sha1(char[] chars) {
    return sha1(toBytes(chars));
  }

  public static byte[] sha1(char[] chars, String encoding) {
    return sha1(toBytes(chars, encoding));
  }

  public static byte[] sha1(String string) {
    return sha1(toBytes(string));
  }


  public static byte[] sha1(String string, String encoding) {
    return sha1(toBytes(string, encoding));
  }

  public byte[] sha1(InputStream inputStream) {
    return sha1(toBytes(inputStream));
  }

  public byte[] sha1(File file) {
    return sha1(toBytes(file));
  }

  // endregion


  // region sha1Hex

  public static String sha1Hex(byte[] bytes) {
    byte[] rbytes = sha1(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String sha1Hex(char[] chars) {
    return sha1Hex(toBytes(chars));
  }

  public static String sha1Hex(char[] chars, String encoding) {
    return sha1Hex(toBytes(chars, encoding));
  }

  public static String sha1Hex(String string) {
    return sha1Hex(toBytes(string));
  }


  public static String sha1Hex(String string, String encoding) {
    return sha1Hex(toBytes(string, encoding));
  }

  public String sha1Hex(InputStream inputStream) {
    return sha1Hex(toBytes(inputStream));
  }

  public String sha1Hex(File file) {
    return sha1Hex(toBytes(file));
  }

  // endregion


  // region sha1B64

  public static String sha1B64(byte[] bytes) {
    byte[] rbytes = sha1(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String sha1B64(char[] chars) {
    return sha1B64(toBytes(chars));
  }

  public static String sha1B64(char[] chars, String encoding) {
    return sha1B64(toBytes(chars, encoding));
  }

  public static String sha1B64(String string) {
    return sha1B64(toBytes(string));
  }


  public static String sha1B64(String string, String encoding) {
    return sha1B64(toBytes(string, encoding));
  }

  public String sha1B64(InputStream inputStream) {
    return sha1B64(toBytes(inputStream));
  }

  public String sha1B64(File file) {
    return sha1B64(toBytes(file));
  }

  // endregion


  // region sha224

  public static byte[] sha224(byte[] bytes) {
    return digest(bytes, "sha-224");
  }

  public static byte[] sha224(char[] chars) {
    return sha224(toBytes(chars));
  }


  public static byte[] sha224(char[] chars, String encoding) {
    return sha224(toBytes(chars, encoding));
  }

  public static byte[] sha224(String string) {
    return sha224(toBytes(string));
  }

  public static byte[] sha224(String string, String encoding) {
    return sha224(toBytes(string, encoding));
  }

  public byte[] sha224(InputStream inputStream) {
    return sha224(toBytes(inputStream));
  }

  public byte[] sha224(File file) {
    return sha224(toBytes(file));
  }

  // endregion


  // region sha224Hex

  public static String sha224Hex(byte[] bytes) {
    byte[] rbytes = sha224(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String sha224Hex(char[] chars) {
    return sha224Hex(toBytes(chars));
  }


  public static String sha224Hex(char[] chars, String encoding) {
    return sha224Hex(toBytes(chars, encoding));
  }

  public static String sha224Hex(String string) {
    return sha224Hex(toBytes(string));
  }

  public static String sha224Hex(String string, String encoding) {
    return sha224Hex(toBytes(string, encoding));
  }

  public String sha224Hex(InputStream inputStream) {
    return sha224Hex(toBytes(inputStream));
  }

  public String sha224Hex(File file) {
    return sha224Hex(toBytes(file));
  }

  // endregion


  // region sha224B64

  public static String sha224B64(byte[] bytes) {
    byte[] rbytes = sha224(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String sha224B64(char[] chars) {
    return sha224B64(toBytes(chars));
  }


  public static String sha224B64(char[] chars, String encoding) {
    return sha224B64(toBytes(chars, encoding));
  }

  public static String sha224B64(String string) {
    return sha224B64(toBytes(string));
  }

  public static String sha224B64(String string, String encoding) {
    return sha224B64(toBytes(string, encoding));
  }

  public String sha224B64(InputStream inputStream) {
    return sha224B64(toBytes(inputStream));
  }

  public String sha224B64(File file) {
    return sha224B64(toBytes(file));
  }

  // endregion


  // region sha256

  public static byte[] sha256(byte[] bytes) {
    return digest(bytes, "sha-256");
  }

  public static byte[] sha256(char[] chars) {
    return sha256(toBytes(chars));
  }

  public static byte[] sha256(char[] chars, String encoding) {
    return sha256(toBytes(chars, encoding));
  }

  public static byte[] sha256(String string) {
    return sha256(toBytes(string));
  }

  public static byte[] sha256(String string, String encoding) {
    return sha256(toBytes(string, encoding));
  }

  public byte[] sha256(InputStream inputStream) {
    return sha256(toBytes(inputStream));
  }

  public byte[] sha256(File file) {
    return sha256(toBytes(file));
  }

  // endregion


  // region sha256Hex

  public static String sha256Hex(byte[] bytes) {
    byte[] rbytes = sha256(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String sha256Hex(char[] chars) {
    return sha256Hex(toBytes(chars));
  }

  public static String sha256Hex(char[] chars, String encoding) {
    return sha256Hex(toBytes(chars, encoding));
  }

  public static String sha256Hex(String string) {
    return sha256Hex(toBytes(string));
  }

  public static String sha256Hex(String string, String encoding) {
    return sha256Hex(toBytes(string, encoding));
  }

  public String sha256Hex(InputStream inputStream) {
    return sha256Hex(toBytes(inputStream));
  }

  public String sha256Hex(File file) {
    return sha256Hex(toBytes(file));
  }

  // endregion


  // region sha256B64

  public static String sha256B64(byte[] bytes) {
    byte[] rbytes = sha256(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String sha256B64(char[] chars) {
    return sha256B64(toBytes(chars));
  }

  public static String sha256B64(char[] chars, String encoding) {
    return sha256B64(toBytes(chars, encoding));
  }

  public static String sha256B64(String string) {
    return sha256B64(toBytes(string));
  }

  public static String sha256B64(String string, String encoding) {
    return sha256B64(toBytes(string, encoding));
  }

  public String sha256B64(InputStream inputStream) {
    return sha256B64(toBytes(inputStream));
  }

  public String sha256B64(File file) {
    return sha256B64(toBytes(file));
  }

  // endregion


  // region sha384

  public static byte[] sha384(byte[] bytes) {
    return digest(bytes, "sha-384");
  }

  public static byte[] sha384(char[] chars) {
    return sha384(toBytes(chars));
  }

  public static byte[] sha384(char[] chars, String encoding) {
    return sha384(toBytes(chars, encoding));
  }

  public static byte[] sha384(String string) {
    return sha384(toBytes(string));
  }

  public static byte[] sha384(String string, String encoding) {
    return sha384(toBytes(string, encoding));
  }

  public byte[] sha384(InputStream inputStream) {
    return sha384(toBytes(inputStream));
  }

  public byte[] sha384(File file) {
    return sha384(toBytes(file));
  }

  // endregion


  // region sha384Hex

  public static String sha384Hex(byte[] bytes) {
    byte[] rbytes = sha384(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String sha384Hex(char[] chars) {
    return sha384Hex(toBytes(chars));
  }

  public static String sha384Hex(char[] chars, String encoding) {
    return sha384Hex(toBytes(chars, encoding));
  }

  public static String sha384Hex(String string) {
    return sha384Hex(toBytes(string));
  }

  public static String sha384Hex(String string, String encoding) {
    return sha384Hex(toBytes(string, encoding));
  }

  public String sha384Hex(InputStream inputStream) {
    return sha384Hex(toBytes(inputStream));
  }

  public String sha384Hex(File file) {
    return sha384Hex(toBytes(file));
  }

  // endregion


  // region sha384B64

  public static String sha384B64(byte[] bytes) {
    byte[] rbytes = sha384(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String sha384B64(char[] chars) {
    return sha384B64(toBytes(chars));
  }

  public static String sha384B64(char[] chars, String encoding) {
    return sha384B64(toBytes(chars, encoding));
  }

  public static String sha384B64(String string) {
    return sha384B64(toBytes(string));
  }

  public static String sha384B64(String string, String encoding) {
    return sha384B64(toBytes(string, encoding));
  }

  public String sha384B64(InputStream inputStream) {
    return sha384B64(toBytes(inputStream));
  }

  public String sha384B64(File file) {
    return sha384B64(toBytes(file));
  }

  // endregion


  // region sha512

  public static byte[] sha512(byte[] bytes) {
    return digest(bytes, "sha-512");
  }

  public static byte[] sha512(char[] chars) {
    return sha512(toBytes(chars));
  }

  public static byte[] sha512(char[] chars, String encoding) {
    return sha512(toBytes(chars, encoding));
  }

  public static byte[] sha512(String string) {
    return sha512(toBytes(string));
  }

  public static byte[] sha512(String string, String encoding) {
    return sha512(toBytes(string, encoding));
  }

  public byte[] sha512(InputStream inputStream) {
    return sha512(toBytes(inputStream));
  }

  public byte[] sha512(File file) {
    return sha512(toBytes(file));
  }

  // endregion


  // region sha512Hex

  public static String sha512Hex(byte[] bytes) {
    byte[] rbytes = sha512(bytes);
    return DatatypeConverter.printHexBinary(rbytes);
  }

  public static String sha512Hex(char[] chars) {
    return sha512Hex(toBytes(chars));
  }

  public static String sha512Hex(char[] chars, String encoding) {
    return sha512Hex(toBytes(chars, encoding));
  }

  public static String sha512Hex(String string) {
    return sha512Hex(toBytes(string));
  }

  public static String sha512Hex(String string, String encoding) {
    return sha512Hex(toBytes(string, encoding));
  }

  public String sha512Hex(InputStream inputStream) {
    return sha512Hex(toBytes(inputStream));
  }

  public String sha512Hex(File file) {
    return sha512Hex(toBytes(file));
  }

  // endregion


  // region sha512B64

  public static String sha512B64(byte[] bytes) {
    byte[] rbytes = sha512(bytes);
    return DatatypeConverter.printBase64Binary(rbytes);
  }

  public static String sha512B64(char[] chars) {
    return sha512B64(toBytes(chars));
  }

  public static String sha512B64(char[] chars, String encoding) {
    return sha512B64(toBytes(chars, encoding));
  }

  public static String sha512B64(String string) {
    return sha512B64(toBytes(string));
  }

  public static String sha512B64(String string, String encoding) {
    return sha512B64(toBytes(string, encoding));
  }

  public String sha512B64(InputStream inputStream) {
    return sha512B64(toBytes(inputStream));
  }

  public String sha512B64(File file) {
    return sha512B64(toBytes(file));
  }

  // endregion


}
