package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JVehiclesByOwner {
    @JsonProperty("uuid")
    public final String uuid;
    @JsonProperty("pricePerDay")
    public final int pricePerDay;
    @JsonProperty("pricePerMonth")
    public final int pricePerMonth;
    @JsonProperty("deposit")
    public final int deposit;
    @JsonProperty("vehiclePattern")
    public final JVehiclePattern vehiclePattern;
    @JsonProperty("description")
    public final String description;
    @JsonProperty("available")
    public final boolean available;
    @JsonProperty("listOfRentDates")
    public final JRentDates listOfRentDates;

}
