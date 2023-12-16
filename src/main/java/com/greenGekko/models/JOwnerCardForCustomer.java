package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JOwnerCardForCustomer {
    @JsonProperty("firstname")
    public final String firstname;
    @JsonProperty("lastname")
    public final String lastname;
    @JsonProperty("companyName")
    public final String companyName;
    @JsonProperty("address")
    public final JAddress address;
    @JsonProperty("ownerOptions")
    public final JOwnerOptions ownerOptions;
    @JsonProperty("phoneNumber")
    public final String phoneNumber;
    @JsonProperty("email")
    public final String email;
    @JsonProperty("vehicleList")
    public final List<JVehiclesByOwner> vehiclesList;

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class JOwnerOptions{

        @JsonProperty("option1")
        public final boolean option1;
        @JsonProperty("option2")
        public final boolean option2;
        @JsonProperty("option3")
        public final boolean option3;
    }
}


