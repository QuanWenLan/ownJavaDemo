package com.lanwq.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Vin lan
 * @className LocalPickupAddressResponse
 * @description
 * @createTime 2021-04-29  09:10
 **/
public class LocalPickupAddressResponse extends BaseResponse {
    private String insertSql;
    private List<String> sfServicePartnerStringList;

    private List<String> missingData = Lists.newArrayList();
    private UpdateCode code = UpdateCode.Complete;
    private List<String> ids = Lists.newArrayList();

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public List<String> getSfServicePartnerStringList() {
        return sfServicePartnerStringList;
    }

    public void setSfServicePartnerStringList(List<String> sfServicePartnerStringList) {
        this.sfServicePartnerStringList = sfServicePartnerStringList;
    }

    public UpdateCode getCode() {
        return code;
    }

    public void setCode(UpdateCode code) {
        this.code = code;
    }

    public List<String> getMissingData() {
        return missingData;
    }

    public void setMissingData(List<String> missingData) {
        this.missingData = missingData;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public enum UpdateCode {
        // complete or skip a part of data that not suitable for requirement
        Complete(0),
        Error(-1),
        SkipPart(1);

        UpdateCode(int code) {
        }
    }
}
