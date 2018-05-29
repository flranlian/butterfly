package com.chain.mode.doc;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by WeiZheng on 2015/9/25.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务状态
     * S代表服务成功.F代表失败。
     */
    @JsonProperty(value = "STATUS")
    private String status;

    /**
     * 处理状态
     */
    @JsonProperty(value = "CODE")
    private String code;

    /**
     * 状态描述
     */
    @JsonProperty(value = "DESC")
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ServResponse{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

