package com.yioks.springboot.common.utils;


import java.security.SecureRandom;
import java.util.UUID;

public class RandomUtil {
  public static final SecureRandom secureRandom = new SecureRandom();
  public static final String baseStr = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
  public static final int baseStrSize = baseStr.length();

  public static int generateInt() {
    return secureRandom.nextInt();
  }

  public static int generateInt(int bound) {
    return secureRandom.nextInt(bound);
  }


  public static float generateFloat() {
    return secureRandom.nextFloat();
  }


  public static double generateDouble() {
    return secureRandom.nextDouble();
  }


  public static long generateLong() {
    return secureRandom.nextLong();
  }


  public static boolean generateBoolean() {
    return secureRandom.nextBoolean();
  }


  public static byte[] generateBytes(int size) {
    byte[] bytes = new byte[size];
    secureRandom.nextBytes(bytes);
    return bytes;
  }

  public static String generateString(int size) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < size; i++) {
      buffer.append(generateChar());
    }
    return buffer.toString();
  }


  public static char generateChar() {
    byte[] b = generateBytes(2);
    return (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
  }


  public static char[] generateChars(int size) {
    char[] chars = new char[size];
    for (int i = 0; i < size; i++) {
      chars[i] = generateChar();
    }
    return chars;
  }


  public static String generateAsciiString(int size) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < size; i++) {
      buffer.append(generateAsciiChar());
    }
    return buffer.toString();
  }

  public static String generateCustomizedString(String source, int size) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < size; i++) {
      buffer.append(source.charAt(generateInt(source.length())));
    }
    return buffer.toString();
  }


  public static char generateAsciiChar() {
    return baseStr.charAt(generateInt(baseStrSize));
  }


  public static char[] generateAsciiChars(int size) {
    char[] chars = new char[size];
    for (int i = 0; i < size; i++) {
      chars[i] = generateAsciiChar();
    }
    return chars;
  }

  public static String generateUuid() {
    return UUID.randomUUID().toString();
  }


  public static String generateUnseparatedUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }


}
