/*
 * This file is generated by jOOQ.
 */
package com.greenGekko.database;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.owner_options_id_seq</code>
     */
    public static final Sequence<Integer> OWNER_OPTIONS_ID_SEQ = Internal.createSequence("owner_options_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.owners_id_seq</code>
     */
    public static final Sequence<Integer> OWNERS_ID_SEQ = Internal.createSequence("owners_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.roles_id_seq</code>
     */
    public static final Sequence<Integer> ROLES_ID_SEQ = Internal.createSequence("roles_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.users_auth_id_seq</code>
     */
    public static final Sequence<Integer> USERS_AUTH_ID_SEQ = Internal.createSequence("users_auth_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.users_id_seq</code>
     */
    public static final Sequence<Integer> USERS_ID_SEQ = Internal.createSequence("users_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.users_roles_id_seq</code>
     */
    public static final Sequence<Integer> USERS_ROLES_ID_SEQ = Internal.createSequence("users_roles_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.vehicle_rent_dates_id_seq</code>
     */
    public static final Sequence<Integer> VEHICLE_RENT_DATES_ID_SEQ = Internal.createSequence("vehicle_rent_dates_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.vehicles_id_seq</code>
     */
    public static final Sequence<Integer> VEHICLES_ID_SEQ = Internal.createSequence("vehicles_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);
}
