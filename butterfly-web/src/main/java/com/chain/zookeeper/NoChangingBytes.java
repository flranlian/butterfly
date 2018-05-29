package com.chain.zookeeper;

public class NoChangingBytes extends RuntimeException {
  public final String subPath;

  public NoChangingBytes(String subPath) {
    super("subPath = " + subPath);
    this.subPath = subPath;
  }
}
