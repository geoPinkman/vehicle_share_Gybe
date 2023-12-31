/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database.tables.records;


import com.greenGekko.database.tables.UsersRoles;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRolesRecord extends UpdatableRecordImpl<UsersRolesRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.users_roles.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.users_roles.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.users_roles.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.users_roles.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.users_roles.role_id</code>.
     */
    public void setRoleId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.users_roles.role_id</code>.
     */
    public Integer getRoleId() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return UsersRoles.USERS_ROLES.ID;
    }

    @Override
    public Field<Integer> field2() {
        return UsersRoles.USERS_ROLES.USER_ID;
    }

    @Override
    public Field<Integer> field3() {
        return UsersRoles.USERS_ROLES.ROLE_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getUserId();
    }

    @Override
    public Integer component3() {
        return getRoleId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getUserId();
    }

    @Override
    public Integer value3() {
        return getRoleId();
    }

    @Override
    public UsersRolesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UsersRolesRecord value2(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public UsersRolesRecord value3(Integer value) {
        setRoleId(value);
        return this;
    }

    @Override
    public UsersRolesRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRolesRecord
     */
    public UsersRolesRecord() {
        super(UsersRoles.USERS_ROLES);
    }

    /**
     * Create a detached, initialised UsersRolesRecord
     */
    public UsersRolesRecord(Integer id, Integer userId, Integer roleId) {
        super(UsersRoles.USERS_ROLES);

        setId(id);
        setUserId(userId);
        setRoleId(roleId);
    }
}
