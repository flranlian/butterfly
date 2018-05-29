package com.chain.zookeeper;

import java.nio.charset.StandardCharsets;
import java.util.List;

public interface ZookeeperAccess {

  void close();

  ZookeeperAccess cd(String subPath);

  ZookeeperAccess root();

  default void set(String subPath, String value) {
    setBytes(subPath, value == null ? null : value.getBytes(StandardCharsets.UTF_8));
  }

  void setBytes(String subPath, byte[] bytes);

  byte[] getBytes(String subPath);

  boolean exists(String subPath);

  default String get(String subPath, String defaultValue) {
    byte[] bytes = getBytes(subPath);
    if (bytes == null) return null;
    return new String(bytes, StandardCharsets.UTF_8);
  }

  default String get(String subPath) {
    return get(subPath, null);
  }

  void delete(String subPath);

  Tapping getChangingBytes(String subPath, final GetBytesHandler handler);
  Tapping getPathCacheChangingBytes(String subPath, final GetBytesHandler handler);


  long lastModifiedMillis(String subPath);

  String getRootPath();

  List<String> ls();

  String fullName();

  String name();
}