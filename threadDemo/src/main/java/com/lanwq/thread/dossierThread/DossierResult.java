package com.lanwq.thread.dossierThread;

import java.util.List;

/**
 * @ClassName DossierResult
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/24 10:37
 */
public class DossierResult {
    private String id;

    private String name;

    private List datas;

    private boolean requestSuccess;

    private String errorMessage;

    public DossierResult(String id, String name, List datas, boolean requestSuccess, String errorMessage) {
        this.id = id;
        this.name = name;
        this.datas = datas;
        this.requestSuccess = requestSuccess;
        this.errorMessage = errorMessage;
    }

    public DossierResult() {
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

    @Override
    public String toString() {
        return "DossierResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", datas=" + datas +
                ", requestSuccess=" + requestSuccess +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}