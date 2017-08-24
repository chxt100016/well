package org.wella.entity;



/**
 * Created by liuwen on 2017/4/7.
 * 风险因子对象，该对象与表risk_factor表对应
 *
 */
public class RiskFactor {

    //风险因子物理id，对应表中的物理id，无实际意义
    private int id ;

    //风险因子英文名称
    private String factorName;

    //风险因子中文描述
    private String factorDesc;

    //该风险因子是否参与计算
    private boolean calculate;

    //该风险因子数据评判所对应的表
    private String resourceTable;

    //该风险因子评判所对应的列，如果该列为控或者该列不存在则取factor所对应的值。
    private String resourceColumn;

    //风险因子的类型，值类型0，字符串类型1.
    private int factorType;

    //风险因子业务编码
    private String factorCode;

    public RiskFactor() {
    }

    public int getFactorType() {
        return factorType;
    }

    public void setFactorType(int factorType) {
        this.factorType = factorType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorDesc() {
        return factorDesc;
    }

    public void setFactorDesc(String factorDesc) {
        this.factorDesc = factorDesc;
    }

    public boolean isCalculate() {
        return calculate;
    }

    public void setCalculate(boolean calculate) {
        this.calculate = calculate;
    }

    public String getResourceTable() {
        return resourceTable;
    }

    public void setResourceTable(String resourceTable) {
        this.resourceTable = resourceTable;
    }

    public String getResourceColumn() {
        return resourceColumn;
    }

    public void setResourceColumn(String resourceColumn) {
        this.resourceColumn = resourceColumn;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }
}
