package org.wella.entity;



/**
 * Created by liuwen on 2017/4/7.
 * 风险分值实体，包含风险分值，风险项，风险因子，等信息
 * 该实体与RiskFactor相对应
 */
public class RiskScore {
    //物理id
    private int id;
    //风险因子编码
    private String riskCode;
    //风险因子子项
    private String riskItem;
    //风险因子子项所对应的分值
    private double score;

    public RiskScore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskItem() {
        return riskItem;
    }

    public void setRiskItem(String riskItem) {
        this.riskItem = riskItem;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
