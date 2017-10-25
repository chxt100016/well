package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Withdraw {
    private Long withdrawId;

    private Long moneyId;

    private Long userId;

    private String userName;

    private BigDecimal withdrawMoney;

    private Long bankcardId;

    private String account;

    private String accountName;

    private String bankName;

    private Byte withdrawState;

    private Date withdrawDate;

    private String withdrawIp;

    private Long mgrUserId;

    private Date mgrDate;

    private String content;

    private String mgrIp;

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Long getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(Long bankcardId) {
        this.bankcardId = bankcardId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Byte getWithdrawState() {
        return withdrawState;
    }

    public void setWithdrawState(Byte withdrawState) {
        this.withdrawState = withdrawState;
    }

    public Date getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public String getWithdrawIp() {
        return withdrawIp;
    }

    public void setWithdrawIp(String withdrawIp) {
        this.withdrawIp = withdrawIp == null ? null : withdrawIp.trim();
    }

    public Long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(Long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp == null ? null : mgrIp.trim();
    }
}