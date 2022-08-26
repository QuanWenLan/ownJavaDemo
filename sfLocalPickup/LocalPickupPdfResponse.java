package com.lanwq.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className LocalPickupPdfResponse
 * @description
 * @createTime 2022-05-18  10:57
 **/
public class LocalPickupPdfResponse extends LocalPickupAddressResponse {
    private String pickupType;
    private List<String> pdfUrls = new ArrayList<>();
    private List<String> aTexts = new ArrayList<>();
    private String typeName;
    private List<String> errorPdf = new ArrayList<>();


    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }

    public List<String> getPdfUrls() {
        return pdfUrls;
    }

    public void setPdfUrls(List<String> pdfUrls) {
        this.pdfUrls = pdfUrls;
    }

    public List<String> getaTexts() {
        return aTexts;
    }

    public void setaTexts(List<String> aTexts) {
        this.aTexts = aTexts;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getErrorPdf() {
        return errorPdf;
    }

    public void setErrorPdf(List<String> errorPdf) {
        this.errorPdf = errorPdf;
    }
}
