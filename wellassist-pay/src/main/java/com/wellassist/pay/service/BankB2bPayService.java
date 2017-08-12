package com.wellassist.pay.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BankB2bPayService {
    @WebMethod
    public String b2bPay(String input);
}
