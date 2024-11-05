package com.example.javaknowledge2.test;

public class SnowflakeIdGenerator {

    // 定义雪花 ID 的各部分位数
    private static final long TIMESTAMP_BITS = 41L;
    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;

    // 定义起始时间戳（可根据实际情况调整）
    private static final long EPOCH = 1609459200000L;

    // 定义最大取值范围
    private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    // 定义偏移量
    private static final long TIMESTAMP_SHIFT = NODE_ID_BITS + SEQUENCE_BITS;
    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;

    private final long nodeId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public SnowflakeIdGenerator(long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("Invalid node ID");
        }
        this.nodeId = nodeId;
    }

    public synchronized long generateId() {
        long currentTimestamp = timestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards");
        }
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = untilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;
        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT) |
                (nodeId << NODE_ID_SHIFT) |
                sequence;
    }

    private long timestamp() {
        return System.currentTimeMillis();
    }

    private long untilNextMillis(long lastTimestamp) {
        long currentTimestamp = timestamp();
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
