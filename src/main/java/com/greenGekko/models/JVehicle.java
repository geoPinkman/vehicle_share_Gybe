package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JVehicle {
    @JsonProperty("uuid")
    public final String uuid;
    @JsonProperty("owner")
    public final JOwner owner;
    @JsonProperty("vehiclePattern")
    public final JVehiclePattern vehiclePattern;
    @JsonProperty("pricePerDay")
    public final int pricePerDay;
    @JsonProperty("pricePerMonth")
    public final int pricePerMonth;
    @JsonProperty("deposit")
    public final int deposit;
    @JsonProperty("description")
    public final String description;
    @JsonProperty("logo")
    public final String logo;
}
