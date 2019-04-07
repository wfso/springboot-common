package com.yioks.springboot.common.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

public class SerializationUtil {

  // 把 Object 对象转化为 byte数组
  public static String serialization(Object obj) {
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    byte[] bytes = null;
    try {
      ObjectOutputStream oo = new ObjectOutputStream(bo);
      oo.writeObject(obj);
      bytes = bo.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return DatatypeConverter.printBase64Binary(bytes);
  }

  // 把 byte数组 还原为 Object 对象
  public static Object deserialization(String string) {
    if (string == null) {
      return null;
    }
    ByteArrayInputStream bi = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(string));
    ObjectInputStream in;
    Object obj = null;
    try {
      in = new ObjectInputStream(bi);
      obj = in.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return obj;
  }
}
