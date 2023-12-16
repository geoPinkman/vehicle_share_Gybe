/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database.tables.records;


import com.greenGekko.database.tables.Vehicles;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VehiclesRecord extends UpdatableRecordImpl<VehiclesRecord> implements Record12<Integer, Integer, Integer, UUID, Integer, Integer, Integer, String, String, OffsetDateTime, OffsetDateTime, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.vehicles.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.vehicles.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.vehicles.vehicle_pattern_id</code>.
     */
    public void setVehiclePatternId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.vehicles.vehicle_pattern_id</code>.
     */
    public Integer getVehiclePatternId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.vehicles.owner_id</code>.
     */
    public void setOwnerId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.vehicles.owner_id</code>.
     */
    public Integer getOwnerId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.vehicles.uuid</code>.
     */
    public void setUuid(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.vehicles.uuid</code>.
     */
    public UUID getUuid() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.vehicles.price_per_day</code>.
     */
    public void setPricePerDay(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.vehicles.price_per_day</code>.
     */
    public Integer getPricePerDay() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.vehicles.price_per_month</code>.
     */
    public void setPricePerMonth(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.vehicles.price_per_month</code>.
     */
    public Integer getPricePerMonth() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>public.vehicles.deposit</code>.
     */
    public void setDeposit(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.vehicles.deposit</code>.
     */
    public Integer getDeposit() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.vehicles.description</code>.
     */
    public void setDescription(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.vehicles.description</code>.
     */
    public String getDescription() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.vehicles.logo</code>.
     */
    public void setLogo(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.vehicles.logo</code>.
     */
    public String getLogo() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.vehicles.created_at</code>.
     */
    public void setCreatedAt(OffsetDateTime value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.vehicles.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return (OffsetDateTime) get(9);
    }

    /**
     * Setter for <code>public.vehicles.updated_at</code>.
     */
    public void setUpdatedAt(OffsetDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.vehicles.updated_at</code>.
     */
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(10);
    }

    /**
     * Setter for <code>public.vehicles.available_now</code>.
     */
    public void setAvailableNow(Boolean value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.vehicles.available_now</code>.
     */
    public Boolean getAvailableNow() {
        return (Boolean) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, Integer, Integer, UUID, Integer, Integer, Integer, String, String, OffsetDateTime, OffsetDateTime, Boolean> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Integer, Integer, Integer, UUID, Integer, Integer, Integer, String, String, OffsetDateTime, OffsetDateTime, Boolean> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Vehicles.VEHICLES.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Vehicles.VEHICLES.VEHICLE_PATTERN_ID;
    }

    @Override
    public Field<Integer> field3() {
        return Vehicles.VEHICLES.OWNER_ID;
    }

    @Override
    public Field<UUID> field4() {
        return Vehicles.VEHICLES.UUID;
    }

    @Override
    public Field<Integer> field5() {
        return Vehicles.VEHICLES.PRICE_PER_DAY;
    }

    @Override
    public Field<Integer> field6() {
        return Vehicles.VEHICLES.PRICE_PER_MONTH;
    }

    @Override
    public Field<Integer> field7() {
        return Vehicles.VEHICLES.DEPOSIT;
    }

    @Override
    public Field<String> field8() {
        return Vehicles.VEHICLES.DESCRIPTION;
    }

    @Override
    public Field<String> field9() {
        return Vehicles.VEHICLES.LOGO;
    }

    @Override
    public Field<OffsetDateTime> field10() {
        return Vehicles.VEHICLES.CREATED_AT;
    }

    @Override
    public Field<OffsetDateTime> field11() {
        return Vehicles.VEHICLES.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field12() {
        return Vehicles.VEHICLES.AVAILABLE_NOW;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getVehiclePatternId();
    }

    @Override
    public Integer component3() {
        return getOwnerId();
    }

    @Override
    public UUID component4() {
        return getUuid();
    }

    @Override
    public Integer component5() {
        return getPricePerDay();
    }

    @Override
    public Integer component6() {
        return getPricePerMonth();
    }

    @Override
    public Integer component7() {
        return getDeposit();
    }

    @Override
    public String component8() {
        return getDescription();
    }

    @Override
    public String component9() {
        return getLogo();
    }

    @Override
    public OffsetDateTime component10() {
        return getCreatedAt();
    }

    @Override
    public OffsetDateTime component11() {
        return getUpdatedAt();
    }

    @Override
    public Boolean component12() {
        return getAvailableNow();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getVehiclePatternId();
    }

    @Override
    public Integer value3() {
        return getOwnerId();
    }

    @Override
    public UUID value4() {
        return getUuid();
    }

    @Override
    public Integer value5() {
        return getPricePerDay();
    }

    @Override
    public Integer value6() {
        return getPricePerMonth();
    }

    @Override
    public Integer value7() {
        return getDeposit();
    }

    @Override
    public String value8() {
        return getDescription();
    }

    @Override
    public String value9() {
        return getLogo();
    }

    @Override
    public OffsetDateTime value10() {
        return getCreatedAt();
    }

    @Override
    public OffsetDateTime value11() {
        return getUpdatedAt();
    }

    @Override
    public Boolean value12() {
        return getAvailableNow();
    }

    @Override
    public VehiclesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public VehiclesRecord value2(Integer value) {
        setVehiclePatternId(value);
        return this;
    }

    @Override
    public VehiclesRecord value3(Integer value) {
        setOwnerId(value);
        return this;
    }

    @Override
    public VehiclesRecord value4(UUID value) {
        setUuid(value);
        return this;
    }

    @Override
    public VehiclesRecord value5(Integer value) {
        setPricePerDay(value);
        return this;
    }

    @Override
    public VehiclesRecord value6(Integer value) {
        setPricePerMonth(value);
        return this;
    }

    @Override
    public VehiclesRecord value7(Integer value) {
        setDeposit(value);
        return this;
    }

    @Override
    public VehiclesRecord value8(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public VehiclesRecord value9(String value) {
        setLogo(value);
        return this;
    }

    @Override
    public VehiclesRecord value10(OffsetDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public VehiclesRecord value11(OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public VehiclesRecord value12(Boolean value) {
        setAvailableNow(value);
        return this;
    }

    @Override
    public VehiclesRecord values(Integer value1, Integer value2, Integer value3, UUID value4, Integer value5, Integer value6, Integer value7, String value8, String value9, OffsetDateTime value10, OffsetDateTime value11, Boolean value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VehiclesRecord
     */
    public VehiclesRecord() {
        super(Vehicles.VEHICLES);
    }

    /**
     * Create a detached, initialised VehiclesRecord
     */
    public VehiclesRecord(Integer id, Integer vehiclePatternId, Integer ownerId, UUID uuid, Integer pricePerDay, Integer pricePerMonth, Integer deposit, String description, String logo, OffsetDateTime createdAt, OffsetDateTime updatedAt, Boolean availableNow) {
        super(Vehicles.VEHICLES);

        setId(id);
        setVehiclePatternId(vehiclePatternId);
        setOwnerId(ownerId);
        setUuid(uuid);
        setPricePerDay(pricePerDay);
        setPricePerMonth(pricePerMonth);
        setDeposit(deposit);
        setDescription(description);
        setLogo(logo);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setAvailableNow(availableNow);
    }
}
