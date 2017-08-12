package com.wellassist.pay.service.impl;

import com.wellassist.pay.service.BankB2bPayService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(serviceName = "bankB2bPayService", endpointInterface = "com.wellassist.pay.service.BankB2bPayService")
public class BankB2bPayServiceImpl implements BankB2bPayService {
    @Override
    public String b2bPay(String input) {
        return "Hello," + input;
    }
}
