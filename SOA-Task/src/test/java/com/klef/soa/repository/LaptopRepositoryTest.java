package com.klef.soa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.klef.soa.entity.Laptop;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LaptopRepositoryTest {

    @Autowired
    private LaptopRepository laptopRepository;

    private Long sampleId;

    @BeforeEach
    public void setUp() {
        laptopRepository.deleteAll();
        Laptop lap1 = laptopRepository.save(new Laptop(null, "Dell", "Intel i7", 16, "512GB SSD", 75000.0));
        laptopRepository.save(new Laptop(null, "HP", "Intel i5", 8, "1TB HDD", 55000.0));
        laptopRepository.save(new Laptop(null, "Dell", "AMD Ryzen 7", 16, "1TB SSD", 85000.0));
        sampleId = lap1.getLaptopId();
    }

    @Test
    public void testFindAll() {
        List<Laptop> list = laptopRepository.findAll();
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    public void testFindById() {
        Optional<Laptop> found = laptopRepository.findById(sampleId);
        assertTrue(found.isPresent());
        assertEquals(sampleId, found.get().getLaptopId());

        Optional<Laptop> notFound = laptopRepository.findById(99999L);
        assertFalse(notFound.isPresent());
    }

    @Test
    public void testFindByDerivedQuery() {
        List<Laptop> dellLaptops = laptopRepository.findByBrand("Dell");
        assertNotNull(dellLaptops);
        assertEquals(2, dellLaptops.size());
        assertEquals("Dell", dellLaptops.get(0).getBrand());
    }

    @Test
    public void testCount() {
        long totalCount = laptopRepository.count();
        assertEquals(3L, totalCount);
    }

    @Test
    public void testEntityDetailsPropertyBasedResult() {
        Laptop retrieved = laptopRepository.findById(sampleId).orElse(null);
        assertNotNull(retrieved);
        assertNotNull(retrieved.getBrand());
        assertNotNull(retrieved.getProcessor());
        assertNotNull(retrieved.getRam());
        assertNotNull(retrieved.getStorage());
        assertNotNull(retrieved.getPrice());
        assertEquals("Dell", retrieved.getBrand());
    }
}