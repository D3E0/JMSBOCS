package entity;

public enum NotifyType {
    /**
     * 点对点
     */
    PEERTOPEER(0),
    /**
     * 课程范围 Pub/Sub 发布订阅模型
     */
    COURSEWIDE(1);
    private int code;

    NotifyType(int code) {
        this.code = code;
    }
}
