package com.yc.www.jfinal.service.result;

import com.yc.www.jfinal.service.result.ResponseError;

/**
 * Created by superuser on 9/16/17.
 */
public class ResponseResult {

    private Object data;

    private ResponseError error;

    public ResponseResult(Object data) {
        this(data, new ResponseError());
    }

    public ResponseResult(ResponseError error) {
        this.data = null;
        this.error = error;
    }

    public ResponseResult(Object data, ResponseError error) {
        this.data = data;
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }
}
