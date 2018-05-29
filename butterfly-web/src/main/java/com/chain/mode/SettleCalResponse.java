package com.chain.mode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 立即结清返回结果
 * <p>
 * DUE_BILL_NO	借据号	VARCHAR2(32)
 * AMOUNT	总金额	VARCHAR2(32)
 * PREMIUM	保费	VARCHAR2(32)
 * POUNDAGE	手续费	VARCHAR2(32)
 * INTEREST	利息	VARCHAR2(32)
 * PRINCIPAL	本金	VARCHAR2(32)
 *
 * @author chao.su
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SettleCalResponse implements Serializable {

    private static final long serialVersionUID = 991719442915994647L;

    /**
     * 借据号
     */
    @JsonProperty(value = "DUE_BILL_NO")
    private String dueBillNo;

    /**
     * 总金额
     */
    @JsonProperty(value = "AMOUNT")
    private BigDecimal amount;

    /**
     * 逾期金额
     */
    @JsonProperty(value = "DUEAMOUNT")
    private String dueAmount;

    /**
     * 保费
     */
    @JsonProperty(value = "PREMIUM")
    private BigDecimal premium;

    /**
     * 手续费
     */
    @JsonProperty(value = "POUNDAGE")
    private BigDecimal poundage;

    /**
     * 利息
     */
    @JsonProperty(value = "INTEREST")
    private BigDecimal interest;

    /**
     * 本金
     */
    @JsonProperty(value = "PRINCIPAL")
    private BigDecimal principal;

    /**
     * 寿险计划包
     */
    @JsonProperty(value = "LIFEINSU")
    private String lifeInsu;
    /**
     * 碎屏险计划包
     */
    @JsonProperty(value = "SCREENINSU")
    private String screenInsu;

    /**
     * 碎屏险居间服务费计划包
     */
    @JsonProperty(value = "SCREENSERV")
    private String screenServ;

    /**
     * 订单ID
     */
    @JsonProperty(value = "ORDER_ID")
    private String orderId;

    /**
     * 外部流水号
     */
    @JsonProperty(value = "SERVICE_SN")
    private String serviceSn;

    /**
     * 代收服务费
     */
    @JsonProperty(value = "REPLACESVC")
    private BigDecimal replaceSvc;

    /**
     * 雪球会员费
     */
    @JsonProperty(value = "SNOWBALL_VIP_FEE")
    private BigDecimal snowBallVipFee;

    /**
     * 雪球会员退会违约金
     */
    @JsonProperty(value = "SNOWBALL_PUNISH_FEE")
    private BigDecimal snowBallPunishFee;

    /**
     * 印花税
     */
    @JsonProperty(value="STAMP")
    private String stamp;

    /**
     * 贷款服务费
     */
    @JsonProperty(value="LOANTERMFEE")
    private String loanTermFee;

    /**
     * 趸交费
     */
    @JsonProperty(value="PREMIUMAMT")
    private String premiumAmt;

    /**
     * 未匹配金额
     */
    @JsonProperty(value="MEMOAMT")
    private String memoAmt;

    /**
     * 代收提前还款手续费
     */
    @JsonProperty(value="REPLACEPREPAYFEE")
    private String replacePrepayFee;

    /**
     * 代收罚金
     */
    @JsonProperty(value="REPLACEMULCT")
    private String replaceMulct;

    /**
     * 代收罚息
     */
    @JsonProperty(value="REPLACEPENALTY")
    private String replacePenalty;

    /**
     * 溢缴款
     */
    @JsonProperty(value="DEPOSIT")
    private String deposit;

    /**
     * 代收滞纳金
     */
    @JsonProperty(value="REPLACELPC")
    private String replaceLpc;

    /**
     * 提前还款计划包费
     * @return
     */
    @JsonProperty(value="PREPAYPKG")
    private String prepayPkg;

    /**
     * 分期手续费
     */
    @JsonProperty(value="LOANTERMSVC")
    private String loanTermSvc;

    /**
     * 利息2
     */
    @JsonProperty(value="INTEREST2")
    private BigDecimal interest2;

    public BigDecimal getSnowBallVipFee() {
        return snowBallVipFee;
    }

    public void setSnowBallVipFee(BigDecimal snowBallVipFee) {
        this.snowBallVipFee = snowBallVipFee;
    }

    public BigDecimal getSnowBallPunishFee() {
        return snowBallPunishFee;
    }

    public void setSnowBallPunishFee(BigDecimal snowBallPunishFee) {
        this.snowBallPunishFee = snowBallPunishFee;
    }

    public String getDueBillNo() {
        return dueBillNo;
    }

    public void setDueBillNo(String dueBillNo) {
        this.dueBillNo = dueBillNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public String getLifeInsu() {
        return lifeInsu;
    }

    public void setLifeInsu(String lifeInsu) {
        this.lifeInsu = lifeInsu;
    }

    public String getScreenInsu() {
        return screenInsu;
    }

    public void setScreenInsu(String screenInsu) {
        this.screenInsu = screenInsu;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceSn() {
        return serviceSn;
    }

    public void setServiceSn(String serviceSn) {
        this.serviceSn = serviceSn;
    }

    public String getScreenServ() {
        return screenServ;
    }

    public void setScreenServ(String screenServ) {
        this.screenServ = screenServ;
    }

    public void setReplaceSvc(BigDecimal replaceSvc) {
        this.replaceSvc = replaceSvc;
    }

    public BigDecimal getReplaceSvc() {
        return replaceSvc;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getLoanTermFee() {
        return loanTermFee;
    }

    public void setLoanTermFee(String loanTermFee) {
        this.loanTermFee = loanTermFee;
    }

    public String getPremiumAmt() {
        return premiumAmt;
    }

    public void setPremiumAmt(String premiumAmt) {
        this.premiumAmt = premiumAmt;
    }

    public String getMemoAmt() {
        return memoAmt;
    }

    public void setMemoAmt(String memoAmt) {
        this.memoAmt = memoAmt;
    }

    public String getReplacePrepayFee() {
        return replacePrepayFee;
    }

    public void setReplacePrepayFee(String replacePrepayFee) {
        this.replacePrepayFee = replacePrepayFee;
    }

    public String getReplaceMulct() {
        return replaceMulct;
    }

    public void setReplaceMulct(String replaceMulct) {
        this.replaceMulct = replaceMulct;
    }

    public String getReplacePenalty() {
        return replacePenalty;
    }

    public void setReplacePenalty(String replacePenalty) {
        this.replacePenalty = replacePenalty;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getReplaceLpc() {
        return replaceLpc;
    }

    public void setReplaceLpc(String replaceLpc) {
        this.replaceLpc = replaceLpc;
    }

    public String getPrepayPkg() {
        return prepayPkg;
    }

    public void setPrepayPkg(String prepayPkg) {
        this.prepayPkg = prepayPkg;
    }

    public String getLoanTermSvc() {
        return loanTermSvc;
    }

    public void setLoanTermSvc(String loanTermSvc) {
        this.loanTermSvc = loanTermSvc;
    }

    public BigDecimal getInterest2() {
        return interest2;
    }

    public void setInterest2(BigDecimal interest2) {
        this.interest2 = interest2;
    }
}
