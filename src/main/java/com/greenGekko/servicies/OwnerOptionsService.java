package com.greenGekko.servicies;

import static com.greenGekko.database.tables.OwnerOptions.OWNER_OPTIONS;

import com.greenGekko.database.tables.records.OwnerOptionsRecord;
import com.greenGekko.exceptions.ResourceException;
import com.greenGekko.models.JOwnerCardForCustomer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.UpdateQuery;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OwnerOptionsService {

    private final DSLContext dslContext;

    protected JOwnerCardForCustomer.JOwnerOptions parseOptions(Record r) {
        return JOwnerCardForCustomer.JOwnerOptions.builder()
                .option1(r.get(OWNER_OPTIONS.OPTION_1))
                .option2(r.get(OWNER_OPTIONS.OPTION_2))
                .option3(r.get(OWNER_OPTIONS.OPTION_3))
                .build();
    }

    public void createOptions(int ownerId) {
        dslContext.insertInto(OWNER_OPTIONS)
                .set(OWNER_OPTIONS.OWNER_ID, ownerId)
                .execute();
    }

    public JOwnerCardForCustomer.JOwnerOptions getOptionsByOwnerId(int ownerId) {
        return dslContext.selectFrom(OWNER_OPTIONS)
                .where(OWNER_OPTIONS.OWNER_ID.eq(ownerId))
                .fetchOptional()
                .map(this::parseOptions)
                .orElse(null);
    }

    public void updateOptionsByOwnerId(int ownerId, JOwnerCardForCustomer.JOwnerOptions request) {
        JOwnerCardForCustomer.JOwnerOptions options = getOptionsByOwnerId(ownerId);
        if (options != null) {
            UpdateQuery<OwnerOptionsRecord> updateQuery = dslContext.updateQuery(OWNER_OPTIONS);
            if (request.option1 != options.option1) {
                updateQuery.addValue(OWNER_OPTIONS.OPTION_1, request.option1);
            }
            if (request.option2 != options.option2) {
                updateQuery.addValue(OWNER_OPTIONS.OPTION_2, request.option2);
            }
            if (request.option3 != options.option3) {
                updateQuery.addValue(OWNER_OPTIONS.OPTION_3, request.option3);
            }
            updateQuery.addConditions(OWNER_OPTIONS.OWNER_ID.eq(ownerId));
            updateQuery.execute();
        }
        throw new ResourceException("");
    }

    public void makeAllOptionsFalse(int ownerId) {
        dslContext.update(OWNER_OPTIONS)
                .set(OWNER_OPTIONS.OPTION_1, false)
                .set(OWNER_OPTIONS.OPTION_2, false)
                .set(OWNER_OPTIONS.OPTION_3, false)
                .where(OWNER_OPTIONS.OWNER_ID.eq(ownerId))
                .execute();
    }
}
