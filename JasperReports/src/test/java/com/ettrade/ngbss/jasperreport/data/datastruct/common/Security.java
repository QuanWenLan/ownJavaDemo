package com.ettrade.ngbss.jasperreport.data.datastruct.common;

public class Security {
    protected String mkt;
    protected String stock;

    public Security() {

    }

    public Security(String market, String stockCode) {
        this.mkt = market;
        this.stock = stockCode;
    }

    public String getMkt() {
        return mkt;
    }

    public void setMkt(String mkt) {
        this.mkt = mkt;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security security = (Security) o;

        if (mkt != null ? !mkt.equals(security.mkt) : security.mkt != null) return false;
        return stock != null ? stock.equals(security.stock) : security.stock == null;
    }

    @Override
    public int hashCode() {
        int result = mkt != null ? mkt.hashCode() : 0;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }
}
