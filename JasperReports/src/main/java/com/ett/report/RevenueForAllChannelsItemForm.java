package com.ett.report;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Vin lan
 * @className RevenueForAllChannelsReportModel
 * @description TODO
 * @createTime 2020-12-14  16:03
 **/
public class RevenueForAllChannelsItemForm {
    private String orderId;
    private Date orderDate;
    private String nameEN;
    private String size;
    private BigDecimal totalQty;
    private BigDecimal listPrice;
    private BigDecimal subTotalAmt;
    private BigDecimal excludeTotalQty;
    private BigDecimal excludeListPrice;
    private BigDecimal excludeSubTotalAmt;

    private String productId;
    private String productNameEN;
    private BigDecimal memberPrice;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getSubTotalAmt() {
        return subTotalAmt;
    }

    public void setSubTotalAmt(BigDecimal subTotalAmt) {
        this.subTotalAmt = subTotalAmt;
    }

    public BigDecimal getExcludeTotalQty() {
        return excludeTotalQty;
    }

    public void setExcludeTotalQty(BigDecimal excludeTotalQty) {
        this.excludeTotalQty = excludeTotalQty;
    }

    public BigDecimal getExcludeListPrice() {
        return excludeListPrice;
    }

    public void setExcludeListPrice(BigDecimal excludeListPrice) {
        this.excludeListPrice = excludeListPrice;
    }

    public BigDecimal getExcludeSubTotalAmt() {
        return excludeSubTotalAmt;
    }

    public void setExcludeSubTotalAmt(BigDecimal excludeSubTotalAmt) {
        this.excludeSubTotalAmt = excludeSubTotalAmt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String productNameEN) {
        this.productNameEN = productNameEN;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    @Override
    public String toString() {
        return "RevenueForAllChannelsForm{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", nameEN='" + nameEN + '\'' +
                ", size='" + size + '\'' +
                ", totalQty=" + totalQty +
                ", listPrice=" + listPrice +
                ", subTotalAmt=" + subTotalAmt +
                ", excludeTotalQty=" + excludeTotalQty +
                ", excludeListPrice=" + excludeListPrice +
                ", excludeSubTotalAmt=" + excludeSubTotalAmt +
                ", productId='" + productId + '\'' +
                ", productNameEN='" + productNameEN + '\'' +
                ", memberPrice=" + memberPrice +
                '}';
    }
}
