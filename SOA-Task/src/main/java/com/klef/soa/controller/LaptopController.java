package com.klef.soa.controller;

import com.klef.soa.entity.Laptop;
import com.klef.soa.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PutMapping("/update/{id}")
    public Laptop updateLaptop(@PathVariable("id") Long laptopId, @RequestBody Laptop laptop) {
        return laptopService.updateLaptop(laptopId, laptop);
    }

    @PostMapping("/add")
    public Laptop addLaptop(@RequestBody Laptop laptop) {
        return laptopService.addLaptop(laptop);
    }

    @GetMapping("/displayall")
    public List<Laptop> getAllLaptops() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/display/{id}")
    public Laptop getLaptopById(@PathVariable("id") Long laptopId) {
        return laptopService.getLaptopById(laptopId);
    }

    @GetMapping("/displaybybrand/{brand}")
    public List<Laptop> getLaptopsByBrand(@PathVariable("brand") String brand) {
        return laptopService.getLaptopsByBrand(brand);
    }

    @GetMapping("/displaybyprocessor/{processor}")
    public List<Laptop> getLaptopsByProcessor(@PathVariable("processor") String processor) {
        return laptopService.getLaptopsByProcessor(processor);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable("id") Long laptopId) {
        return laptopService.deleteLaptop(laptopId);
    }
}