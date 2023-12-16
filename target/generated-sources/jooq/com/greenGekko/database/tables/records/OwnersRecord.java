/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database.tables.records;


import com.greenGekko.database.tables.Owners;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OwnersRecord extends UpdatableRecordImpl<OwnersRecord> implements Record10<Integer, String, String, String, String, String, BigDecimal, OffsetDateTime, OffsetDateTime, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.owners.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.owners.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.owners.firstname</code>.
     */
    public void setFirstname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.owners.firstname</code>.
     */
    public String getFirstname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.owners.lastname</code>.
     */
    public void setLastname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.owners.lastname</code>.
     */
    public String getLastname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.owners.company_name</code>.
     */
    public void setCompanyName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.owners.company_name</code>.
     */
    public String getCompanyName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.owners.phone_number</code>.
     */
    public void setPhoneNumber(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.owners.phone_number</code>.
     */
    public String getPhoneNumber() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.owners.address</code>.
     */
    public void setAddress(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.owners.address</code>.
     */
    public String getAddress() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.owners.rating</code>.
     */
    public void setRating(BigDecimal value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.owners.rating</code>.
     */
    public BigDecimal getRating() {
        return (BigDecimal) get(6);
    }

    /**
     * Setter for <code>public.owners.created_at</code>.
     */
    public void setCreatedAt(OffsetDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.owners.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return (OffsetDateTime) get(7);
    }

    /**
     * Setter for <code>public.owners.updated_at</code>.
     */
    public void setUpdatedAt(OffsetDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.owners.updated_at</code>.
     */
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(8);
    }

    /**
     * Setter for <code>public.owners.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.owners.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, String, String, String, String, String, BigDecimal, OffsetDateTime, OffsetDateTime, Integer> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<Integer, String, String, String, String, String, BigDecimal, OffsetDateTime, OffsetDateTime, Integer> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Owners.OWNERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Owners.OWNERS.FIRSTNAME;
    }

    @Override
    public Field<String> field3() {
        return Owners.OWNERS.LASTNAME;
    }

    @Override
    public Field<String> field4() {
        return Owners.OWNERS.COMPANY_NAME;
    }

    @Override
    public Field<String> field5() {
        return Owners.OWNERS.PHONE_NUMBER;
    }

    @Override
    public Field<String> field6() {
        return Owners.OWNERS.ADDRESS;
    }

    @Override
    public Field<BigDecimal> field7() {
        return Owners.OWNERS.RATING;
    }

    @Override
    public Field<OffsetDateTime> field8() {
        return Owners.OWNERS.CREATED_AT;
    }

    @Override
    public Field<OffsetDateTime> field9() {
        return Owners.OWNERS.UPDATED_AT;
    }

    @Override
    public Field<Integer> field10() {
        return Owners.OWNERS.USER_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstname();
    }

    @Override
    public String component3() {
        return getLastname();
    }

    @Override
    public String component4() {
        return getCompanyName();
    }

    @Override
    public String component5() {
        return getPhoneNumber();
    }

    @Override
    public String component6() {
        return getAddress();
    }

    @Override
    public BigDecimal component7() {
        return getRating();
    }

    @Override
    public OffsetDateTime component8() {
        return getCreatedAt();
    }

    @Override
    public OffsetDateTime component9() {
        return getUpdatedAt();
    }

    @Override
    public Integer component10() {
        return getUserId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstname();
    }

    @Override
    public String value3() {
        return getLastname();
    }

    @Override
    public String value4() {
        return getCompanyName();
    }

    @Override
    public String value5() {
        return getPhoneNumber();
    }

    @Override
    public String value6() {
        return getAddress();
    }

    @Override
    public BigDecimal value7() {
        return getRating();
    }

    @Override
    public OffsetDateTime value8() {
        return getCreatedAt();
    }

    @Override
    public OffsetDateTime value9() {
        return getUpdatedAt();
    }

    @Override
    public Integer value10() {
        return getUserId();
    }

    @Override
    public OwnersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public OwnersRecord value2(String value) {
        setFirstname(value);
        return this;
    }

    @Override
    public OwnersRecord value3(String value) {
        setLastname(value);
        return this;
    }

    @Override
    public OwnersRecord value4(String value) {
        setCompanyName(value);
        return this;
    }

    @Override
    public OwnersRecord value5(String value) {
        setPhoneNumber(value);
        return this;
    }

    @Override
    public OwnersRecord value6(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public OwnersRecord value7(BigDecimal value) {
        setRating(value);
        return this;
    }

    @Override
    public OwnersRecord value8(OffsetDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public OwnersRecord value9(OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public OwnersRecord value10(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public OwnersRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, BigDecimal value7, OffsetDateTime value8, OffsetDateTime value9, Integer value10) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OwnersRecord
     */
    public OwnersRecord() {
        super(Owners.OWNERS);
    }

    /**
     * Create a detached, initialised OwnersRecord
     */
    public OwnersRecord(Integer id, String firstname, String lastname, String companyName, String phoneNumber, String address, BigDecimal rating, OffsetDateTime createdAt, OffsetDateTime updatedAt, Integer userId) {
        super(Owners.OWNERS);

        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
        setCompanyName(companyName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setRating(rating);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setUserId(userId);
    }
}