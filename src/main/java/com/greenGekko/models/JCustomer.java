package com.greenGekko.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@AllArgsConstructor
@Builder
public class JCustomer {

    public final int id;

    public final String name;

    public final String email;

    public final String phone;

//    public final List<CustomerService.VehicleModelCustomer> vehicles;
}
