package com.greenGekko.servicies.user_service;

import static com.greenGekko.database.tables.Users.USERS;

import com.greenGekko.dto.UserRegistrationDto;
import com.greenGekko.exceptions.ResourceException;
import com.greenGekko.exceptions.ResourceNotFoundException;
import com.greenGekko.models.JUser;
import com.greenGekko.servicies.RolesService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Slf4j
public class UsersService {

    private final DSLContext dslContext;
    private final RolesService rolesService;
    private final UserRoleService userRoleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private JUser parseUser(Record r, Set<JUser.JRole> roles) {
        return JUser.builder()
                .email(r.get(USERS.EMAIL))
                .userPassword(r.get(USERS.USER_PASSWORD))
                .uuid(r.get(USERS.UUID))
                .rolesSet(roles)
                .build();
    }

    public JUser createUser(UserRegistrationDto user) {
        int id;
        if (dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.equalIgnoreCase(user.getEmail()))
                .fetchOptional(USERS.ID)
                .isEmpty()) {
            id = dslContext.insertInto(USERS)
                    .set(USERS.UUID, UUID.randomUUID().toString())
                    .set(USERS.EMAIL, user.getEmail())
                    .set(USERS.USER_PASSWORD, bCryptPasswordEncoder.encode(user.getUserPassword()))
                    .set(USERS.CREATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                    .set(USERS.UPDATED_AT,LocalDateTime.now().atOffset(ZoneOffset.UTC))
                    .returning()
                    .fetchOne()
                    .getId();
            userRoleService.create(id, 1);
            return getUserByEmail(user.getEmail());
        }
        log.info("*** LOG *** User with {} already exist", user.getEmail());
        throw new ResourceException("User with " + user.getEmail() + " already exist");
    }

    public JUser getUserByEmail(String email){
        Set<JUser.JRole> setRoles = rolesService.getRolesByUserEmail(email);

        return dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.equalIgnoreCase(email))
                .fetchOptional()
                .map(r -> parseUser(r, setRoles))
                .orElseThrow(() -> new ResourceNotFoundException("User with " + email + " not found"));
    }

    public boolean isUser(String email) {
        return dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.equalIgnoreCase(email))
                .fetchOptional()
                .isPresent();

    }

    public String getUuidByEmail(String email) {
        return dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.equalIgnoreCase(email))
                .fetchOptional(USERS.UUID)
                .orElse("");
    }
}
