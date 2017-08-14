package com.wellassist.pay;

import com.wellassist.pay.client.BankB2BPayService;
import com.wellassist.pay.client.BankB2BPayService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("webServiceClient")
public class WebServiceClient {
    public String client(String inputXml){
        BankB2BPayService_Service bankB2bPayService = new BankB2BPayService_Service();
        BankB2BPayService bankB2BPayService = bankB2bPayService.getBankB2BPayServiceImplPort();
        String res = bankB2BPayService.b2BPay(inputXml);
        return res;
    }

}
