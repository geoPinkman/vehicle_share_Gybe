package com.greenGekko.servicies;

import com.greenGekko.models.JVehicle;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class VehicleServiceTest {

//    @Autowired
//    VehicleService vehicleService;


//    @Test
//    void isAvailableDifYearToJan() {
//        JVehicle.JTime from = JVehicle.JTime.builder()
//                .year(2022)
//                .month(12)
//                .day(12).build();
//
//        JVehicle.JTime to = JVehicle.JTime.builder()
//                .year(2023)
//                .month(1)
//                .day(1).build();
//        assertEquals(true, vehicleService.isAvailableByTime(from, to));
//    }
//
//    @Test
//    void isAvailableToDayBefore() {
//        JVehicle.JTime from = JVehicle.JTime.builder()
//                .year(2022)
//                .month(12)
//                .day(12).build();
//
//        JVehicle.JTime to = JVehicle.JTime.builder()
//                .year(2023)
//                .month(2)
//                .day(22).build();
//        assertEquals(true, vehicleService.isAvailableByTime(from, to));
//
//    }
//
//    @Test
//    void isAvailableToDayAfter() {
//        JVehicle.JTime from = JVehicle.JTime.builder()
//                .year(2023)
//                .month(2)
//                .day(22).build();
//
//        JVehicle.JTime to = JVehicle.JTime.builder()
//                .year(2023)
//                .month(3)
//                .day(22).build();
//        assertEquals(false, vehicleService.isAvailableByTime(from, to));
//
//    }
//
//    @Test
//    void isAvailableDayInDay() {
//        JVehicle.JTime from = JVehicle.JTime.builder()
//                .year(2023)
//                .month(2)
//                .day(22).build();
//
//        JVehicle.JTime to = JVehicle.JTime.builder()
//                .year(2023)
//                .month(2)
//                .day(22).build();
//        assertEquals(true, vehicleService.isAvailableByTime(from, to));
//    }

}