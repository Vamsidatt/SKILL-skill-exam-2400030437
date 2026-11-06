package com.klef.soa.service;

import com.klef.soa.entity.Laptop;
import com.klef.soa.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Laptop addLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop updateLaptop(Long laptopId, Laptop updatedLaptop) {
        Laptop existingLaptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new RuntimeException("Laptop not found with id: " + laptopId));

        existingLaptop.setBrand(updatedLaptop.getBrand());
        existingLaptop.setProcessor(updatedLaptop.getProcessor());
        existingLaptop.setRam(updatedLaptop.getRam());
        existingLaptop.setStorage(updatedLaptop.getStorage());
        existingLaptop.setPrice(updatedLaptop.getPrice());

        return laptopRepository.save(existingLaptop);
    }

    @Override
    public String deleteLaptop(Long laptopId) {
        if (!laptopRepository.existsById(laptopId)) {
            return "Laptop not found with id: " + laptopId;
        }
        laptopRepository.deleteById(laptopId);
        return "Laptop deleted successfully with ID: " + laptopId;
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop getLaptopById(Long laptopId) {
        return laptopRepository.findById(laptopId)
                .orElseThrow(() -> new RuntimeException("Laptop not found with id: " + laptopId));
    }

    @Override
    public List<Laptop> getLaptopsByBrand(String brand) {
        return laptopRepository.findByBrand(brand);
    }

    @Override
    public List<Laptop> getLaptopsByProcessor(String processor) {
        return laptopRepository.findByProcessor(processor);
    }
}