package com.chain.exception;


import com.chain.mode.SystemCode;
import com.chain.mode.doc.ServiceDoc;

/**
 * 账务系统异常类
 *  
 * @author zhaofei
 * @date 2015年12月19日 下午2:21:17
 */
public class CCSException extends RuntimeException {

	private static final long serialVersionUID = 7331092892158642226L;
	
	private ServiceDoc responseServiceDoc;
	
//	public CCSException(String errorCode, String message) {
//		super(errorCode, message, SystemCode.CCS);
//	}
//
//	public CCSException(String errorCode, String message, Throwable e) {
//		super(errorCode, message, SystemCode.CCS, e);
//	}
//
//	public CCSException(String errorCode, String message, SystemCode systemCode) {
//		super(errorCode, message, systemCode);
//	}
//
//	public CCSException(String errorCode, String message,
//						SystemCode systemCode, Throwable e) {
//		super(errorCode, message, systemCode, e);
//	}
	
	public ServiceDoc getResponseServiceDoc() {
		return responseServiceDoc;
	}

	public CCSException setResponseServiceDoc(ServiceDoc responseServiceDoc) {
		this.responseServiceDoc = responseServiceDoc;
		return this;
	}
}
