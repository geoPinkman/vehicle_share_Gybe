package com.greenGekko.servicies;

import static com.greenGekko.database.tables.VehicleRentDates.VEHICLE_RENT_DATES;
import static com.greenGekko.database.tables.Vehicles.VEHICLES;

import com.greenGekko.models.JRentDates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RentDatesService {

    private final DSLContext dslContext;

    private JRentDates.RentFromTo parseDates(Record r) {
        return JRentDates.RentFromTo.builder()
                .dateFrom(r.get(VEHICLE_RENT_DATES.DATE_FROM).toLocalDate())
                .dateTo(r.get(VEHICLE_RENT_DATES.DATE_TO).toLocalDate())
                .build();

    }

    public void createPair(TimeRentRequest time, int vehicleId){
        dslContext.insertInto(VEHICLE_RENT_DATES)
                .set(VEHICLE_RENT_DATES.VEHICLE_ID, vehicleId)
                .set(VEHICLE_RENT_DATES.DATE_FROM, LocalDateTime.of(
                        time.from.year,
                        time.from.month,
                        time.from.day,
                        12,
                        0))
                .set(VEHICLE_RENT_DATES.DATE_TO, LocalDateTime.of(
                        time.to.year,
                        time.to.month,
                        time.to.day,
                        12,
                        0))
                .execute();
    }

    public JRentDates getListOfRentDatesByVehicleId(int vehicleId) {
        return new JRentDates(dslContext.selectFrom(VEHICLE_RENT_DATES)
                .where(VEHICLE_RENT_DATES.VEHICLE_ID.eq(vehicleId))
                .fetchStream()
                .map(this::parseDates)
                .collect(Collectors.toList()));
    }

    public JRentDates getListOfRentDatesByVehicleUuid(String uuid) {
        return new JRentDates(dslContext.select(VEHICLE_RENT_DATES.DATE_FROM, VEHICLE_RENT_DATES.DATE_TO)
                .from(VEHICLES)
                .leftJoin(VEHICLE_RENT_DATES).on(VEHICLES.ID.eq(VEHICLE_RENT_DATES.VEHICLE_ID))
                .where(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                .fetchStream()
                .map(this::parseDates)
                .collect(Collectors.toList()));


    }

    private Condition selectBetweenDatesCondition(LocalDateTime timeFrom, LocalDateTime timeTo) {
        return (VEHICLE_RENT_DATES.DATE_FROM.between(timeFrom, timeTo))
                .or(VEHICLE_RENT_DATES.DATE_TO.between(timeFrom, timeTo))
                .or(VEHICLE_RENT_DATES.DATE_FROM.lessThan(timeFrom).and(VEHICLE_RENT_DATES.DATE_TO.greaterThan(timeFrom))
                        .or(VEHICLE_RENT_DATES.DATE_FROM.lessThan(timeTo).and(VEHICLE_RENT_DATES.DATE_TO.greaterThan(timeTo))));

    }

    public Set<Integer> getVehiclesIdBetweenDatesRequest(TimeRentRequest request) {
        JTime from = request.from;
        LocalDateTime timeFrom = LocalDateTime.of(from.year, from.month, from.day, 12, 0);

        JTime to = request.to;
        LocalDateTime timeTo = LocalDateTime.of(to.year, to.month, to.day, 12, 0);

        return dslContext.selectFrom(VEHICLE_RENT_DATES)
                .where(selectBetweenDatesCondition(timeFrom, timeTo))
                .and(VEHICLE_RENT_DATES.DATE_FROM.greaterOrEqual(LocalDateTime.now()))
                .orderBy(VEHICLE_RENT_DATES.DATE_FROM)
                .fetchSet(VEHICLE_RENT_DATES.VEHICLE_ID);
    }

    @AllArgsConstructor
    @Builder
    public static class JTime {
        public final int day;
        public final int month;
        public final int year;
    }

    @AllArgsConstructor
    @Builder
    public static class TimeRentRequest {
        public final JTime from;
        public final JTime to;
    }

    @AllArgsConstructor
    @Builder
    public static class SearchDates {

        public int vehicleId;
        public final LocalDate from;
        public final LocalDate to;

    }
}
