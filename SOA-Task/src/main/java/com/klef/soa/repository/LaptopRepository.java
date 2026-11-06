package com.klef.soa.repository;

import com.klef.soa.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findByBrand(String brand);

    List<Laptop> findByProcessor(String processor);
}