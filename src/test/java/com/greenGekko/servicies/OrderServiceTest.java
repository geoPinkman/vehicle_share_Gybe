package com.greenGekko.servicies;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import java.util.Calendar;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    //@Autowired
    //private OrderService orderService;

//    @Test
//    void getAmount() {
//        Calendar from = Calendar.getInstance();
//        from.set(2023, Calendar.APRIL, 4);
//        Calendar to = Calendar.getInstance();
//        to.set(2023, Calendar.APRIL, 5);
//        assertEquals(200.0, orderService.getAmount(200, from, to));
//    }
//
//    @Test
//    void getAmountOnNextMonth() {
//        Calendar from = Calendar.getInstance();
//        from.set(2023, Calendar.MARCH, 27);
//        Calendar to = Calendar.getInstance();
//        to.set(2023, Calendar.APRIL, 1);
//        assertEquals(500, orderService.getAmount(100, from, to));
//    }
//
//    @Test
//    void getAmountThenMoreMonth31() {
//        Calendar from = Calendar.getInstance();
//        from.set(2023, Calendar.MARCH, 1);
//        Calendar to = Calendar.getInstance();
//        to.set(2023, Calendar.APRIL, 1);
//        assertEquals(1550, orderService.getAmount(100, from, to));
//    }
//    @Test
//    void getAmountThenMoreMonth() {
//        Calendar from = Calendar.getInstance();
//        from.set(2023, Calendar.MARCH, 1);
//        Calendar to = Calendar.getInstance();
//        to.set(2023, Calendar.APRIL, 2);
//        assertEquals(1600, orderService.getAmount(100, from, to));
//    }
//
//    @Test
//    void getAmountThenMoreMonth30() {
//        Calendar from = Calendar.getInstance();
//        from.set(2023, Calendar.APRIL, 1);
//        Calendar to = Calendar.getInstance();
//        to.set(2023, Calendar.MAY, 1);
//        assertEquals(1500, orderService.getAmount(100, from, to));
//    }

}