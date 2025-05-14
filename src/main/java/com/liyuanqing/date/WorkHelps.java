package com.liyuanqing.date;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WorkHelps {

    static class TimeRange {
        private final LocalTime start;
        private final LocalTime end;

        public TimeRange(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }

        public LocalTime getStart() {
            return start;
        }

        public LocalTime getEnd() {
            return end;
        }
    }


    public static void main(String[] args) {

        LocalDateTime startDateTime = LocalDateTime.of(2025, 4, 21, 8, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 4, 21, 20, 0);
        // 定义常规工作时间段和加班时间段
        List<TimeRange> regularRanges = new ArrayList<>();
        regularRanges.add(new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        regularRanges.add(new TimeRange(LocalTime.of(13, 0), LocalTime.of(18, 0)));

        List<TimeRange> overtimeRanges = new ArrayList<>();
        overtimeRanges.add(new TimeRange(LocalTime.of(19, 0), LocalTime.of(23, 59)));
        overtimeRanges.add(new TimeRange(LocalTime.of(0, 0), LocalTime.of(6, 0)));

        long regularHours = calculateWorkHours(startDateTime, endDateTime, regularRanges);
        long overtimeHours = calculateWorkHours(startDateTime, endDateTime, overtimeRanges);

        System.out.println("常规工作时间（小时）：" + regularHours / 60.0);
        System.out.println("加班时间（小时）：" + overtimeHours / 60.0);


        LocalDateTime startDateTime1 = LocalDateTime.of(2025, 4, 21, 8, 30);
        LocalDateTime endDateTime1 = LocalDateTime.of(2025, 4, 21, 20, 0);
        try {
            validate(startDateTime1, endDateTime1);
            System.out.println("校验通过");
        } catch (DateTimeException e) {
            System.out.println("校验失败: " + e.getMessage());
        }


        long minutes = 89; // 示例分钟数

        // 将分钟转换为小时
        BigDecimal hours = convertMinutesToHours(minutes);
        System.out.println("小时数（保留一位小数）：" + hours);


    }

    private static long calculateWorkHours(LocalDateTime start, LocalDateTime end, List<TimeRange> timeRanges) {
        long totalMinutes = 0;

        for (TimeRange range : timeRanges) {
            LocalTime rangeStart = range.getStart();
            LocalTime rangeEnd = range.getEnd();

            // 将时间段转换为当天的时间
            LocalDateTime currentStart = LocalDateTime.of(start.toLocalDate(), rangeStart);
            LocalDateTime currentEnd = LocalDateTime.of(start.toLocalDate(), rangeEnd);

            // 计算时间段与查询区间的重叠部分
            LocalDateTime overlapStart = start.isBefore(currentStart) ? currentStart : start;
            LocalDateTime overlapEnd = end.isAfter(currentEnd) ? currentEnd : end;

            // 如果开始时间早于结束时间，则计算这段时间内的分钟数并累加
            if (!overlapStart.isAfter(overlapEnd)) {
                totalMinutes += ChronoUnit.MINUTES.between(overlapStart, overlapEnd);
            }
        }

        return totalMinutes;
    }


    /**
     * 校验开始时间和结束时间
     *
     * @param start 开始时间
     * @param end   结束时间
     * @throws DateTimeException 如果校验失败
     */
    public static void validate(LocalDateTime start, LocalDateTime end) {
        // 校验是否为同一天
        if (start.toLocalDate().compareTo(end.toLocalDate()) != 0) {
            throw new DateTimeException("开始时间和结束时间必须在同一天");
        }

        // 校验结束时间是否大于开始时间
        if (start.until(end, ChronoUnit.SECONDS) <= 0) {
            throw new DateTimeException("结束时间必须大于开始时间");
        }
    }


    public static BigDecimal convertMinutesToHours(long minutes) {
        // 将分钟转换为小时，除以60
        BigDecimal hours = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 1, RoundingMode.DOWN);

        // 检查小数部分是否大于等于5
        int decimalPart = hours.multiply(BigDecimal.valueOf(10)).remainder(BigDecimal.valueOf(10)).intValue();

        if (decimalPart >= 5) {
            hours = BigDecimal.valueOf(hours.intValue()).add(BigDecimal.valueOf(0.5));
        } else {
            hours = BigDecimal.valueOf(hours.intValue());
        }

        return hours;
    }


}
