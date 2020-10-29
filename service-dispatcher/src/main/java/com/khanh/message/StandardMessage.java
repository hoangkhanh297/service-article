package com.khanh.message;

import io.vertx.core.json.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class StandardMessage extends BaseTraceableRequest{

    private Date time = new Date();

    private String agent;

    private String requestId = UUID.randomUUID().toString();

    private String serviceId;

    private Map<String, Object> data;


    @Override
    public String getRequestId() {
        return requestId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public StandardMessage addData(String key, Object val) {
        if (val != null) {
            if (data == null) {
                data = new HashMap<>();
            }
            data.put(key, val);
        }
        return this;
    }

    public StandardMessage addAllData(Map<String, Object> moreData) {
        if (moreData == null || moreData.isEmpty()) {
            return this;
        }
        if (data == null) {
            data = new HashMap<>();
        }
        data.putAll(moreData);
        return this;
    }

    public StandardMessage removeData(String key) {
        if (data == null) {
            data = new HashMap<>();
        }
        if (data.get(key) != null) {
            data.remove(key);
        }
        return this;
    }

    public JsonObject getDataAsJson() {
        return new JsonObject(data);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public <T> T getData(String key) {
        return data == null ? null : (T) data.get(key);
    }


    public void setData(Map<String, Object> jsonObject) {
        this.data = jsonObject;
    }

}
