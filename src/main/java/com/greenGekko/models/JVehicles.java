package com.greenGekko.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;



@AllArgsConstructor
@Builder
public class JVehicles {

    public final List<JVehicle> vehicles;
}
