package com.zhou.mjava.Test ;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by IntelliJ IDEA. User: liuzz Date: 14-3-21 Time: 下午3:16
 */
class OrderIdGenerator  {

    private final String syscode;

    private final int suffixLength;

    private static final int[] TIME_INDEX = new int[]{2, 3, 5, 6, 8, 9};

    private static AtomicLong idGenerator = new AtomicLong(0);

    private static final short[][] SHUFFLE_DATA = new short[4][10];

    private static final int EMPTY_SUFFIX = -1;

    OrderIdGenerator(String syscode, int suffixLength) {
        this.syscode = syscode;
        this.suffixLength = suffixLength;
        initData();
    }

    private void initData() {
        List<Short> data = Lists.newArrayListWithExpectedSize(100);
        for (short i = 0; i < 10; i++) {
            data.add(i);
        }
        for (int i = 0; i < 4; i++) {
            Collections.shuffle(data);
            toArray(data, SHUFFLE_DATA[i]);
        }
    }

    private short[] toArray(List<Short> shortList, short[] data) {
        int len = shortList.size();
        for (int i = 0; i < len; i++) {
            data[i] = shortList.get(i);
        }
        return data;
    }

    /**
     * 2为syscode + 6为日期 + 10位数字
     *
     * @return
     */
    public long generateOrderId() {
        return generateOrderId(EMPTY_SUFFIX);
    }

    private static final int DEFAULT_RANDOM_LENGTH = 10;

    private static final int MAX_OFFSET_LENGTH = 4;

    private static final int MIN_OFFSET_LENGTH = 2;

    public long generateOrderId(int suffix) { // 假设 suffix 为 -1
        StringBuilder builder = new StringBuilder(syscode);
        String tsString = new Timestamp(System.currentTimeMillis()).toString();

        int suffixLength = suffix == EMPTY_SUFFIX ? 0 : this.suffixLength;
        // restOffsetNum = 4 位
        int restOffsetNum = suffixLength > MAX_OFFSET_LENGTH - MIN_OFFSET_LENGTH ? MIN_OFFSET_LENGTH : MAX_OFFSET_LENGTH - suffixLength;
        int randomLength; // 随机位数
        if (suffixLength < 4) {
            for (int index : TIME_INDEX) {
                builder.append(tsString.charAt(index)); // 拼 6 位年月日
            }
            randomLength = DEFAULT_RANDOM_LENGTH - suffixLength; // 10 位
        } else {
            randomLength = DEFAULT_RANDOM_LENGTH + TIME_INDEX.length - suffixLength;
        }
        int timeLength = randomLength - restOffsetNum; // 6 位

        String ts = String.valueOf(System.currentTimeMillis()); // 时间戳为 13 位
        // suffix 先占用随机数的位置，再占用ts的位置，随机数最多占用2位，ts最多占用4位(已在上层规定了suffixLength <= 6)
        for (int i = ts.length() - timeLength; i < ts.length(); i++) { // 6 位
            builder.append(ts.charAt(i)); // 拼 6 位当前时间戳
        }

        return Long.valueOf(builder
                .append(getRestOffset(restOffsetNum, Integer.parseInt(ts.charAt(ts.length() - 1) + "")))
                .append(OngUtil.fixSize(suffix, suffixLength)).toString());
    }

    public int getSuffix(long orderId) {
        throw new UnsupportedOperationException();
    }

    private String getRestOffset(int offsetNum, int delta) {
        StringBuilder builder = new StringBuilder();
        long index = idGenerator.getAndIncrement() + delta;
        for (int i = 0; i < offsetNum; i++) {
            builder.append(SHUFFLE_DATA[i][(int) (index % 10)]);
            index = index / 10;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        OrderIdGenerator generator = new OrderIdGenerator("10", 5);
        Set<Long> set = Sets.newHashSet();
        for (int i = 0; i < 5; i++) {
            long l = generator.generateOrderId(5);
             System.out.println(l);
        }
    }
}

