package com.xzl.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/11/20 16:03
 * @version: v0.0.1
 * @history:
 */
public class TimeUtil {

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
}
