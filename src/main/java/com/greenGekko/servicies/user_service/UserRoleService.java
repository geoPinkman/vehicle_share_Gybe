package com.greenGekko.servicies.user_service;

import static com.greenGekko.database.tables.UsersRoles.USERS_ROLES;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final DSLContext dslContext;

    void create(int userId, int roleId) {
        dslContext.insertInto(USERS_ROLES)
                .set(USERS_ROLES.USER_ID, userId)
                .set(USERS_ROLES.ROLE_ID, roleId)
                .execute();
    }

}
