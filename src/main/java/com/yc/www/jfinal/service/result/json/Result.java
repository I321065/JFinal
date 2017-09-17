package com.yc.www.jfinal.service.result.json;

/**
 * Created by superuser on 9/16/17.
 */
public class Result {

    private Object data;

    private int errorNum;

    private String errorMsg;

    public Result(Object data) {
        this(data, 0, null);
    }

    public Result(String errorMsg) {
        this(null, 1, errorMsg);
    }

    public Result(Object data, int errorNum, String errorMsg) {
        this.data = data;
        this.errorNum = errorNum;
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
