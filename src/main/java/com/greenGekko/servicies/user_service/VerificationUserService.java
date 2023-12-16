package com.greenGekko.servicies.user_service;

import static com.greenGekko.database.tables.UsersAuth.USERS_AUTH;

import com.greenGekko.enums.CodeStatus;
import com.greenGekko.utils.VerificationCodeGenerator;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationUserService {

    private final DSLContext dslContext;

    public void createNewVerificationCode (String email) {
        String systemCode = VerificationCodeGenerator.codeGenerator(6);
        dslContext.insertInto(USERS_AUTH)
                .set(USERS_AUTH.EMAIL, email)
                .set(USERS_AUTH.SYSTEM_CODE, systemCode)
                .set(USERS_AUTH.CODE_STATUS, CodeStatus.NEW_CODE_GENERATED.name())
                .execute();
    }

    public String getUserEmailByCode(String userCode) {
        return dslContext.selectFrom(USERS_AUTH)
                .where(USERS_AUTH.SYSTEM_CODE.eq(userCode))
                .and(USERS_AUTH.CODE_STATUS.notEqual(CodeStatus.CODE_EXPIRED.name()))
                .orderBy(USERS_AUTH.CREATED_AT.desc())
                .limit(1)
                .fetchOptional(USERS_AUTH.EMAIL)
                .orElse("");
    }

    public void createConfirmVerificationCode(String userCode) {
        String userEmail = getUserEmailByCode(userCode);
        if (!userEmail.isEmpty()) {
            dslContext.insertInto(USERS_AUTH)
                    .set(USERS_AUTH.EMAIL, userEmail)
                    .set(USERS_AUTH.SYSTEM_CODE, userCode)
                    .set(USERS_AUTH.CODE_STATUS, CodeStatus.CODE_CONFIRMED.name())
                    .execute();
        }
    }

}
