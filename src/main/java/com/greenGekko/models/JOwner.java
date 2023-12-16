package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greenGekko.servicies.OwnerService;
import lombok.*;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JOwner {
    @JsonProperty("firstname")
    public final String firstname;
    @JsonProperty("lastname")
    public final String lastname;
    @JsonProperty("companyName")
    public final String companyName;
    @JsonProperty("phoneNumber")
    public final String phoneNumber;
    @JsonProperty("address")
    public final JAddress address;
    @JsonProperty("vehicleList")
    public final List<JVehiclesByOwner> vehiclesList;
}
