package com.chain.controller;

import com.chain.DateUtils;
import com.chain.com.chain.service.com.chain.service.impl.DocBuilder;
import com.chain.exception.CCSException;
import com.chain.mode.AccountingSystem;
import com.chain.mode.BaseRequest;
import com.chain.mode.SettleCalRequest;
import com.chain.mode.SettleCalResponse;
import com.chain.mode.doc.ServResponse;
import com.chain.mode.doc.ServiceDoc;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

/**
 * Created by lian.ran on 2018/2/14.
 */
@RestController
public class TestController {
    /**
     * 提前还款试算/签约
     */
    private static final String TNMLSettle = "TNMLSettle";
    int connectTimeout =500;
    int readTimeout =10000;
    String host = "http://10.16.30.112:8081/gcory/d/outService/enterPoint";
    RestTemplate restTemplate;
    @RequestMapping(value = "/test",produces = "text/plain;charset=UTF-8")
    public String index() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
       // RestTemplate restTemplate = new RestTemplate(factory);
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setOrg("000000000001");
        baseRequest.setChannelId("BANK");
        baseRequest.setCooperator("20000001");
        baseRequest.setAppTerm("APP");
        baseRequest.setCallId("PEC" + System.currentTimeMillis());
        SettleCalRequest request =new SettleCalRequest();
        request.setContrNbr("231026122310000134");
        request.setType("1");
        //restTemplate.postForEntity("",null,String.class);
        SettleCalResponse response =  settleCal(baseRequest,request);

        String time =  DateUtils.getDataToStr(new Date());
        System.out.println("名称:"+time);
        return "Hello World"+response.toString();
    }

    public SettleCalResponse settleCal(BaseRequest baseRequest, SettleCalRequest request) throws CCSException {
        ServiceDoc<SettleCalRequest> doc = DocBuilder.build(TNMLSettle, baseRequest, request);
        SettleCalResponse response = execute(doc, HttpMethod.POST,
                HeaderBuilder.newBuilder().setAccountingSystem(baseRequest.getAccountingSystem()).setDueBillNo(request.getDueBillNo()).build(),
                new TypeReference<ServiceDoc<SettleCalResponse>>() {
                });
        return response;
    }

    private <T> T execute(ServiceDoc<?> doc,
                          HttpMethod method, HttpHeaders headers,
                          TypeReference<ServiceDoc<T>> reference) throws CCSException {
        // 三个Header: 产品代码、合同号、借据号 [LOAN_CODE，CONTR_NBR，DUE_BILL_NO ]
        return doExecute(this.host, doc, method, headers, reference);
    }

    /**
     * 提交申请成功标识
     */
    public static final String RESPONSE_CODE_SUCCESS = "0000";
    /**
     * 放款处理中
     */
    public static final String RESPONSE_CODE_PROCESSING = "1001";
    /**
     * 人工放款
     */
    public static final String RESPONSE_CODE_LONEING = "1055";

    private <T> T doExecute(String host, ServiceDoc<?> doc,
                            HttpMethod method, HttpHeaders headers,
                            TypeReference<ServiceDoc<T>> reference) throws CCSException {
        String serviceId = doc.getServiceInfo().getServiceHeader().getServiceId();
        String serviceNo = doc.getServiceInfo().getServiceHeader().getServiceSn();
        //header添加UUID
        String uuid = doc.getServiceInfo().getServiceHeader().getUuid();
        if (org.springframework.util.StringUtils.hasLength(uuid)) {
            headers.add("UUID", uuid);
        }
        Gson gson = new Gson();
        String requestStr = gson.toJson(doc);
        ResponseEntity<String> entity = null;
        long callStart = System.currentTimeMillis();
        try {
            entity = restTemplate.exchange(host, method, new HttpEntity<String>(requestStr, headers), String.class);
            System.out.println("==>CCS_TIME@{}, host:{} serviceId:{} serviceNo:{}");
        } catch (Exception e) {
            System.out.println(e);
        }
        String resultStr = entity.getBody();
        ServiceDoc<T> resultServiceDoc =    gson.fromJson(resultStr,ServiceDoc.class);
        if (resultServiceDoc != null) {
            ServResponse servResponse = resultServiceDoc.getServiceInfo().getServiceHeader().getServResponse();
            if (servResponse != null) {
                if (RESPONSE_CODE_SUCCESS.equals(servResponse.getCode())) {
                    return resultServiceDoc.getServiceInfo().getServiceBody().getResponse();
                }
               // log.info("==>CCS_ERR@{}:[{}]", host, serviceId);
               // throw new CCSException(servResponse.getCode(), servResponse.getDesc()).setResponseServiceDoc(resultServiceDoc);
            }
        }
    return null;
       // log.error("==>CCS_ERR@{}:[{}]", host, serviceId);
      //  throw new CCSException("-1", entity.getStatusCode().toString() + "-未知异常");
    }

    /**
     * 增加三个折分的nginx所需要的三个 header.
     */
    private static class HeaderBuilder {
        // 三个Header: 产品代码、合同号、借据号 [LOAN_CODE，CONTR_NBR，DUE_BILL_NO ]
        private static HeaderBuilder newBuilder() {
            return new HeaderBuilder();
        }

        final HttpHeaders build() {
            HttpHeaders result = this.headers;
            this.headers = null;
            return result;
        }

        public HeaderBuilder setLoanCode(String loanCode) {
            if (!isEmpty(loanCode))
                this.headers.add("LOAN_CODE", loanCode);
            return this;
        }

        public HeaderBuilder setDueBillNo(String dueBillNo) {
            if (!isEmpty(dueBillNo))
                this.headers.add("DUE_BILL_NO", dueBillNo);
            return this;
        }

        public HeaderBuilder setContrNbr(String contrNbr) {
            if (!isEmpty(contrNbr))
                this.headers.add("CONTR_NBR", contrNbr);
            return this;
        }

        public HeaderBuilder setOrderId(String orderId) {
            this.headers.add("ORDER_ID", orderId);
            return this;
        }

        public HeaderBuilder setAccountingSystem(AccountingSystem accountingSystem) {
            if (accountingSystem != null) {
                this.headers.add("channel_id", accountingSystem.getAccountingSystem());
            }
            return this;
        }

        private HeaderBuilder() {
            this.headers = new HttpHeaders();
        }

        private HttpHeaders headers = null;

        private static boolean isEmpty(String v) {
            return (v == null || v.length() == 0);
        }
    }
}
