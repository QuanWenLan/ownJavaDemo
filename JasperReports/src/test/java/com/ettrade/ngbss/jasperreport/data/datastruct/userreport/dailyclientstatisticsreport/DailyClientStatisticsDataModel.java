package com.ettrade.ngbss.jasperreport.data.datastruct.userreport.dailyclientstatisticsreport;

import java.util.Date;

/**
 * @author Vin lan
 * @className DailyClientStatisticsDataModel
 * @description TODO display data
 * @createTime 2020-09-25  17:14
 **/
public class DailyClientStatisticsDataModel {
    private Date tradeDate;
    private String currency;

    private int gtsLiteFilledOrderCnt;
    private int manualTradeCnt;
    private long manualTradeQty;

    private int filledOrderCnt;
    private long totalFilledQty;

    private int bookInTradeCnt;
    private long bookInTradeQty;
    private double totalTurnover;

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getGtsLiteFilledOrderCnt() {
        return gtsLiteFilledOrderCnt;
    }

    public void setGtsLiteFilledOrderCnt(int gtsLiteFilledOrderCnt) {
        this.gtsLiteFilledOrderCnt = gtsLiteFilledOrderCnt;
    }

    public int getManualTradeCnt() {
        return manualTradeCnt;
    }

    public void setManualTradeCnt(int manualTradeCnt) {
        this.manualTradeCnt = manualTradeCnt;
    }

    public long getManualTradeQty() {
        return manualTradeQty;
    }

    public void setManualTradeQty(long manualTradeQty) {
        this.manualTradeQty = manualTradeQty;
    }

    public int getFilledOrderCnt() {
        return filledOrderCnt;
    }

    public void setFilledOrderCnt(int filledOrderCnt) {
        this.filledOrderCnt = filledOrderCnt;
    }

    public long getTotalFilledQty() {
        return totalFilledQty;
    }

    public void setTotalFilledQty(long totalFilledQty) {
        this.totalFilledQty = totalFilledQty;
    }

    public int getBookInTradeCnt() {
        return bookInTradeCnt;
    }

    public void setBookInTradeCnt(int bookInTradeCnt) {
        this.bookInTradeCnt = bookInTradeCnt;
    }

    public long getBookInTradeQty() {
        return bookInTradeQty;
    }

    public void setBookInTradeQty(long bookInTradeQty) {
        this.bookInTradeQty = bookInTradeQty;
    }

    public double getTotalTurnover() {
        return totalTurnover;
    }

    public void setTotalTurnover(double totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    // count the ManualTradeCnt and ManualTradeQty
    public void addManualTradeCnt() {
        this.manualTradeCnt++;
    }

    public void addManualTradeQty(long fillQty) {
        this.manualTradeQty += fillQty;
    }

    // count the BookInTradeCnt and BookInTradeQty
    public void addBookInTradeCnt() {
        this.bookInTradeCnt++;
    }

    public void addBookInTradeQty(long fillQty) {
        this.bookInTradeQty += fillQty;
    }

    public void addTotalAmount(double currentAmount) {
        this.totalTurnover += currentAmount;
    }
}
