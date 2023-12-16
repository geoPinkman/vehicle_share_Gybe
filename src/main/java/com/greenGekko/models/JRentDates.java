package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class  JRentDates {

    @JsonProperty("listOfRent")
    public final List<RentFromTo> listOfRents;

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class RentFromTo {

        @JsonProperty("dateFrom")
        public final LocalDate dateFrom;
        @JsonProperty("dateTo")
        public final LocalDate dateTo;

    }
}

