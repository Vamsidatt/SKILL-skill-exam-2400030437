package com.klef.soa.service;

import com.klef.soa.entity.Laptop;
import java.util.List;

public interface LaptopService {

    Laptop addLaptop(Laptop laptop);
    Laptop updateLaptop(Long laptopId, Laptop laptop);
    String deleteLaptop(Long laptopId);
    List<Laptop> getAllLaptops();
    Laptop getLaptopById(Long laptopId);
    List<Laptop> getLaptopsByBrand(String brand);
    List<Laptop> getLaptopsByProcessor(String processor);
}