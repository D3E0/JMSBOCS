package util;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.HashMap;
import java.util.Map;

public class RestResult {

    private int code;
    private String message;
    private Long count;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    private RestResult(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
        this.count = builder.count;
    }

    public static class Builder {
        private int code;
        private String message;
        private Long count;
        private Object data = null;

        public Builder() {
        }

        public Builder(int code) {
            this.code = code;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public Builder add(String key, Object value) {
            if (data == null) {
                data = new JSONObject();
            }
            if (data instanceof JSONObject) {
                ((JSONObject) data).put(key, value);
            }
            return this;
        }

        public Builder success(Object data) {
            this.code = 200;
            this.message = "success";
            this.data = data;
            return this;
        }


        public RestResult build() {
            return new RestResult(this);
        }
    }

}
