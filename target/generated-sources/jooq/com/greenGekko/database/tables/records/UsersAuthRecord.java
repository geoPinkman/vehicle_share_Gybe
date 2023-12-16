/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database.tables.records;


import com.greenGekko.database.tables.UsersAuth;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersAuthRecord extends UpdatableRecordImpl<UsersAuthRecord> implements Record5<Integer, String, String, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.users_auth.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.users_auth.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.users_auth.email</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.users_auth.email</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.users_auth.system_code</code>.
     */
    public void setSystemCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.users_auth.system_code</code>.
     */
    public String getSystemCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.users_auth.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.users_auth.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.users_auth.code_status</code>.
     */
    public void setCodeStatus(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.users_auth.code_status</code>.
     */
    public String getCodeStatus() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, LocalDateTime, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return UsersAuth.USERS_AUTH.ID;
    }

    @Override
    public Field<String> field2() {
        return UsersAuth.USERS_AUTH.EMAIL;
    }

    @Override
    public Field<String> field3() {
        return UsersAuth.USERS_AUTH.SYSTEM_CODE;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return UsersAuth.USERS_AUTH.CREATED_AT;
    }

    @Override
    public Field<String> field5() {
        return UsersAuth.USERS_AUTH.CODE_STATUS;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public String component3() {
        return getSystemCode();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedAt();
    }

    @Override
    public String component5() {
        return getCodeStatus();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public String value3() {
        return getSystemCode();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedAt();
    }

    @Override
    public String value5() {
        return getCodeStatus();
    }

    @Override
    public UsersAuthRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UsersAuthRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UsersAuthRecord value3(String value) {
        setSystemCode(value);
        return this;
    }

    @Override
    public UsersAuthRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UsersAuthRecord value5(String value) {
        setCodeStatus(value);
        return this;
    }

    @Override
    public UsersAuthRecord values(Integer value1, String value2, String value3, LocalDateTime value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersAuthRecord
     */
    public UsersAuthRecord() {
        super(UsersAuth.USERS_AUTH);
    }

    /**
     * Create a detached, initialised UsersAuthRecord
     */
    public UsersAuthRecord(Integer id, String email, String systemCode, LocalDateTime createdAt, String codeStatus) {
        super(UsersAuth.USERS_AUTH);

        setId(id);
        setEmail(email);
        setSystemCode(systemCode);
        setCreatedAt(createdAt);
        setCodeStatus(codeStatus);
    }
}