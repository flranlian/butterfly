package com.chain.mode.doc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by WeiZheng on 2015/9/25.
 */
@JsonAutoDetect(fieldVisibility= Visibility.ANY)
public class ServiceDoc<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value="SERVICE")
    private ServiceInfo<T> serviceInfo;

    public ServiceInfo<T> getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo<T> serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
