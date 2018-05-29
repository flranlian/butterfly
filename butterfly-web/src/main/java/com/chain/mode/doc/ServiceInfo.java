package com.chain.mode.doc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by WeiZheng on 2015/9/25.
 */
@JsonAutoDetect(fieldVisibility= Visibility.ANY)
public class ServiceInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value="SERVICE_HEADER")
    private ServiceHeader serviceHeader;

    @JsonProperty(value="SERVICE_BODY")
    private ServiceBody<T> serviceBody;

    public ServiceHeader getServiceHeader() {
        return serviceHeader;
    }

    public void setServiceHeader(ServiceHeader serviceHeader) {
        this.serviceHeader = serviceHeader;
    }

    public ServiceBody<T> getServiceBody() {
        return serviceBody;
    }

    public void setServiceBody(ServiceBody<T> serviceBody) {
        this.serviceBody = serviceBody;
    }
}
