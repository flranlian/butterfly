package com.chain.ftp;

/**
 * Created by lian.ran on 2018/3/26.
 */
public class FTPClientException extends RuntimeException {
    public FTPClientException(String message){
        super(message);
    }
    public FTPClientException(String message, Throwable e){
        super(message, e);
    }
}
