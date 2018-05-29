package com.chain.mode.doc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by WeiZheng on 2015/9/25.
 */
@JsonAutoDetect(fieldVisibility= Visibility.ANY)
public class ServiceBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接受报文信息
     */
    @JsonProperty(value="REQUEST")
    private T request;

    /**
     * 返回报文信息
     */
    @JsonProperty(value="RESPONSE")
    private T response;


    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}