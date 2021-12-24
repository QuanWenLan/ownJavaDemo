package com.ett.report;

import com.fasterxml.jackson.annotation.JsonFormat;
//import play.data.format.Formats;

import java.util.Date;
import java.util.List;

/**
 * @author Vin lan
 * @className RevenueForAllChannelsRequest
 * @description TODO
 * @createTime 2020-12-14  16:57
 **/
public class RevenueForAllChannelsRequest {
    private List<String> productIds;
    private List<String> storeIds;

//    @Formats.DateTime(pattern = "yyyyMMddHHmmss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date orderDateFrom;

//    @Formats.DateTime(pattern = "yyyyMMddHHmmss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date orderDateTo;

    private Integer reportType;

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<String> storeIds) {
        this.storeIds = storeIds;
    }

    public Date getOrderDateFrom() {
        return orderDateFrom;
    }

    public void setOrderDateFrom(Date orderDateFrom) {
        this.orderDateFrom = orderDateFrom;
    }

    public Date getOrderDateTo() {
        return orderDateTo;
    }

    public void setOrderDateTo(Date orderDateTo) {
        this.orderDateTo = orderDateTo;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
}
