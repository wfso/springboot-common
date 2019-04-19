package com.yioks.springboot.common.codec;

import com.yioks.springboot.common.exception.CodecException;

import java.io.*;

public abstract class CodecSupport {

  public static final String PREFERRED_ENCODING = "UTF-8";

  public static byte[] toBytes(char[] chars) {
    return toBytes(new String(chars), PREFERRED_ENCODING);
  }

  public static byte[] toBytes(char[] chars, String encoding) throws CodecException {
    return toBytes(new String(chars), encoding);
  }


  public static byte[] toBytes(String source) {
    return toBytes(source, PREFERRED_ENCODING);
  }


  public static byte[] toBytes(String source, String encoding) throws CodecException {
    try {
      return source.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      String msg = "Unable to convert source [" + source + "] to byte array using " +
        "encoding '" + encoding + "'";
      throw new CodecException(msg, e);
    }
  }


  public static String toString(byte[] bytes) {
    return toString(bytes, PREFERRED_ENCODING);
  }


  public static String toString(byte[] bytes, String encoding) throws CodecException {
    try {
      return new String(bytes, encoding);
    } catch (UnsupportedEncodingException e) {
      String msg = "Unable to convert byte array to String with encoding '" + encoding + "'.";
      throw new CodecException(msg, e);
    }
  }


  public static char[] toChars(byte[] bytes) {
    return toChars(bytes, PREFERRED_ENCODING);
  }


  public static char[] toChars(byte[] bytes, String encoding) throws CodecException {
    return toString(bytes, encoding).toCharArray();
  }


  protected static boolean isByteSource(Object o) {
    return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof File || o instanceof InputStream;
  }


  protected static byte[] toBytes(Object o) {
    if (o == null) {
      String msg = "Argument for byte conversion cannot be null.";
      throw new IllegalArgumentException(msg);
    }
    if (o instanceof byte[]) {
      return (byte[]) o;
    } else if (o instanceof char[]) {
      return toBytes((char[]) o);
    } else if (o instanceof String) {
      return toBytes((String) o);
    } else if (o instanceof File) {
      return toBytes((File) o);
    } else if (o instanceof InputStream) {
      return toBytes((InputStream) o);
    } else {
      return objectToBytes(o);
    }
  }

  protected static String toString(Object o) {
    if (o == null) {
      String msg = "Argument for String conversion cannot be null.";
      throw new IllegalArgumentException(msg);
    }
    if (o instanceof byte[]) {
      return toString((byte[]) o);
    } else if (o instanceof char[]) {
      return new String((char[]) o);
    } else if (o instanceof String) {
      return (String) o;
    } else {
      return objectToString(o);
    }
  }

  protected static byte[] toBytes(File file) {
    if (file == null) {
      throw new IllegalArgumentException("File argument cannot be null.");
    }
    try {
      return toBytes(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      String msg = "Unable to acquire InputStream for file [" + file + "]";
      throw new CodecException(msg, e);
    }
  }

  protected static byte[] toBytes(InputStream in) {
    if (in == null) {
      throw new IllegalArgumentException("InputStream argument cannot be null.");
    }
    final int BUFFER_SIZE = 512;
    ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
    byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    try {
      while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
      return out.toByteArray();
    } catch (IOException ioe) {
      throw new CodecException(ioe);
    } finally {
      try {
        in.close();
      } catch (IOException ignored) {
      }
      try {
        out.close();
      } catch (IOException ignored) {
      }
    }
  }

  protected static byte[] objectToBytes(Object o) {
    String msg = "The " + CodecSupport.class.getName() + " implementation only supports conversion to " +
      "byte[] if the source is of type byte[], char[], String, " +
      " File or InputStream.  The instance provided as a method " +
      "argument is of type [" + o.getClass().getName() + "].  If you would like to convert " +
      "this argument type to a byte[], you can 1) convert the argument to one of the supported types " +
      "yourself and then use that as the method argument or 2) subclass " + CodecSupport.class.getName() +
      "and override the objectToBytes(Object o) method.";
    throw new CodecException(msg);
  }

  /**
   * Default implementation merely returns <code>objectArgument.toString()</code>.  Subclasses can override this
   * method for different mechanisms of converting an object to a String.
   *
   * @param o the Object to convert to a byte array.
   * @return a String representation of the Object argument.
   */
  protected static String objectToString(Object o) {
    return o.toString();
  }
}
