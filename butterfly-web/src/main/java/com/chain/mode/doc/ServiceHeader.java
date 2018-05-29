package com.chain.mode.doc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by WeiZheng on 2015/9/25.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServiceHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易服务码
     */
    @JsonProperty(value = "SERVICE_ID")
    private String serviceId;
    /**
     * 本系统机构编号
     */
    @JsonProperty(value = "ORG")
    private String org;
    /**
     * 服务渠道编号
     */
    @JsonProperty(value = "CHANNEL_ID")
    private String channelId;
    /**
     * 收单机构编号
     */
    @JsonProperty(value = "ACQ_ID")
    private String acqId;
    /**
     * 终端类型
     */
    @JsonProperty(value = "SUB_TERMINAL_TYPE")
    private String subTerminalType;
    /**
     * 请求交易流水号
     */
    @JsonProperty(value = "SERVICESN")
    private String serviceSn;
    /**
     * 操作员号
     */
    @JsonProperty(value = "OP_ID")
    private String opId;

    /**
     * 交易响应时间
     * YYYYMMDDHHMMSS
     */
    @JsonProperty(value = "REQUEST_TIME")
    private String requstTime;
    /**
     * 版本号
     */
    @JsonProperty(value = "VERSION_ID")
    private String versionId;

    /**
     * 信息安全码MAC
     */
    @JsonProperty(value = "MAC")
    private String mac;
   
	/**
     * UUID
     */
    @JsonProperty(value = "UUID")
    private String uuid;

    /**
     * 响应状态信息
     */
    @JsonProperty(value = "SERV_RESPONSE")
    private ServResponse servResponse;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAcqId() {
        return acqId;
    }

    public void setAcqId(String acqId) {
        this.acqId = acqId;
    }

    public String getSubTerminalType() {
        return subTerminalType;
    }

    public void setSubTerminalType(String subTerminalType) {
        this.subTerminalType = subTerminalType;
    }

    public String getServiceSn() {
        return serviceSn;
    }

    public void setServiceSn(String serviceSn) {
        this.serviceSn = serviceSn;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }

    public String getRequstTime() {
        return requstTime;
    }

    public void setRequstTime(String requstTime) {
        this.requstTime = requstTime;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    public ServResponse getServResponse() {
        return servResponse;
    }

    public void setServResponse(ServResponse servResponse) {
        this.servResponse = servResponse;
    }
}

