/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database.tables.records;


import com.greenGekko.database.tables.VehiclePatterns;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VehiclePatternsRecord extends UpdatableRecordImpl<VehiclePatternsRecord> implements Record8<Integer, String, String, String, String, String, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.vehicle_patterns.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.vehicle_patterns.vehicle_type</code>.
     */
    public void setVehicleType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.vehicle_type</code>.
     */
    public String getVehicleType() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.vehicle_patterns.company</code>.
     */
    public void setCompany(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.company</code>.
     */
    public String getCompany() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.vehicle_patterns.model</code>.
     */
    public void setModel(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.model</code>.
     */
    public String getModel() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.vehicle_patterns.color</code>.
     */
    public void setColor(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.color</code>.
     */
    public String getColor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.vehicle_patterns.gearbox</code>.
     */
    public void setGearbox(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.gearbox</code>.
     */
    public String getGearbox() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.vehicle_patterns.engine_value</code>.
     */
    public void setEngineValue(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.engine_value</code>.
     */
    public Integer getEngineValue() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.vehicle_patterns.year_of_issue</code>.
     */
    public void setYearOfIssue(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.vehicle_patterns.year_of_issue</code>.
     */
    public Integer getYearOfIssue() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, String, String, String, String, String, Integer, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, String, String, String, String, String, Integer, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return VehiclePatterns.VEHICLE_PATTERNS.ID;
    }

    @Override
    public Field<String> field2() {
        return VehiclePatterns.VEHICLE_PATTERNS.VEHICLE_TYPE;
    }

    @Override
    public Field<String> field3() {
        return VehiclePatterns.VEHICLE_PATTERNS.COMPANY;
    }

    @Override
    public Field<String> field4() {
        return VehiclePatterns.VEHICLE_PATTERNS.MODEL;
    }

    @Override
    public Field<String> field5() {
        return VehiclePatterns.VEHICLE_PATTERNS.COLOR;
    }

    @Override
    public Field<String> field6() {
        return VehiclePatterns.VEHICLE_PATTERNS.GEARBOX;
    }

    @Override
    public Field<Integer> field7() {
        return VehiclePatterns.VEHICLE_PATTERNS.ENGINE_VALUE;
    }

    @Override
    public Field<Integer> field8() {
        return VehiclePatterns.VEHICLE_PATTERNS.YEAR_OF_ISSUE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getVehicleType();
    }

    @Override
    public String component3() {
        return getCompany();
    }

    @Override
    public String component4() {
        return getModel();
    }

    @Override
    public String component5() {
        return getColor();
    }

    @Override
    public String component6() {
        return getGearbox();
    }

    @Override
    public Integer component7() {
        return getEngineValue();
    }

    @Override
    public Integer component8() {
        return getYearOfIssue();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getVehicleType();
    }

    @Override
    public String value3() {
        return getCompany();
    }

    @Override
    public String value4() {
        return getModel();
    }

    @Override
    public String value5() {
        return getColor();
    }

    @Override
    public String value6() {
        return getGearbox();
    }

    @Override
    public Integer value7() {
        return getEngineValue();
    }

    @Override
    public Integer value8() {
        return getYearOfIssue();
    }

    @Override
    public VehiclePatternsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value2(String value) {
        setVehicleType(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value3(String value) {
        setCompany(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value4(String value) {
        setModel(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value5(String value) {
        setColor(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value6(String value) {
        setGearbox(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value7(Integer value) {
        setEngineValue(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord value8(Integer value) {
        setYearOfIssue(value);
        return this;
    }

    @Override
    public VehiclePatternsRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, Integer value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VehiclePatternsRecord
     */
    public VehiclePatternsRecord() {
        super(VehiclePatterns.VEHICLE_PATTERNS);
    }

    /**
     * Create a detached, initialised VehiclePatternsRecord
     */
    public VehiclePatternsRecord(Integer id, String vehicleType, String company, String model, String color, String gearbox, Integer engineValue, Integer yearOfIssue) {
        super(VehiclePatterns.VEHICLE_PATTERNS);

        setId(id);
        setVehicleType(vehicleType);
        setCompany(company);
        setModel(model);
        setColor(color);
        setGearbox(gearbox);
        setEngineValue(engineValue);
        setYearOfIssue(yearOfIssue);
    }
}
