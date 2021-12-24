package com.ett.report;

import java.util.List;

/**
 * @author Vin lan
 * @className RevenueForAllChannelsForm
 * @description TODO
 * @createTime 2020-12-14  16:03
 **/
public class RevenueForAllChannelsForm {
    private List<RevenueForAllChannelsItemForm> productList;
    private List<RevenueForAllChannelsItemForm> discountList;
    private List<RevenueForAllChannelsItemForm> bonusList;
    private List<RevenueForAllChannelsItemForm> miscList;

    public List<RevenueForAllChannelsItemForm> getProductList() {
        return productList;
    }

    public void setProductList(List<RevenueForAllChannelsItemForm> productList) {
        this.productList = productList;
    }

    public List<RevenueForAllChannelsItemForm> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<RevenueForAllChannelsItemForm> discountList) {
        this.discountList = discountList;
    }

    public List<RevenueForAllChannelsItemForm> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<RevenueForAllChannelsItemForm> bonusList) {
        this.bonusList = bonusList;
    }

    public List<RevenueForAllChannelsItemForm> getMiscList() {
        return miscList;
    }

    public void setMiscList(List<RevenueForAllChannelsItemForm> miscList) {
        this.miscList = miscList;
    }
}
