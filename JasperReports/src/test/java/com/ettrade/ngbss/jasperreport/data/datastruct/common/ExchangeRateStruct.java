package com.ettrade.ngbss.jasperreport.data.datastruct.common;

public class ExchangeRateStruct extends Security implements BaseStruct {
    private String srcCurrency;
    private String srcWithCents;
    private String targetCurrency;
    private String targetWithCents;
    private double rate;
    private String calculationMethod;
    private double conversionUnit;

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(String srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    public String getSrcWithCents() {
        return srcWithCents;
    }

    public void setSrcWithCents(String srcWithCents) {
        this.srcWithCents = srcWithCents;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getTargetWithCents() {
        return targetWithCents;
    }

    public void setTargetWithCents(String targetWithCents) {
        this.targetWithCents = targetWithCents;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public double getConversionUnit() {
        return conversionUnit;
    }

    public void setConversionUnit(double conversionUnit) {
        this.conversionUnit = conversionUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        ExchangeRateStruct that = (ExchangeRateStruct) o;

        if (Double.compare(that.rate, rate) != 0)
            return false;
        if (Double.compare(that.conversionUnit, conversionUnit) != 0)
            return false;
        if (srcCurrency != null ? !srcCurrency.equals(that.srcCurrency) : that.srcCurrency != null)
            return false;
        if (srcWithCents != null ? !srcWithCents.equals(that.srcWithCents) : that.srcWithCents != null)
            return false;
        if (targetCurrency != null ? !targetCurrency.equals(that.targetCurrency) : that.targetCurrency != null)
            return false;
        if (targetWithCents != null ? !targetWithCents.equals(that.targetWithCents) : that.targetWithCents != null)
            return false;
        return calculationMethod != null ?
            calculationMethod.equals(that.calculationMethod) :
            that.calculationMethod == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (srcCurrency != null ? srcCurrency.hashCode() : 0);
        result = 31 * result + (srcWithCents != null ? srcWithCents.hashCode() : 0);
        result = 31 * result + (targetCurrency != null ? targetCurrency.hashCode() : 0);
        result = 31 * result + (targetWithCents != null ? targetWithCents.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (calculationMethod != null ? calculationMethod.hashCode() : 0);
        temp = Double.doubleToLongBits(conversionUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
