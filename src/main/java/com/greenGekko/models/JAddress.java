package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"buildNumber", "street", "district", "town", "mailIndex"})
public class JAddress {

    public final String buildNumber;
    public final String street;
    public final String district;
    public final String town;
    public final String mailIndex;


}
