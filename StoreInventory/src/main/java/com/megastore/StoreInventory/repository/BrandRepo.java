package com.megastore.StoreInventory.repository;

import com.megastore.StoreInventory.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {

    Brand findByBrandId(Long id);
    List<Brand> findByBrandName(String brandName);
}
