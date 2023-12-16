package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JVehiclePattern {

    @JsonProperty("vehicleType")
    public final String vehicleType;
    @JsonProperty("company")
    public final String company;
    @JsonProperty("model")
    public final String model;
    @JsonProperty("color")
    public final String color;
    @JsonProperty("gearbox")
    public final String gearbox;
    @JsonProperty("engineValue")
    public final int engineValue;
    @JsonProperty("yearOfIssue")
    public final int yearOfIssue;

}
