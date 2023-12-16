package com.greenGekko.servicies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenGekko.database.tables.records.VehiclesRecord;
import com.greenGekko.exceptions.InputDataException;
import com.greenGekko.models.*;
import lombok.*;
import org.jooq.Record;
import com.greenGekko.exceptions.ResourceNotFoundException;
import static com.greenGekko.database.tables.Vehicles.VEHICLES;
import static com.greenGekko.database.tables.VehiclePatterns.VEHICLE_PATTERNS;
import static com.greenGekko.database.tables.Owners.OWNERS;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.UpdateQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private final DSLContext dslContext;
    private final RentDatesService datesService;
    private final VehiclePatternService vehiclePatternService;

    private JVehiclesByOwner parseVehicleForOwner(Record r) {
        return parseVehicleForOwner(r, new JRentDates(new ArrayList<>()));
    }

    private JVehiclesByOwner parseVehicleForOwner(Record r, JRentDates rentDates) {
        return JVehiclesByOwner.builder()
                .uuid(r.get(VEHICLES.UUID).toString())
                .pricePerDay(r.get(VEHICLES.PRICE_PER_DAY))
                .pricePerMonth(r.get(VEHICLES.PRICE_PER_MONTH))
                .deposit(r.get(VEHICLES.DEPOSIT))
                .vehiclePattern(JVehiclePattern.builder()
                        .vehicleType(r.get(VEHICLE_PATTERNS.VEHICLE_TYPE))
                        .company(r.get(VEHICLE_PATTERNS.COMPANY))
                        .model(r.get(VEHICLE_PATTERNS.MODEL))
                        .color(r.get(VEHICLE_PATTERNS.COLOR))
                        .gearbox(r.get(VEHICLE_PATTERNS.GEARBOX))
                        .engineValue(r.get(VEHICLE_PATTERNS.ENGINE_VALUE))
                        .yearOfIssue(r.get(VEHICLE_PATTERNS.YEAR_OF_ISSUE))
                        .build())
                .description(r.get(VEHICLES.DESCRIPTION))
                .available(r.get(VEHICLES.AVAILABLE_NOW))
                .listOfRentDates(rentDates)
                .build();
    }

    private JVehicle parseVehicle(Record r) {
        return JVehicle.builder()
                .logo(r.get(VEHICLES.LOGO))
                .vehiclePattern(JVehiclePattern.builder()
                        .vehicleType(r.get(VEHICLE_PATTERNS.VEHICLE_TYPE))
                        .company(r.get(VEHICLE_PATTERNS.COMPANY))
                        .model(r.get(VEHICLE_PATTERNS.MODEL))
                        .color(r.get(VEHICLE_PATTERNS.COLOR))
                        .gearbox(r.get(VEHICLE_PATTERNS.GEARBOX))
                        .engineValue(r.get(VEHICLE_PATTERNS.ENGINE_VALUE))
                        .yearOfIssue(r.get(VEHICLE_PATTERNS.YEAR_OF_ISSUE))
                        .build())
                .description(r.get(VEHICLES.DESCRIPTION))
                .deposit(r.get(VEHICLES.DEPOSIT))
                .pricePerDay(r.get(VEHICLES.PRICE_PER_DAY))
                .pricePerMonth(r.get(VEHICLES.PRICE_PER_MONTH))
                .uuid(r.get(VEHICLES.UUID).toString())
                .build();
    }

    public List<JVehicle> getAllVehicles() {
        return dslContext.select().from(VEHICLES)
                .join(VEHICLE_PATTERNS).on(VEHICLES.VEHICLE_PATTERN_ID.eq(VEHICLE_PATTERNS.ID))
                .fetchStream()
                .map(this::parseVehicle)
                .collect(Collectors.toList());
    }

    public JVehiclesByOwner create(int ownerId, int vehiclePatternId, VehicleCreateParams vehicleParams) {
        return dslContext.insertInto(VEHICLES)
                .set(VEHICLES.OWNER_ID, ownerId)
                .set(VEHICLES.VEHICLE_PATTERN_ID, vehiclePatternId)
                .set(VEHICLES.UUID, UUID.randomUUID())
                .set(VEHICLES.LOGO, "Path to logo by pattern of vehicle")
                .set(VEHICLES.CREATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                .set(VEHICLES.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                .set(VEHICLES.AVAILABLE_NOW, false)
                .set(VEHICLES.PRICE_PER_DAY, vehicleParams.pricePerDay)
                .set(VEHICLES.PRICE_PER_MONTH, vehicleParams.pricePerMonth)
                .set(VEHICLES.DEPOSIT, vehicleParams.deposit)
                .set(VEHICLES.DESCRIPTION, vehicleParams.description)
                .returning()
                .fetchOptional()
                .map(this::parseVehicleForOwner)
                .orElseThrow(() -> new InputDataException("Something wrong"));
    }

    public List<JVehiclesByOwner> getAllVehiclesByOwnerId(int id) {
        return dslContext.select().from(VEHICLES)
                .join(VEHICLE_PATTERNS).on(VEHICLES.VEHICLE_PATTERN_ID.eq(VEHICLE_PATTERNS.ID))
                .where(VEHICLES.OWNER_ID.eq(id))
                .fetchStream()
                .map(this::parseVehicleForOwner)
                .collect(Collectors.toList());
    }

    public JVehiclesByOwner getVehicleByOwnerIdAndUuid(int ownerId, String uuid) {
        JRentDates rentDates = datesService.getListOfRentDatesByVehicleUuid(uuid);

        return dslContext.select().from(VEHICLES)
                .join(VEHICLE_PATTERNS).on(VEHICLES.VEHICLE_PATTERN_ID.eq(VEHICLE_PATTERNS.ID))
                .where(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                .and(VEHICLES.OWNER_ID.eq(ownerId))
                .fetchOptional()
                .map(r -> parseVehicleForOwner(r, rentDates))
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public JVehiclesByOwner update(int ownerId, String uuid, VehicleUpdateParams updateParams) {
        int vehiclePatternId = dslContext.select(VEHICLES.VEHICLE_PATTERN_ID)
                .from(VEHICLES)
                .where(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                .fetchOne()
                .value1();

        if (vehiclePatternId != 0) {
            if (updateParams.vehiclePattern != null) {
                vehiclePatternId = vehiclePatternService.updateVehiclePatternByRequest(vehiclePatternId, updateParams.vehiclePattern);
            }
        }
        UpdateQuery<VehiclesRecord> updateQuery = dslContext.updateQuery(VEHICLES);

        if (updateParams.pricePerDay != 0) {
            updateQuery.addValue(VEHICLES.PRICE_PER_DAY, updateParams.pricePerDay);
        }
        if (updateParams.pricePerMonth != 0) {
            updateQuery.addValue(VEHICLES.PRICE_PER_MONTH, updateParams.pricePerMonth);
        }
        if (updateParams.deposit != 0) {
            updateQuery.addValue(VEHICLES.DEPOSIT, updateParams.deposit);
        }
        if (updateParams.description != null) {
            updateQuery.addValue(VEHICLES.DESCRIPTION, updateParams.description);
        }

        updateQuery.addValue(VEHICLES.LOGO, "logo by new pattern");
        updateQuery.addValue(VEHICLES.VEHICLE_PATTERN_ID, vehiclePatternId);
        updateQuery.addValue(VEHICLES.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC));
        updateQuery.addConditions(VEHICLES.UUID.eq(UUID.fromString(uuid))
                .and(VEHICLES.OWNER_ID.eq(ownerId)));
        updateQuery.execute();

        return getVehicleByOwnerIdAndUuid(ownerId, uuid);
    }

    public void changeAvailableStatus(int ownerId, String uuid, boolean status) {
        JVehiclesByOwner vehicle = getVehicleByOwnerIdAndUuid(ownerId, uuid);
        if (vehicle.available != status) {
            dslContext.update(VEHICLES)
                    .set(VEHICLES.AVAILABLE_NOW, status)
                    .where(VEHICLES.OWNER_ID.eq(ownerId))
                    .and(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                    .execute();
        }
    }

    public Integer deleteVehicleByUuid(int ownerId, String uuid) {
        JVehiclesByOwner vehicles = getVehicleByOwnerIdAndUuid(ownerId, uuid);
        JRentDates rentDates = vehicles.listOfRentDates;
        if (rentDates == null || rentDates.listOfRents == null) {
            return dslContext.deleteFrom(VEHICLES)
                    .where(VEHICLES.OWNER_ID.eq(ownerId))
                    .and(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                    .returning()
                    .fetchOne()
                    .getId();
        }
        throw new RuntimeException(String.valueOf(HttpStatus.BAD_REQUEST));
    }
//
//    public JVehicle getVehicleById(int id) {
//
//        return dslContext.select()
//                .from(VEHICLES)
//                .join(VEHICLE_VALUE).on(VEHICLE_VALUE.ID.eq(VEHICLES.VEHICLE_VALUE_ID))
//                .join(OWNERS).on(OWNERS.ID.eq(VEHICLES.OWNER_ID))
//                .where(VEHICLES.ID.eq(id))
//                .fetchOptional()
//                .map(this::parseVehicle)
//                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id " + id + " not found"));
//    }
//
//    public JVehicles getAvailableVehiclesOnDates(RentDatesService.TimeRentRequest timeRentRequest) {
//
//        Set<Integer> rentedVehiclesId = datesService.getVehiclesIdBetweenDatesRequest(timeRentRequest);
//        Set<Integer> allVehiclesId = dslContext
//                .selectFrom(VEHICLES)
//                .fetchSet(VEHICLES.ID);
//
//        allVehiclesId.removeAll(rentedVehiclesId);
//
//        return new JVehicles(dslContext.select()
//                .from(VEHICLES)
//                .join(VEHICLE_VALUE).on(VEHICLE_VALUE.ID.eq(VEHICLES.VEHICLE_VALUE_ID))
//                .join(OWNERS).on(OWNERS.ID.eq(VEHICLES.OWNER_ID))
//                .where(VEHICLES.ID.in(allVehiclesId))
//                .orderBy(VEHICLES.ID.asc())
//                .fetchStream()
//                .map(this::parseVehicle)
//                .collect(Collectors.toList()));
//    }
//
//    public JVehicles getAllVehicles(){
//        return new JVehicles(dslContext.select()
//                .from(VEHICLES)
//                .join(VEHICLE_VALUE).on(VEHICLE_VALUE.ID.eq(VEHICLES.VEHICLE_VALUE_ID))
//                .join(OWNERS).on(OWNERS.ID.eq(VEHICLES.OWNER_ID))
//                .orderBy(VEHICLES.PRICE_PER_MONTH)
//                .fetchStream()
//                .map(this::parseVehicle)
//                .collect(Collectors.toList()));
//    }
//
//    public JVehicle update(int ownerId, String uuid, VehicleUpdateParams params){
//        VehiclesRecord vehicle = dslContext.selectFrom(VEHICLES)
//                .where(VEHICLES.OWNER_ID.eq(ownerId))
//                .and(VEHICLES.UUID.eq(UUID.fromString(uuid)))
//                .fetchOptional()
//                .orElseThrow(() -> new ResourceNotFoundException("No vehicle available by these params" + ownerId + " and " + uuid));
//
//        UpdateQuery<VehiclesRecord> query = dslContext.updateQuery(VEHICLES);
//        if (params.pricePerDay != 0) {
//            query.addValue(VEHICLES.PRICE_PER_DAY, params.pricePerDay);
//        }
//        if (params.pricePerMonth != 0 ) {
//            query.addValue(VEHICLES.PRICE_PER_MONTH, params.pricePerMonth);
//        }
//        query.addConditions(VEHICLES.ID.eq(vehicle.getId()));
//
//        return query
//                .getReturnedRecord()
//                .map(this::parseVehicle);
//    }
//
//    public void deleteVehicleById(int id){
//        dslContext.deleteFrom(VEHICLES)
//                .where(VEHICLES.ID.eq(id))
//                .execute();
//
//    }
//
    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class VehicleCreateParams {

        @JsonProperty("pricePerDay")
        public final int pricePerDay;
        @JsonProperty("pricePerMonth")
        public final int pricePerMonth;
        @JsonProperty("deposit")
        public final int deposit;
        @JsonProperty("description")
        public final String description;

    }

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class VehicleUpdateParams {

        @JsonProperty("pricePerDay")
        public final int pricePerDay;
        @JsonProperty("pricePerMonth")
        public final int pricePerMonth;
        @JsonProperty("deposit")
        public final int deposit;
        @JsonProperty("description")
        public final String description;
        @JsonProperty("vehiclePattern")
        public final JVehiclePattern vehiclePattern;
    }

}
