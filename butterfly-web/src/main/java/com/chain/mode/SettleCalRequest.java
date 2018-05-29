package com.chain.mode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 立即结清接口请求参数
 *
 * @author chao.su
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SettleCalRequest implements Serializable {

    private static final long serialVersionUID = 8809493790660668339L;

    /**
     * 借据号
     */
    @JsonProperty(value = "DUE_BILL_NO")
    private String dueBillNo;

    /**
     * 合同号
     */
    //@Check(lengths=18)
    @JsonProperty(value = "CONTR_NBR")
    public String contrNbr;

    /**
     * 请求类型
     * 1.提前还款试算  2.提前还款预约
     */
    @JsonProperty(value = "TYPE")
    private String type;

//    /**
//     * 还款优惠项
//     *
//     * @return
//     */
//    @JsonProperty("discountItems")
//    private List<DiscontItem> discontItems = new ArrayList<DiscontItem>();
//
//    /**
//     * 是否异步调用支付
//     */
//    @JsonProperty(value = "isAsync")
//    public PaySysCallType isAsync;


    /**
     * 还款账号
     */
    @JsonProperty("NEW_BANK_ACCT_NBR")
    private String newBankAcctNbr;

    /**
     * 约定还款账户姓名
     */
    @JsonProperty("DD_BANK_ACCT_NAME")
    private String ddBankAcctName;

    /**
     * 约定还款开户行号
     */
    @JsonProperty("DD_BANK_BRANCH")
    private String ddBankBranch;

    /**
     * 约定还款银行名称
     */
    @JsonProperty("DD_BANK_NAME")
    private String ddBankName;

    /**
     *约定还款开户行省
     */
    @JsonProperty("DD_BANK_PROVINCE")
    private String ddBankProvince;

    /**
     *约定还款开户行省code
     */
    @JsonProperty("DD_BANK_PROV_CODE")
    private String ddBankProvCode;

    /**
     *约定还款开户行市
     */
    @JsonProperty("DD_BANK_CITY")
    private String ddBankCity;

    /**
     *约定还款开户行市code
     */
    @JsonProperty("DD_BANK_CITY_CODE")
    private String ddBankCityCode;

    /**
     * GREETGO-1179 【融360原子贷】提前结清 添加 20171013
     * 总扣款金额
     *
     * @return
     */
    @JsonProperty("TOTAL_AMT")
    private BigDecimal totalAmt;

    /**
     * GREETGO-1179 【融360原子贷】提前结清 添加 20171013
     * 总本金
     *
     * @return
     */
    @JsonProperty("TOTAL_PRINCIPAL")
    private BigDecimal totalPrincipal;

    /**
     * GREETGO-1179 【融360原子贷】提前结清 添加 20171013
     * 总利息
     *
     * @return
     */
    @JsonProperty("TOTAL_INTEREST")
    private BigDecimal totalInterest;


    /**
     * GREETGO-1179 【融360原子贷】提前结清 添加 20171013
     * 总马上侧费用
     *
     * @return
     */
    @JsonProperty("TOTAL_FEE")
    private BigDecimal totalFee;


    /**
     * GREETGO-1179 【融360原子贷】提前结清 添加 20171013
     * 合作方总代收服务费
     *
     * @return
     */
    @JsonProperty("TOTAL_AGENT_FEE")
    private BigDecimal totalAgentFee;
    /**
     * 【省呗】提前结清 添加 20180321
     * 还款类型
     */
    @JsonProperty("REPAY_TYPE")
    private String repayType;

    /**
     * 还款计划列表
     *
     * @return
     */
    @JsonProperty("REPAYMENT_LIST")
    private List<RepaymentItem> repaymentList = new ArrayList<RepaymentItem>();

    public SettleCalRequest() {
    }


    public String getDueBillNo() {
        return dueBillNo;
    }

    public void setDueBillNo(String dueBillNo) {
        this.dueBillNo = dueBillNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


//    public List<DiscontItem> getDiscontItems() {
//        return discontItems;
//    }
//
//    public void setDiscontItems(List<DiscontItem> discontItems) {
//        this.discontItems = discontItems;
//    }

    public String getContrNbr() {
        return contrNbr;
    }

    public void setContrNbr(String contrNbr) {
        this.contrNbr = contrNbr;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTotalPrincipal() {
        return totalPrincipal;
    }

    public void setTotalPrincipal(BigDecimal totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalAgentFee() {
        return totalAgentFee;
    }

    public void setTotalAgentFee(BigDecimal totalAgentFee) {
        this.totalAgentFee = totalAgentFee;
    }


    public String getNewBankAcctNbr() {
        return newBankAcctNbr;
    }

    public void setNewBankAcctNbr(String newBankAcctNbr) {
        this.newBankAcctNbr = newBankAcctNbr;
    }

    public String getDdBankAcctName() {
        return ddBankAcctName;
    }

    public void setDdBankAcctName(String ddBankAcctName) {
        this.ddBankAcctName = ddBankAcctName;
    }

    public String getDdBankBranch() {
        return ddBankBranch;
    }

    public void setDdBankBranch(String ddBankBranch) {
        this.ddBankBranch = ddBankBranch;
    }

    public String getDdBankName() {
        return ddBankName;
    }

    public void setDdBankName(String ddBankName) {
        this.ddBankName = ddBankName;
    }

    public String getDdBankProvince() {
        return ddBankProvince;
    }

    public void setDdBankProvince(String ddBankProvince) {
        this.ddBankProvince = ddBankProvince;
    }

    public String getDdBankProvCode() {
        return ddBankProvCode;
    }

    public void setDdBankProvCode(String ddBankProvCode) {
        this.ddBankProvCode = ddBankProvCode;
    }

    public String getDdBankCity() {
        return ddBankCity;
    }

    public void setDdBankCity(String ddBankCity) {
        this.ddBankCity = ddBankCity;
    }

    public String getDdBankCityCode() {
        return ddBankCityCode;
    }

    public void setDdBankCityCode(String ddBankCityCode) {
        this.ddBankCityCode = ddBankCityCode;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public List<RepaymentItem> getRepaymentList() {
        return repaymentList;
    }

    public void setRepaymentList(List<RepaymentItem> repaymentList) {
        this.repaymentList = repaymentList;
    }
}
