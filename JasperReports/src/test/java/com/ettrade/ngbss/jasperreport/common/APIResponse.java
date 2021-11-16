package com.ettrade.ngbss.jasperreport.common;

import java.util.HashMap;
import java.util.Map;

/**
 *@version $Id$
 */

public class APIResponse extends APIMessage {
    //    protected static boolean isDelim =
    //        "delim".equalsIgnoreCase(APISystemConfig.getInstance().getString("api.response.datatype"));
    //    protected static boolean isJson =
    //        "json".equalsIgnoreCase(APISystemConfig.getInstance().getString("api.response.datatype"));

    protected String returnCode = "";
    protected String returnMsg = "";
    protected String returnMsgTC = "";
    protected String returnMsgSC = "";
    protected Map<String, String> headers = new HashMap<String, String>();

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean hasError() {
        return !returnCode.equals("0");
    }

    public APIResponse() {
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnMsgTC() {
        return returnMsgTC;
    }

    public void setReturnMsgTC(String returnMsgTC) {
        this.returnMsgTC = returnMsgTC;
    }

    public String getReturnMsgSC() {
        return returnMsgSC;
    }

    public void setReturnMsgSC(String returnMsgSC) {
        this.returnMsgSC = returnMsgSC;
    }

    /**
     * Format the reponse string using bean attribute
     */
    public String encodeResponse(String apiVersion) throws Exception {
        /*
        if (isDelim) {
            if (hasError()) {
                return errorCode;
            }
            else {
                return MessageFormatUtils.encodeResponse(this, apiVersion);
            }
        }
        else if (isJson) {
            if (hasError()) {
                JSONErrorResponse response = retrieveErrorResponse();
                response.setErrorCode(errorCode);
                response.setErrorMessage(errorMessage);
                return MessageFormatUtils.encodeJSONResponse(response);
            }
            else {
                return MessageFormatUtils.encodeJSONResponse(this);
            }
        }
        */
        return "";

    }

    //    public JSONErrorResponse retrieveErrorResponse() {
    //        JSONErrorResponse response = new JSONErrorResponse();
    //        response.setErrorCode(errorCode);
    //        response.setErrorMessage(errorMessage);
    //        return response;
    //    }

}
