package com.lanwq.thread.dossierThread;

import java.util.List;

/**
 * @ClassName DossierData
 * @Description TODO 数据对象
 * @Author lanwenquan
 * @Date 2020/4/24 9:26
 */
public class DossierData {
    private String id;

    private String name;

    private List datas;

    private boolean requestSuccess;

    private String errorMessage;

    public DossierData(String id, String name, List datas, boolean requestSuccess, String errorMessage) {
        this.id = id;
        this.name = name;
        this.datas = datas;
        this.requestSuccess = requestSuccess;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public boolean isRequestSuccess() {
        return requestSuccess;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        this.requestSuccess = requestSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}