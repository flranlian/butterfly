package com.chain.com.chain.service.com.chain.service.impl;



import com.chain.mode.doc.ServiceBody;
import com.chain.mode.doc.ServiceDoc;
import com.chain.mode.doc.ServiceHeader;
import com.chain.mode.doc.ServiceInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuhz on 15/12/5.
 */
public class DocBuilder {
   private static final String SDF="yyyyMMddHHmmss";
   private static final String V01 = "01";


   public static <T> ServiceDoc<T> build(String serviceId, BaseRequest baseRequest, T request){
       ServiceHeader serviceHeader = new ServiceHeader();
       serviceHeader.setChannelId(baseRequest.getChannelId());
       serviceHeader.setOrg(baseRequest.getOrg());
       serviceHeader.setServiceId(serviceId);
       serviceHeader.setAcqId(baseRequest.getCooperator());
       serviceHeader.setServiceSn(baseRequest.getCallId());
       serviceHeader.setSubTerminalType(baseRequest.getAppTerm());
       serviceHeader.setVersionId(V01);
       serviceHeader.setRequstTime(new SimpleDateFormat(SDF).format(new Date()));
       if(baseRequest.getUuid() != null) {
    	   serviceHeader.setUuid(baseRequest.getUuid());
       }

       ServiceBody<T> serviceBody = new ServiceBody<T>();
       serviceBody.setRequest(request);

       ServiceInfo<T> serviceInfo = new ServiceInfo<T>();
       serviceInfo.setServiceHeader(serviceHeader);
       serviceInfo.setServiceBody(serviceBody);

       ServiceDoc<T> serviceDoc = new ServiceDoc<T>();
       serviceDoc.setServiceInfo(serviceInfo);

       return serviceDoc;
   }
}
