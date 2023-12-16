package com.greenGekko.servicies.user_service;

import static com.greenGekko.database.tables.UsersAuth.USERS_AUTH;

import com.greenGekko.enums.CodeStatus;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VerificationListener {

    private final DSLContext dslContext;

    @Async
    public void ListenUsersAuth(String systemCode) throws InterruptedException {
        wait(3 * 1000L * 60);// wait 3 min

        String email = dslContext.selectFrom(USERS_AUTH)
                .where(USERS_AUTH.SYSTEM_CODE.eq(systemCode))
                .and(USERS_AUTH.CODE_STATUS.notEqual(CodeStatus.CODE_CONFIRMED.name()))
                .orderBy(USERS_AUTH.CREATED_AT.desc())
                .limit(1)
                .fetchOptional(USERS_AUTH.EMAIL)
                .orElse("");
        if (email.isEmpty()) {
            dslContext.insertInto(USERS_AUTH)
                    .set(USERS_AUTH.EMAIL, email)
                    .set(USERS_AUTH.SYSTEM_CODE, systemCode)
                    .set(USERS_AUTH.CODE_STATUS, CodeStatus.CODE_EXPIRED.name())
                    .execute();
        }
    }

}
