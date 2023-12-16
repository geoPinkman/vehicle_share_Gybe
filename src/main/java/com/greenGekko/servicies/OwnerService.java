package com.greenGekko.servicies;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.greenGekko.database.tables.Owners.OWNERS;
import static com.greenGekko.database.tables.OwnerOptions.OWNER_OPTIONS;
import com.greenGekko.database.tables.records.OwnersRecord;
import com.greenGekko.exceptions.InputDataException;
import com.greenGekko.exceptions.ResourceException;
import com.greenGekko.exceptions.ResourceNotFoundException;
import com.greenGekko.models.*;
import com.greenGekko.utils.ValidationPhoneNumber;
import lombok.*;
import org.jooq.DSLContext;
import org.jooq.Record;

import lombok.extern.slf4j.Slf4j;
import org.jooq.UpdateQuery;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OwnerService {

    private final DSLContext dslContext;
    private final VehicleService vehicleService;
    private final OwnerOptionsService optionsService;
    private final ValidationPhoneNumber validationPhoneNumber;

    private JOwnerCardForCustomer parseOwner(Record r, List<JVehiclesByOwner> list) {
        return JOwnerCardForCustomer.builder()
                .firstname(r.get(OWNERS.FIRSTNAME))
                .lastname(r.get(OWNERS.LASTNAME))
                .companyName(r.get(OWNERS.COMPANY_NAME))
                .address(parseStringToAddress(r.get(OWNERS.ADDRESS)))
                .ownerOptions(optionsService.parseOptions(r))
                .phoneNumber(r.get(OWNERS.PHONE_NUMBER))
                .vehiclesList(list)
                .build();
    }

    private JOwnerCardForCustomer parseOwner(Record r) {
        return parseOwner(r, new ArrayList<>());
    }

    private String parseAddressToString(JAddress address) {
        return String.format("%s; %s; %s; %s; %s",
                address.buildNumber, address.street, address.district, address.town, address.mailIndex);
    }

    private String[] parseAddressToMassive(JAddress address) {
        int BUILD = 0; int STREET = 1; int DISTRICT = 2; int TOWN = 3; int INDEX = 4;
        String[] fieldsOfAddress = new String[5];
        fieldsOfAddress[BUILD] = address.buildNumber;
        fieldsOfAddress[STREET] = address.street;
        fieldsOfAddress[DISTRICT] = address.district;
        fieldsOfAddress[TOWN] = address.town;
        fieldsOfAddress[INDEX] = address.mailIndex;
        return fieldsOfAddress;
    }

    private JAddress parseStringToAddress(String str) {
        int BUILD = 0; int STREET = 1; int DISTRICT = 2; int TOWN = 3; int INDEX = 4;
        String [] partsOfAddress = str.split("; ");
        return JAddress.builder()
                .buildNumber(partsOfAddress[BUILD])
                .street(partsOfAddress[STREET])
                .district(partsOfAddress[DISTRICT])
                .town(partsOfAddress[TOWN])
                .mailIndex(partsOfAddress[INDEX])
                .build();
    }

    private String changeFieldsOfAddress(String oldAddress, JAddress newAddress) {
        int BUILD = 0; int STREET = 1; int DISTRICT = 2; int TOWN = 3; int INDEX = 4;
        String[] fieldsOfOldAddress = new String[5];
        fieldsOfOldAddress = oldAddress.split("; ");
        String[] fieldsOfNewAddress = new String[5];
        fieldsOfNewAddress = parseAddressToMassive(newAddress);
        for (int i = 0; i < fieldsOfOldAddress.length; i++) {
            if (fieldsOfNewAddress[i] == null
                    || fieldsOfNewAddress[i].isEmpty()
                    || fieldsOfOldAddress[i].equals(fieldsOfNewAddress[i])) {
                continue;
            }
            fieldsOfOldAddress[i] = fieldsOfNewAddress[i];
        }
        return fieldsOfOldAddress[BUILD] + "; " +
                fieldsOfOldAddress[STREET] + "; " +
                fieldsOfOldAddress[DISTRICT] + "; " +
                fieldsOfOldAddress[TOWN] + "; " +
                fieldsOfOldAddress[INDEX];
    }

    private boolean isOwnerPresent(int id) {
        return dslContext.select(OWNERS.ID)
                .from(OWNERS)
                .where(OWNERS.ID.eq(id))
                .fetchOptional()
                .isPresent();
    }

    public JOwnerCardForCustomer getOwnerById(int id) {
        List<JVehiclesByOwner> list = vehicleService.getAllVehiclesByOwnerId(id);

        return dslContext.select().from(OWNERS)
                .join(OWNER_OPTIONS).on(OWNERS.ID.eq(OWNER_OPTIONS.OWNER_ID))
                .where(OWNERS.ID.eq(id))
                .fetchOptional(r -> parseOwner(r, list != null ? list : new ArrayList<>()))
                .orElseThrow(() -> new ResourceNotFoundException("Owner with id " + id + " not found"));
    }
    //@TODO делать через SpringSecurity
//    public Integer createNewOwner(UpdateOwnerPrivateParams params) {
//        if (ValidationEmail.isValid(params.email)) {
//            int id = dslContext.select(OWNERS.ID)
//                    .from(OWNERS)
//                    .where(OWNERS.EMAIL.equalIgnoreCase(params.email))
//                    .fetchAny()
//                    .value1();
//            //String encodedPassword = bCryptPasswordEncoder.encode(password);
//            if (id == 0) {
//                int ownerId = dslContext.insertInto(OWNERS)
//                        .set(OWNERS.CREATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
//                        .set(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
//                        .set(OWNERS.UUID, String.valueOf(UUID.randomUUID()))
//                        .returning()
//                        .fetchOne()
//                        .getId();
//                optionsService.createOptions(ownerId);
//                return ownerId;
//            }
//            throw new ResourceException("User with email: " + params.email + " was already created");
//        }
//        throw new InputDataException("Wrong format email: " + params.email);
//    }
    //@TODO придумать механизм проверок прежде чем начать update
    public JOwnerCardForCustomer updatePrivateParams(int id, UpdateOwnerPrivateParams params) {
        if (isOwnerPresent(id)) {

        }
        throw new ResourceException("");
    }

    public JOwnerCardForCustomer updateOptions(int id, JOwnerCardForCustomer.JOwnerOptions options) {
        if (isOwnerPresent(id)) {
            optionsService.updateOptionsByOwnerId(id, options);
            dslContext.update(OWNERS)
                    .set(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                    .where(OWNERS.ID.eq(id))
                    .execute();
            return getOwnerById(id);
        }
        throw new ResourceException();
    }

    public JOwnerCardForCustomer addPublicParamsToOwner(int id, UpdateOwnerPublicParams params) {
        dslContext.update(OWNERS)
                .set(OWNERS.FIRSTNAME, params.firstname)
                .set(OWNERS.LASTNAME, params.lastname)
                .set(OWNERS.COMPANY_NAME, params.companyName)
                .set(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                .where(OWNERS.ID.eq(id))
                .execute();
        return getOwnerById(id);
    }

    public JOwnerCardForCustomer updatePublicParams(int id, UpdateOwnerPublicParams params) {
        if (isOwnerPresent(id)) {
            UpdateQuery<OwnersRecord> updateQuery = dslContext.updateQuery(OWNERS);
            if (params.firstname != null) {
                updateQuery.addValue(OWNERS.FIRSTNAME, params.firstname);
            }
            if (params.lastname != null) {
                updateQuery.addValue(OWNERS.LASTNAME, params.lastname);
            }
            if (params.companyName != null) {
                updateQuery.addValue(OWNERS.COMPANY_NAME, params.companyName);
            }
            updateQuery.addValue(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC));
            updateQuery.addConditions(OWNERS.ID.eq(id));
            updateQuery.execute();
            return getOwnerById(id);
        }
        throw new ResourceException("");
    }

//    public JOwnerCardForCustomer addPhoneNumberToOwner(int id, String phoneNumber) {
//
//        return null;
//    }

    public JOwnerCardForCustomer updatePhoneNumber(int id, String phoneNumber) {
        if (validationPhoneNumber.isValid(phoneNumber)) {
            if (isOwnerPresent(id)) {
                dslContext.update(OWNERS)
                        .set(OWNERS.PHONE_NUMBER, phoneNumber)
                        .set(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                        .where(OWNERS.ID.eq(id))
                        .execute();
            }
            throw new ResourceException();
        }
        throw new InputDataException();
    }

    public JOwnerCardForCustomer addAddressToOwner(int id, JAddress address) {
        dslContext.update(OWNERS)
                .set(OWNERS.ADDRESS, parseAddressToString(address))
                .set(OWNERS.UPDATED_AT, LocalDateTime.now().atOffset(ZoneOffset.UTC))
                .where(OWNERS.ID.eq(id))
                .execute();
        return getOwnerById(id);
    }

    public JOwnerCardForCustomer updateAddress(int id, JAddress address) {
        String oldAddress = dslContext.select(OWNERS.ADDRESS)
                .from(OWNERS)
                .where(OWNERS.ID.eq(id))
                .fetchAny()
                .get(OWNERS.ADDRESS);
        if (oldAddress == null || oldAddress.isEmpty()) {
            return addAddressToOwner(id, address);
        }
        String newAddress = changeFieldsOfAddress(oldAddress, address);
        dslContext.update(OWNERS)
                .set(OWNERS.ADDRESS, newAddress)
                .where(OWNERS.ID.eq(id))
                .execute();

        return getOwnerById(id);
    }

    public void deleteOwnerById(int id) {
        dslContext.deleteFrom(OWNERS)
                .where(OWNERS.ID.eq(id))
                .execute();
    }

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class UpdateOwnerPublicParams {
        @JsonProperty("firstname")
        public final String firstname;
        @JsonProperty("lastname")
        public final String lastname;
        @JsonProperty("companyName")
        public final String companyName;
    }
    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class UpdateOwnerPrivateParams {
        @JsonProperty("email")
        public final String email;
        @JsonProperty("password")
        public final String password;
    }

}
