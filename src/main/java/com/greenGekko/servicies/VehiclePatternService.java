package com.greenGekko.servicies;

import static com.greenGekko.database.tables.VehiclePatterns.VEHICLE_PATTERNS;
import static com.greenGekko.database.tables.Vehicles.VEHICLES;
import com.greenGekko.database.tables.records.VehiclePatternsRecord;
import com.greenGekko.exceptions.InputDataException;
import com.greenGekko.exceptions.ResourceNotFoundException;
import com.greenGekko.models.JVehiclePattern;
import com.greenGekko.utils.ObjectToMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VehiclePatternService {

    private final DSLContext dslContext;

    private JVehiclePattern parseVehiclePattern(Record r) {
        log.info("Parse record {} into JVehiclePattern class", r);
        return JVehiclePattern.builder()
                .vehicleType(r.get(VEHICLE_PATTERNS.VEHICLE_TYPE))
                .company(r.get(VEHICLE_PATTERNS.COMPANY))
                .model(r.get(VEHICLE_PATTERNS.MODEL))
                .color(r.get(VEHICLE_PATTERNS.COLOR))
                .gearbox(r.get(VEHICLE_PATTERNS.GEARBOX))
                .engineValue(r.get(VEHICLE_PATTERNS.ENGINE_VALUE))
                .yearOfIssue(r.get(VEHICLE_PATTERNS.YEAR_OF_ISSUE))
                .build();
    }

    private boolean isEmptyFields(Object o) {
        long countFields = ObjectToMap.getMap(o)
                .values()
                .stream()
                .filter(v -> v == null || v.toString().isBlank())
                .count();
        log.warn("If countField {} != 0 is means that some fields of Object {} are null", countFields, o);
        return countFields != 0;
    }

    private Map<String, Object> getMapOfUpdatedPattern(Map<String, Object> oldPattern, Map<String, Object> newPattern) {
        Map<String, Object> result = new HashMap<>(oldPattern);
        for (Map.Entry<String, Object> map : oldPattern.entrySet()) {
            if (newPattern.get(map.getKey()) == null
                    || newPattern.get(map.getKey()).equals(map.getValue().toString())) {
                continue;
            }
            if (newPattern.get(map.getKey()) instanceof Integer) {
                if (Integer.parseInt(newPattern.get(map.getKey()).toString()) == 0) {
                    continue;
                }
            }
            result.put(map.getKey(), newPattern.get(map.getKey()).toString());
        }
        log.info("Changed all fields in Old Vehicle Pattern {} to New Vehicle Pattern {} fields", oldPattern, result);
        return result;
    }

    private Integer getVehiclePatternIdByRequest(JVehiclePattern pattern) {
        if (!isEmptyFields(pattern)) {
            log.info("Pattern {} have all inserted fields", pattern);
            return dslContext.selectFrom(VEHICLE_PATTERNS)
                    .where(VEHICLE_PATTERNS.VEHICLE_TYPE.equalIgnoreCase(pattern.vehicleType)
                            .and(VEHICLE_PATTERNS.COMPANY.equalIgnoreCase(pattern.company))
                            .and(VEHICLE_PATTERNS.MODEL.equalIgnoreCase(pattern.model))
                            .and(VEHICLE_PATTERNS.COLOR.equalIgnoreCase(pattern.color))
                            .and(VEHICLE_PATTERNS.GEARBOX.equalIgnoreCase(pattern.gearbox))
                            .and(VEHICLE_PATTERNS.ENGINE_VALUE.eq(pattern.engineValue))
                            .and(VEHICLE_PATTERNS.YEAR_OF_ISSUE.eq(pattern.yearOfIssue)))
                    .fetchOne()
                    .getId();
        }
        log.warn("Some fields of {} are null", pattern);
        throw new InputDataException("One or more fields are empty or blank");
    }

    public JVehiclePattern getVehiclePatternById(int id) {
        return dslContext.selectFrom(VEHICLE_PATTERNS)
                .where(VEHICLE_PATTERNS.ID.eq(id))
                .fetchOne(this::parseVehiclePattern);
    }

    public JVehiclePattern getPatternByVehicleUuid(String uuid) {
        return dslContext.select().from(VEHICLE_PATTERNS)
                .leftJoin(VEHICLES).on(VEHICLE_PATTERNS.ID.eq(VEHICLES.VEHICLE_PATTERN_ID))
                .where(VEHICLES.UUID.eq(UUID.fromString(uuid)))
                .fetchOptional()
                .map(this::parseVehiclePattern)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public Integer create(JVehiclePattern pattern) {

        return dslContext.insertInto(VEHICLE_PATTERNS)
                .set(VEHICLE_PATTERNS.VEHICLE_TYPE, pattern.vehicleType)
                .set(VEHICLE_PATTERNS.COMPANY, pattern.company)
                .set(VEHICLE_PATTERNS.MODEL, pattern.model)
                .set(VEHICLE_PATTERNS.COLOR, pattern.color)
                .set(VEHICLE_PATTERNS.GEARBOX, pattern.gearbox)
                .set(VEHICLE_PATTERNS.ENGINE_VALUE, pattern.engineValue)
                .set(VEHICLE_PATTERNS.YEAR_OF_ISSUE, pattern.yearOfIssue)
                .returning()
                .fetchOne()
                .getId();

    }

    public Integer updateVehiclePatternByRequest(int id, JVehiclePattern newPattern) {
        Map<String, Object> resultMap;
        JVehiclePattern oldPattern = getVehiclePatternById(id);
        if (oldPattern == null) {
            if (isEmptyFields(newPattern)) {
                throw new InputDataException("One or more fields are empty or blank");
            }
            return create(newPattern);
        }
        resultMap = getMapOfUpdatedPattern(ObjectToMap.getMap(oldPattern),
                ObjectToMap.getMap(newPattern));
        JVehiclePattern newReq = ObjectToMap.getObject(resultMap, JVehiclePattern.class);
        Integer updatedId = getVehiclePatternIdByRequest(newReq);
        if (updatedId != null) {
            return updatedId;
        }
        return create(newReq);
    }

    //admin mode
    public JVehiclePattern getVehiclePatternByRequest(JVehiclePattern pattern) {
        return dslContext.select()
                .from(VEHICLE_PATTERNS)
                .where(VEHICLE_PATTERNS.VEHICLE_TYPE.equalIgnoreCase(pattern.vehicleType)
                        .and(VEHICLE_PATTERNS.COMPANY.equalIgnoreCase(pattern.company))
                        .and(VEHICLE_PATTERNS.MODEL.equalIgnoreCase(pattern.model))
                        .and(VEHICLE_PATTERNS.COLOR.equalIgnoreCase(pattern.color))
                        .and(VEHICLE_PATTERNS.GEARBOX.equalIgnoreCase(pattern.gearbox))
                        .and(VEHICLE_PATTERNS.ENGINE_VALUE.eq(pattern.engineValue))
                        .and(VEHICLE_PATTERNS.YEAR_OF_ISSUE.eq(pattern.yearOfIssue)))
                .fetchOptional()
                .map(this::parseVehiclePattern)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + pattern + " wasn't found"));
    }

    public List<JVehiclePattern> getAllPattern() {
        return dslContext.selectFrom(VEHICLE_PATTERNS)
                .fetchStream()
                .map(this::parseVehiclePattern)
                .collect(Collectors.toList());

    }


    //admin mode
    public void delete(int id) {
        dslContext.delete(VEHICLE_PATTERNS)
                .where(VEHICLE_PATTERNS.ID.eq(id))
                .execute();
    }

}
