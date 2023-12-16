package com.greenGekko.controllers;

import com.greenGekko.models.JVehicle;
import com.greenGekko.models.JVehiclePattern;
import com.greenGekko.servicies.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final VehicleService vehicleService;

    //@ModelAttribute
    public List<JVehicle> getList(){
        return vehicleService.getAllVehicles();
    }
    @ModelAttribute
    public List<JVehicle> getVehicles() {
        List<JVehicle> jVehicles = new ArrayList<>();
        JVehicle a = JVehicle.builder()
                .logo("logo.png")
                .uuid("3828b12b-29b2-4de7-838b-f2e883393b8f")
                .pricePerDay(300)
                .pricePerMonth(4000)
                .deposit(4000)
                .vehiclePattern(JVehiclePattern.builder()
                        .vehicleType("moto")
                        .company("yamaha")
                        .model("gtx")
                        .color("grey")
                        .engineValue(125)
                        .gearbox("auto")
                        .yearOfIssue(2021)
                        .build())
                .build();
        JVehicle b = JVehicle.builder()
                .logo("logo2.png")
                .uuid("3828b12b-29b2-4de7-838b-f2e883393b8f")
                .pricePerDay(350)
                .pricePerMonth(5000)
                .deposit(4000)
                .vehiclePattern(JVehiclePattern.builder()
                        .vehicleType("moto")
                        .company("toyota")
                        .model("juke")
                        .color("black")
                        .engineValue(125)
                        .gearbox("auto")
                        .yearOfIssue(2022)
                        .build())
                .build();
        JVehicle c = JVehicle.builder()
                .logo("logo22.png")
                .uuid("3828b12b-29b2-4de7-838b-f2e883393b8f")
                .pricePerDay(200)
                .pricePerMonth(3000)
                .deposit(3000)
                .vehiclePattern(JVehiclePattern.builder()
                        .vehicleType("moto")
                        .company("honda")
                        .model("gtr")
                        .color("blue")
                        .engineValue(300)
                        .gearbox("auto")
                        .yearOfIssue(2019)
                        .build())
                .build();
        jVehicles.add(a);
        jVehicles.add(b);
        jVehicles.add(c);
        return jVehicles;
    }

    @GetMapping()
    public String home(Model model,
                       @ModelAttribute List<JVehicle> vehicles) {
        log.info("list {}", vehicles);
        model.addAttribute("vehicles", vehicles);
        List<JVehicle> veh3 = getVehicles();
        log.info("veh3 {}", veh3);

        return "home";
    }

}
