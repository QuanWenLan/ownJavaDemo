package com.ettrade.ngbss.jasperreport.data.datastruct.common;

public class AccountSecurityStruct extends Security implements BaseStruct {
    private String accountId;
    private String currency;
    private double nominal;
    private long quantity;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        AccountSecurityStruct that = (AccountSecurityStruct) o;

        if (Double.compare(that.nominal, nominal) != 0)
            return false;
        if (quantity != that.quantity)
            return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null)
            return false;
        return currency != null ? currency.equals(that.currency) : that.currency == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        temp = Double.doubleToLongBits(nominal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }
}
