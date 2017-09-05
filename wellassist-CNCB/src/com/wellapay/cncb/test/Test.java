package com.wellapay.cncb.test;

import com.wellapay.cncb.model.input.BalanceQuery;
import com.wellapay.cncb.model.input.InitAccount;
import com.wellapay.cncb.util.CNCBPayService;
import com.wellapay.cncb.util.StringUtil;


public class Test {
	public static void main(String[] args) throws Exception{
		//testInitAccount();
		//balanceQuery();
		test();
	}

	public static void testInitAccount() throws Exception {
		CNCBPayService service = new CNCBPayService();
		InitAccount initAccountInput=new InitAccount();
		initAccountInput.setAction("DLFNDINI");
		initAccountInput.setUserName("wzzl");
		initAccountInput.setClientID("1");
		initAccountInput.setAccountNo("123456");
		initAccountInput.setSubAccNo("1234567");
		initAccountInput.setSubAccNm("dingjianwen");
		initAccountInput.setTranAmt("0");
		service.initAccount(initAccountInput);
	}

	public static void balanceQuery() throws Exception {
		CNCBPayService service=new CNCBPayService();
		BalanceQuery bq=new BalanceQuery();
		bq.setAction("DLSBALQR");
		bq.setUserName("wzzl");
		bq.setAccountNo("8110701013700026166");
		bq.setSubAccNo("3110710017041063981");
		service.balanceQuery(bq);
	}

	public static void test() throws Exception {
		CNCBPayService service=new CNCBPayService();
		service.sendStr("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
				"<stream>\n" +
				"\t<action>DLCIDSTT</action>\n" +
				"\t<userName>wzzl</userName>\n" +
				"\t<clientID>201709051106356463</clientID>\n" +
				"\t<type></type>\n" +
				"</stream>");
	}
	
}
