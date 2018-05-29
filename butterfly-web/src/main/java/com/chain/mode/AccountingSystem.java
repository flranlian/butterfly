package com.chain.mode;

/**
 * 账务类型
 * 
 *@author pengfei.yan 
 *@date 2017年5月20日 下午2:13:56
 *
 */

public enum AccountingSystem {

	/**
	 *	长亮
	 */
	Sunline("1"),
	/**
	 *	虚拟卡
	 */
	VCC("2"),
	/**
	 *	GreetGo
	 */
	GreetGo("3");

	
	private String accountingSystem;
	
	private AccountingSystem(String accountingSystem)
	{
		this.accountingSystem = accountingSystem;
	}
	
	public String getAccountingSystem() {
		return accountingSystem;
	}
}
