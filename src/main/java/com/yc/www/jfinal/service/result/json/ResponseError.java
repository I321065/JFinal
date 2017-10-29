package com.yc.www.jfinal.service.result.json;

/**
 * Created by superuser on 10/29/17.
 */
public class ResponseError {

    private int errorNum;

    private String errorMsg;

    public ResponseError() {

    }

    public ResponseError(String errorMsg) {
        this.errorNum = 1;
        this.errorMsg = errorMsg;

    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
