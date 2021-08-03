package com.ettrade.ngbss.jasperreport.data.datastruct.userreport.dailyclientstatisticsreport;

import java.sql.Timestamp;

/**
 * @author Vin lan
 * @className DailyClientStaticData
 * @description TODO table data
 * @createTime 2020-09-25  12:15
 **/
public class DailyClientStatisticsData {
    private String exchangeId;
    private String executionId;
    private String matchType;
    private String origOrderId;
    private Timestamp fillTime;
    private Timestamp formatFillTime;
    private String currency;
    private String tradeCancelFlag;
    private String bookInFlag;
    private double fillPrice;
    private long fillQty;
    private double currentAmount;
    private String channel;
    private String orderStatus;
    private long cumulativeFillQty;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getOrigOrderId() {
        return origOrderId;
    }

    public void setOrigOrderId(String origOrderId) {
        this.origOrderId = origOrderId;
    }

    public Timestamp getFillTime() {
        return fillTime;
    }

    public void setFillTime(Timestamp fillTime) {
        this.fillTime = fillTime;
    }

    public Timestamp getFormatFillTime() {
        return formatFillTime;
    }

    public void setFormatFillTime(Timestamp formatFillTime) {
        this.formatFillTime = formatFillTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTradeCancelFlag() {
        return tradeCancelFlag;
    }

    public void setTradeCancelFlag(String tradeCancelFlag) {
        this.tradeCancelFlag = tradeCancelFlag;
    }

    public String getBookInFlag() {
        return bookInFlag;
    }

    public void setBookInFlag(String bookInFlag) {
        this.bookInFlag = bookInFlag;
    }

    public double getFillPrice() {
        return fillPrice;
    }

    public void setFillPrice(double fillPrice) {
        this.fillPrice = fillPrice;
    }

    public long getFillQty() {
        return fillQty;
    }

    public void setFillQty(long fillQty) {
        this.fillQty = fillQty;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getCumulativeFillQty() {
        return cumulativeFillQty;
    }

    public void setCumulativeFillQty(long cumulativeFillQty) {
        this.cumulativeFillQty = cumulativeFillQty;
    }
}
