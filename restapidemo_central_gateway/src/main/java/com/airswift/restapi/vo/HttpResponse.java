package com.airswift.restapi.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * In response to the results
 */
public class HttpResponse {

    private JSONObject jsonObject;
    private Integer httpStatus;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
