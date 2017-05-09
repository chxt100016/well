package io.wellassist.entity;

import java.math.BigDecimal;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Degree {
    private long degreeId;
    private BigDecimal moneyFw;
    private BigDecimal moneyFw1;

    public long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(long degreeId) {
        this.degreeId = degreeId;
    }

    public BigDecimal getMoneyFw() {
        return moneyFw;
    }

    public void setMoneyFw(BigDecimal moneyFw) {
        this.moneyFw = moneyFw;
    }

    public BigDecimal getMoneyFw1() {
        return moneyFw1;
    }

    public void setMoneyFw1(BigDecimal moneyFw1) {
        this.moneyFw1 = moneyFw1;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Degree waDegree = (Degree) o;

        if (degreeId != waDegree.degreeId) return false;
        if (moneyFw != null ? !moneyFw.equals(waDegree.moneyFw) : waDegree.moneyFw != null) return false;
        if (moneyFw1 != null ? !moneyFw1.equals(waDegree.moneyFw1) : waDegree.moneyFw1 != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (degreeId ^ (degreeId >>> 32));
        result = 31 * result + (moneyFw != null ? moneyFw.hashCode() : 0);
        result = 31 * result + (moneyFw1 != null ? moneyFw1.hashCode() : 0);
        return result;
    }
}
