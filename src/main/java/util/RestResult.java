package util;

public class RestResult {

    private int code;
    private String message;
    private int count;
    private Object data;

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
        private int count;
        private Object data = null;

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

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public RestResult build() {
            return new RestResult(this);
        }
    }

}
