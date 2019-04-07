package com.yioks.springboot.common.utils;

import org.apache.shiro.codec.CodecSupport;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RsaUtil extends CodecSupport {

  private static KeyFactory keyFactory;

  private static KeyPairGenerator keyPairGen;

  static {
    try {
      keyFactory = KeyFactory.getInstance("RSA");
      keyPairGen = KeyPairGenerator.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  // region 获取 RSA 公钥和私钥（密钥对）
  public static Map<String, String> genRsaKey(int keySize) {
    keyPairGen.initialize(keySize);
    KeyPair keyPair = keyPairGen.genKeyPair();
    Map<String, String> keyStore = new HashMap<>();
    keyStore.put("publicKey", DatatypeConverter.printBase64Binary(keyPair.getPublic().getEncoded()));
    keyStore.put("privateKey", DatatypeConverter.printBase64Binary(keyPair.getPrivate().getEncoded()));
    return keyStore;
  }
  // endregion

  // region 签名
  private static byte[] sign(byte[] data, String encodedPrivateKey, String algorithm) {
    try {
      Signature signature = Signature.getInstance(algorithm);
      RSAPrivateKey privateKey = loadPrivateKeyByStr(encodedPrivateKey);
      if (privateKey != null) {
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (SignatureException e) {
      e.printStackTrace();
    }
    return new byte[0];
  }
  // endregion

  // region 签名 md2
  public static byte[] signMd2(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "MD2withRSA");
  }


  public static String signMd2Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signMd2(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signMd2Hex(char[] data, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd2Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signMd2Hex(String data, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd2Hex(String data, String encoding, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signMd2Hex(InputStream data, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data), encodedPrivateKey);
  }


  public String signMd2Hex(File data, String encodedPrivateKey) {
    return signMd2Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd2B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signMd2(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signMd2B64(char[] data, String encodedPrivateKey) {
    return signMd2B64(toBytes(data), encodedPrivateKey);
  }


  public static String signMd2B64(char[] data, String encoding, String encodedPrivateKey) {
    return signMd2B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signMd2B64(String data, String encodedPrivateKey) {
    return signMd2B64(toBytes(data), encodedPrivateKey);
  }


  public static String signMd2B64(String data, String encoding, String encodedPrivateKey) {
    return signMd2B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signMd2B64(InputStream data, String encodedPrivateKey) {
    return signMd2B64(toBytes(data), encodedPrivateKey);
  }


  public String signMd2B64(File data, String encodedPrivateKey) {
    return signMd2B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 md5
  public static byte[] signMd5(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Md5withRSA");
  }


  public static String signMd5Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signMd5(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signMd5Hex(char[] data, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd5Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signMd5Hex(String data, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd5Hex(String data, String encoding, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signMd5Hex(InputStream data, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data), encodedPrivateKey);
  }


  public String signMd5Hex(File data, String encodedPrivateKey) {
    return signMd5Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signMd5B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signMd5(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signMd5B64(char[] data, String encodedPrivateKey) {
    return signMd5B64(toBytes(data), encodedPrivateKey);
  }


  public static String signMd5B64(char[] data, String encoding, String encodedPrivateKey) {
    return signMd5B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signMd5B64(String data, String encodedPrivateKey) {
    return signMd5B64(toBytes(data), encodedPrivateKey);
  }


  public static String signMd5B64(String data, String encoding, String encodedPrivateKey) {
    return signMd5B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signMd5B64(InputStream data, String encodedPrivateKey) {
    return signMd5B64(toBytes(data), encodedPrivateKey);
  }


  public String signMd5B64(File data, String encodedPrivateKey) {
    return signMd5B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 sha1
  public static byte[] signSha1(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Sha1withRSA");
  }


  public static String signSha1Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha1(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signSha1Hex(char[] data, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha1Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha1Hex(String data, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha1Hex(String data, String encoding, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha1Hex(InputStream data, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data), encodedPrivateKey);
  }


  public String signSha1Hex(File data, String encodedPrivateKey) {
    return signSha1Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha1B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha1(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signSha1B64(char[] data, String encodedPrivateKey) {
    return signSha1B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha1B64(char[] data, String encoding, String encodedPrivateKey) {
    return signSha1B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha1B64(String data, String encodedPrivateKey) {
    return signSha1B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha1B64(String data, String encoding, String encodedPrivateKey) {
    return signSha1B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha1B64(InputStream data, String encodedPrivateKey) {
    return signSha1B64(toBytes(data), encodedPrivateKey);
  }


  public String signSha1B64(File data, String encodedPrivateKey) {
    return signSha1B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 sha224
  public static byte[] signSha224(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Sha224withRSA");
  }


  public static String signSha224Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha224(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signSha224Hex(char[] data, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha224Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha224Hex(String data, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha224Hex(String data, String encoding, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha224Hex(InputStream data, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data), encodedPrivateKey);
  }


  public String signSha224Hex(File data, String encodedPrivateKey) {
    return signSha224Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha224B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha224(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signSha224B64(char[] data, String encodedPrivateKey) {
    return signSha224B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha224B64(char[] data, String encoding, String encodedPrivateKey) {
    return signSha224B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha224B64(String data, String encodedPrivateKey) {
    return signSha224B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha224B64(String data, String encoding, String encodedPrivateKey) {
    return signSha224B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha224B64(InputStream data, String encodedPrivateKey) {
    return signSha224B64(toBytes(data), encodedPrivateKey);
  }


  public String signSha224B64(File data, String encodedPrivateKey) {
    return signSha224B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 sha256
  public static byte[] signSha256(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Sha256withRSA");
  }


  public static String signSha256Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha256(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signSha256Hex(char[] data, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha256Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha256Hex(String data, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha256Hex(String data, String encoding, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha256Hex(InputStream data, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data), encodedPrivateKey);
  }


  public String signSha256Hex(File data, String encodedPrivateKey) {
    return signSha256Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha256B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha256(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signSha256B64(char[] data, String encodedPrivateKey) {
    return signSha256B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha256B64(char[] data, String encoding, String encodedPrivateKey) {
    return signSha256B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha256B64(String data, String encodedPrivateKey) {
    return signSha256B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha256B64(String data, String encoding, String encodedPrivateKey) {
    return signSha256B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha256B64(InputStream data, String encodedPrivateKey) {
    return signSha256B64(toBytes(data), encodedPrivateKey);
  }


  public String signSha256B64(File data, String encodedPrivateKey) {
    return signSha256B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 sha384
  public static byte[] signSha384(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Sha384withRSA");
  }


  public static String signSha384Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha384(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signSha384Hex(char[] data, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha384Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha384Hex(String data, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha384Hex(String data, String encoding, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha384Hex(InputStream data, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data), encodedPrivateKey);
  }


  public String signSha384Hex(File data, String encodedPrivateKey) {
    return signSha384Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha384B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha384(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signSha384B64(char[] data, String encodedPrivateKey) {
    return signSha384B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha384B64(char[] data, String encoding, String encodedPrivateKey) {
    return signSha384B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha384B64(String data, String encodedPrivateKey) {
    return signSha384B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha384B64(String data, String encoding, String encodedPrivateKey) {
    return signSha384B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha384B64(InputStream data, String encodedPrivateKey) {
    return signSha384B64(toBytes(data), encodedPrivateKey);
  }


  public String signSha384B64(File data, String encodedPrivateKey) {
    return signSha384B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 签名 sha512
  public static byte[] signSha512(byte[] data, String encodedPrivateKey) {
    return sign(data, encodedPrivateKey, "Sha512withRSA");
  }


  public static String signSha512Hex(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha512(data, encodedPrivateKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String signSha512Hex(char[] data, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha512Hex(char[] data, String encoding, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha512Hex(String data, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha512Hex(String data, String encoding, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha512Hex(InputStream data, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data), encodedPrivateKey);
  }


  public String signSha512Hex(File data, String encodedPrivateKey) {
    return signSha512Hex(toBytes(data), encodedPrivateKey);
  }


  public static String signSha512B64(byte[] data, String encodedPrivateKey) {
    byte[] bytes = signSha512(data, encodedPrivateKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String signSha512B64(char[] data, String encodedPrivateKey) {
    return signSha512B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha512B64(char[] data, String encoding, String encodedPrivateKey) {
    return signSha512B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public static String signSha512B64(String data, String encodedPrivateKey) {
    return signSha512B64(toBytes(data), encodedPrivateKey);
  }


  public static String signSha512B64(String data, String encoding, String encodedPrivateKey) {
    return signSha512B64(toBytes(data, encoding), encodedPrivateKey);
  }


  public String signSha512B64(InputStream data, String encodedPrivateKey) {
    return signSha512B64(toBytes(data), encodedPrivateKey);
  }


  public String signSha512B64(File data, String encodedPrivateKey) {
    return signSha512B64(toBytes(data), encodedPrivateKey);
  }

  // endregion


  // region 验签
  public static boolean verify(byte[] data, byte[] sign, String encodedPublicKey, String algorithm) {
    try {
      Signature signature = Signature.getInstance(algorithm);
      RSAPublicKey publicKey = loadPublicKeyByStr(encodedPublicKey);
      if (publicKey != null) {
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (SignatureException e) {
      e.printStackTrace();
    }
    return false;
  }
  // endregion


  // region 验签 md2
  public static boolean verifyMd2(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "MD2withRSA");
  }

  public static boolean verifyMd2Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifyMd2(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifyMd2Hex(char[] data, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd2Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifyMd2Hex(String data, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd2Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifyMd2Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifyMd2Hex(File data, String sign, String encodedPublicKey) {
    return verifyMd2Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd2B64(byte[] data, String sign, String encodedPublicKey) {
    return verifyMd2(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifyMd2B64(char[] data, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd2B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifyMd2B64(String data, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd2B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifyMd2B64(InputStream data, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifyMd2B64(File data, String sign, String encodedPublicKey) {
    return verifyMd2B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 md5
  public static boolean verifyMd5(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Md5withRSA");
  }

  public static boolean verifyMd5Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifyMd5(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifyMd5Hex(char[] data, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd5Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifyMd5Hex(String data, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd5Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifyMd5Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifyMd5Hex(File data, String sign, String encodedPublicKey) {
    return verifyMd5Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd5B64(byte[] data, String sign, String encodedPublicKey) {
    return verifyMd5(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifyMd5B64(char[] data, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd5B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifyMd5B64(String data, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifyMd5B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifyMd5B64(InputStream data, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifyMd5B64(File data, String sign, String encodedPublicKey) {
    return verifyMd5B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 sha1
  public static boolean verifySha1(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Sha1withRSA");
  }

  public static boolean verifySha1Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifySha1(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifySha1Hex(char[] data, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha1Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha1Hex(String data, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha1Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha1Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha1Hex(File data, String sign, String encodedPublicKey) {
    return verifySha1Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha1B64(byte[] data, String sign, String encodedPublicKey) {
    return verifySha1(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifySha1B64(char[] data, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha1B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha1B64(String data, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha1B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha1B64(InputStream data, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha1B64(File data, String sign, String encodedPublicKey) {
    return verifySha1B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 sha224
  public static boolean verifySha224(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Sha224withRSA");
  }

  public static boolean verifySha224Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifySha224(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifySha224Hex(char[] data, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha224Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha224Hex(String data, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha224Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha224Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha224Hex(File data, String sign, String encodedPublicKey) {
    return verifySha224Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha224B64(byte[] data, String sign, String encodedPublicKey) {
    return verifySha224(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifySha224B64(char[] data, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha224B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha224B64(String data, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha224B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha224B64(InputStream data, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha224B64(File data, String sign, String encodedPublicKey) {
    return verifySha224B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 sha256
  public static boolean verifySha256(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Sha256withRSA");
  }

  public static boolean verifySha256Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifySha256(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifySha256Hex(char[] data, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha256Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha256Hex(String data, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha256Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha256Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha256Hex(File data, String sign, String encodedPublicKey) {
    return verifySha256Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha256B64(byte[] data, String sign, String encodedPublicKey) {
    return verifySha256(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifySha256B64(char[] data, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha256B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha256B64(String data, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha256B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha256B64(InputStream data, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha256B64(File data, String sign, String encodedPublicKey) {
    return verifySha256B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 sha384
  public static boolean verifySha384(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Sha384withRSA");
  }

  public static boolean verifySha384Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifySha384(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifySha384Hex(char[] data, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha384Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha384Hex(String data, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha384Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha384Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha384Hex(File data, String sign, String encodedPublicKey) {
    return verifySha384Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha384B64(byte[] data, String sign, String encodedPublicKey) {
    return verifySha384(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifySha384B64(char[] data, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha384B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha384B64(String data, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha384B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha384B64(InputStream data, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha384B64(File data, String sign, String encodedPublicKey) {
    return verifySha384B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 验签 sha512
  public static boolean verifySha512(byte[] data, byte[] sign, String encodedPublicKey) {
    return verify(data, sign, encodedPublicKey, "Sha512withRSA");
  }

  public static boolean verifySha512Hex(byte[] data, String sign, String encodedPublicKey) {
    return verifySha512(data, DatatypeConverter.parseHexBinary(sign), encodedPublicKey);
  }


  public static boolean verifySha512Hex(char[] data, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha512Hex(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha512Hex(String data, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha512Hex(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha512Hex(InputStream data, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha512Hex(File data, String sign, String encodedPublicKey) {
    return verifySha512Hex(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha512B64(byte[] data, String sign, String encodedPublicKey) {
    return verifySha512(data, DatatypeConverter.parseBase64Binary(sign), encodedPublicKey);
  }


  public static boolean verifySha512B64(char[] data, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha512B64(char[] data, String encoding, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public static boolean verifySha512B64(String data, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data), sign, encodedPublicKey);
  }


  public static boolean verifySha512B64(String data, String encoding, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data, encoding), sign, encodedPublicKey);
  }


  public boolean verifySha512B64(InputStream data, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data), sign, encodedPublicKey);
  }


  public boolean verifySha512B64(File data, String sign, String encodedPublicKey) {
    return verifySha512B64(toBytes(data), sign, encodedPublicKey);
  }

  // endregion


  // region 加密
  public static byte[] encrypt(byte[] data, String encodedPublicKey) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      RSAPublicKey publicKey = loadPublicKeyByStr(encodedPublicKey);
      if(publicKey!=null){
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        cipher.update(data);
        return cipher.doFinal();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    }
    return new byte[0];
  }

  public static byte[] encrypt(char[] data, String encodedPublicKey) {
    return encrypt(toBytes(data), encodedPublicKey);
  }

  public static byte[] encrypt(char[] data, String encoding, String encodedPublicKey) {
    return encrypt(toBytes(data, encoding), encodedPublicKey);
  }

  public static byte[] encrypt(String data, String encodedPublicKey) {
    return encrypt(toBytes(data), encodedPublicKey);
  }

  public static byte[] encrypt(String data, String encoding, String encodedPublicKey) {
    return encrypt(toBytes(data, encoding), encodedPublicKey);
  }

  public byte[] encrypt(InputStream data, String encodedPublicKey) {
    return encrypt(toBytes(data), encodedPublicKey);
  }

  public byte[] encrypt(File data, String encodedPublicKey) {
    return encrypt(toBytes(data), encodedPublicKey);
  }

  // endregion

  // region 加密 hex

  public static String encryptHex(byte[] data, String encodedPublicKey) {
    byte[] bytes = encrypt(data, encodedPublicKey);
    return DatatypeConverter.printHexBinary(bytes);
  }


  public static String encryptHex(char[] data, String encodedPublicKey) {
    return encryptHex(toBytes(data), encodedPublicKey);
  }

  public static String encryptHex(char[] data, String encoding, String encodedPublicKey) {
    return encryptHex(toBytes(data, encoding), encodedPublicKey);
  }

  public static String encryptHex(String data, String encodedPublicKey) {
    return encryptHex(toBytes(data), encodedPublicKey);
  }

  public static String encryptHex(String data, String encoding, String encodedPublicKey) {
    return encryptHex(toBytes(data, encoding), encodedPublicKey);
  }

  public String encryptHex(InputStream data, String encodedPublicKey) {
    return encryptHex(toBytes(data), encodedPublicKey);
  }

  public String encryptHex(File data, String encodedPublicKey) {
    return encryptHex(toBytes(data), encodedPublicKey);
  }

  // endregion

  // region 加密 b64

  public static String encryptB64(byte[] data, String encodedPublicKey) {
    byte[] bytes = encrypt(data, encodedPublicKey);
    return DatatypeConverter.printBase64Binary(bytes);
  }


  public static String encryptB64(char[] data, String encodedPublicKey) {
    return encryptB64(toBytes(data), encodedPublicKey);
  }

  public static String encryptB64(char[] data, String encoding, String encodedPublicKey) {
    return encryptB64(toBytes(data, encoding), encodedPublicKey);
  }

  public static String encryptB64(String data, String encodedPublicKey) {
    return encryptB64(toBytes(data), encodedPublicKey);
  }

  public static String encryptB64(String data, String encoding, String encodedPublicKey) {
    return encryptB64(toBytes(data, encoding), encodedPublicKey);
  }

  public String encryptB64(InputStream data, String encodedPublicKey) {
    return encryptB64(toBytes(data), encodedPublicKey);
  }

  public String encryptB64(File data, String encodedPublicKey) {
    return encryptB64(toBytes(data), encodedPublicKey);
  }

  // endregion


  // region 解密
  public static byte[] decrypt(byte[] data, String encodedPrivateKey) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      RSAPrivateKey privateKey = loadPrivateKeyByStr(encodedPrivateKey);
      if(privateKey!=null){
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        cipher.update(data);
        return cipher.doFinal();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    }
    return new byte[0];
  }

  
  public static String decryptAndToString(byte[] data, String encodedPrivateKey) {
    return new String(decrypt(data,encodedPrivateKey));
  }


  public static String decryptAndToString(byte[] data, String encodedPrivateKey,String encoding) throws UnsupportedEncodingException {
    return new String(decrypt(data,encodedPrivateKey),encoding);
  }

  public byte[] decrypt(InputStream data, String encodedPrivateKey) {
    return decrypt(toBytes(data), encodedPrivateKey);
  }

  public byte[] decrypt(File data, String encodedPrivateKey) {
    return decrypt(toBytes(data), encodedPrivateKey);
  }


  public static byte[] decryptHex(String data, String encodedPrivateKey) {
    return decrypt(DatatypeConverter.parseHexBinary(data), encodedPrivateKey);
  }


  public static String decryptHexAndToString(String data, String encodedPrivateKey) {
    return decryptAndToString(DatatypeConverter.parseHexBinary(data), encodedPrivateKey);
  }


  public static String decryptHexAndToString(String data, String encodedPrivateKey,String encoding) throws UnsupportedEncodingException {
    return decryptAndToString(DatatypeConverter.parseHexBinary(data), encodedPrivateKey,encoding);
  }


  public static byte[] decryptB64(String data, String encodedPrivateKey) {
    return decrypt(DatatypeConverter.parseBase64Binary(data), encodedPrivateKey);
  }




  public static String decryptB64AndToString(String data, String encodedPrivateKey) {
    return decryptAndToString(DatatypeConverter.parseBase64Binary(data), encodedPrivateKey);
  }




  public static String decryptB64AndToString(String data, String encodedPrivateKey,String encoding) throws UnsupportedEncodingException {
    return decryptAndToString(DatatypeConverter.parseBase64Binary(data), encodedPrivateKey,encoding);
  }

  // endregion


  // region 私有通用工具函数
  private static RSAPrivateKey loadPrivateKeyByStr(String encodedPrivateKey) {
    byte[] buffer = DatatypeConverter.parseBase64Binary(encodedPrivateKey);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
    try {
      return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
      return null;
    }
  }


  private static RSAPublicKey loadPublicKeyByStr(String encodedPublicKey) {
    byte[] buffer = DatatypeConverter.parseBase64Binary(encodedPublicKey);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
    try {
      return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
      return null;
    }
  }
  // endregion


}
