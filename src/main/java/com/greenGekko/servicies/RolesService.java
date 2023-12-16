package com.greenGekko.servicies;

import static com.greenGekko.database.tables.UsersRoles.USERS_ROLES;
import static com.greenGekko.database.tables.Users.USERS;
import static com.greenGekko.database.tables.Roles.ROLES;

import com.greenGekko.exceptions.ResourceNotFoundException;
import com.greenGekko.models.JUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RolesService {

    private final DSLContext dslContext;

    private JUser.JRole parseRole(Record r) {
        return JUser.JRole.builder()
                .id(r.get(ROLES.ID))
                .role(r.get(ROLES.ROLE_NAME))
                .build();
    }

    public Set<JUser.JRole> getRolesByUserEmail(String email) {
        return dslContext.select(ROLES.ID, ROLES.ROLE_NAME)
                .from(ROLES)
                .leftJoin(USERS_ROLES).on(ROLES.ID.eq(USERS_ROLES.ROLE_ID))
                .leftJoin(USERS).on(USERS_ROLES.USER_ID.eq(USERS.ID))
                .where(USERS.EMAIL.eq(email))
                .fetchStream()
                .map(this::parseRole)
                .collect(Collectors.toSet());
    }

}
