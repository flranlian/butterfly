package com.chain.mode;

import java.io.Serializable;

/**
 * 基础ccs接口参数
 *  
 * @author zhaofei
 * @author pengfei.yan
 * @date 2015年12月7日 下午2:12:46
 */
public class BaseRequest implements Serializable {
    public static final long serialVersionUID = 1L;
    public static final String ORG = "000000000001";
    /**
     * 渠道编号API
     * 阳光     - SUNS
     * 审批     - BANK
     * APP     - BANK
     * 催收     - BANK
     * 商户平台  - MSCM
     * 支付平台  - MSPY
     */
    public static final String CHANNEL_ID = "BANK";

    /**
     * 调用方组织机构号
     */
    private String org = ORG;
    /**
     * 长亮调用渠道
     */
    private String channelId = CHANNEL_ID;
    /***
     * 合作方
     */
    private String cooperator;
    /**
     * 终端类型
     */
    private String appTerm;
    /**
     * 调用流水
     */
    private String callId;
    
    /***
     * 唯一客户号，建议传入
     * @see BaseRequest#accountingSystem
     */
    private String uuid;

	/**
     * 指定账务系统 ,不传值默认accountingSystem=null;传值AccountingSystem枚举
     * <p>accountingSystem=null,根据Body中合同号<CONTRA_NBR,contractId,CONTR_NBR,CONTRNBR>,借据号<DUE_BILL_NO>,产品编码<LOAN_CODE>路由到正确的账务系统。
     * <p>如果BODY中没有上述3个值，默认路由到Sunline。有上述3个值，默认会从CCA取到UUID填充Header。
     * <p>注意：如果没有同号<CONTRA_NBR,contractId,CONTR_NBR,CONTRNBR>,借据号<DUE_BILL_NO>,产品编码<LOAN_CODE>;一般需要传UUID到Body或者Header，建议传入UUID。
     * <p>VCC有自己的SDK，建议直接调用VCC SDK
     * 
     */
    private AccountingSystem accountingSystem;
       
	public BaseRequest() {
    }

    public BaseRequest(String org, String channelId, String cooperator, String appTerm, String callId) {
        this.org = org;
        this.channelId = channelId;
        this.cooperator = cooperator;
        this.appTerm = appTerm;
        this.callId = callId;
    }

    public BaseRequest(String channelId, String cooperator, String appTerm, String callId) {
        this(ORG, CHANNEL_ID, cooperator, appTerm, callId);
    }

    public BaseRequest(String cooperator, String appTerm, String callId) {
        this(CHANNEL_ID, cooperator, appTerm, callId);
    }

    public String getOrg() {
        return org;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getCooperator() {
        return cooperator;
    }

    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }

    public String getAppTerm() {
        return appTerm;
    }

    public void setAppTerm(String appKey) {
        this.appTerm = appKey;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
    
    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    public AccountingSystem getAccountingSystem() {
		return accountingSystem;
	}

	public void setAccountingSystem(AccountingSystem accountingSystem) {
		this.accountingSystem = accountingSystem;
	}

}
