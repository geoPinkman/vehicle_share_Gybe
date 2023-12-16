//package com.greenGekko.integrations.calculate;
//
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//
//@Slf4j
//public class DatesService {
//
//    public long daysBetweenDates(LocalDate from, LocalDate to) {
//        return Math.abs(ChronoUnit.DAYS.between(from, to));
//    }
//
//    public JDateModel dateModelCount(LocalDate from, LocalDate to) {
//        JDateModel count = new JDateModel(0,0);
//        log.info("Count days {}, months {}", count.days, count.month);
//        LocalDate newFrom = from;
//        while (daysBetweenDates(newFrom, to) >= newFrom.lengthOfMonth()) {
//            log.info("daysBetween {} >? lengthMonth {}", daysBetweenDates(newFrom, to), newFrom.lengthOfMonth());
//            count.setMonth(count.month + 1);
//            log.info("Count month {}",  count.month);
//            log.info("Count days {}, months {}", count.days, count.month);
//            newFrom = newFrom.plusDays(newFrom.lengthOfMonth());
//            log.info("New month from {}",  newFrom);
//        }
//        count.setDays(daysBetweenDates(newFrom, to));
//        log.info("Count days {}, months {}", count.days, count.month);
//        return count;
//    }
//
//    @AllArgsConstructor
//    @Setter
//    @Getter
//    @ToString
//    public static class JDateModel {
//
//        private long month;
//
//        private long days;
//    }
//}
