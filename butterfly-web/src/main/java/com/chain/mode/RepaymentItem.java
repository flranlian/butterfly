package com.chain.mode;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 还款计划
 *
 * Created by chuanlin.fan on 2017/10/13.
 */
public class RepaymentItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 期数
     */
    @JsonProperty(value = "TERM")
    private Integer term;

    /**
     * 账单日
     */
    @JsonProperty(value = "PMT_DUE_DATE")
    private String pmtDueDate;


    /**
     * 期款
     */
    @JsonProperty(value = "AMT")
    private BigDecimal amt;

    /**
     * 马上本金
     */
    @JsonProperty(value = "PRINCIPAL")
    private BigDecimal principal;

    /**
     * 马上利息
     */
    @JsonProperty(value = "INTEREST")
    private BigDecimal interest;
    /**
     * 马上杂费
     */
    @JsonProperty(value = "FEE")
    private BigDecimal fee;

    /**
     * 合作方代收服务费
     */
    @JsonProperty(value = "AGENT_FEE")
    private BigDecimal agentFee;

    /**
     * 罚息金额
     */
    @JsonProperty(value = "INTEREST_PENALTY")
    private BigDecimal interestPenalty;

    /**
     * 利息2
     */
    @JsonProperty(value = "INTEREST2")
    private BigDecimal interest2;

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getPmtDueDate() {
        return pmtDueDate;
    }

    public void setPmtDueDate(String pmtDueDate) {
        this.pmtDueDate = pmtDueDate;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(BigDecimal agentFee) {
        this.agentFee = agentFee;
    }

    public BigDecimal getInterestPenalty() {
        return interestPenalty;
    }

    public void setInterestPenalty(BigDecimal interestPenalty) {
        this.interestPenalty = interestPenalty;
    }

    public BigDecimal getInterest2() {
        return interest2;
    }

    public void setInterest2(BigDecimal interest2) {
        this.interest2 = interest2;
    }
}
