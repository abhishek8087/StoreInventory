package com.megastore.StoreInventory.repository;

import com.megastore.StoreInventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


   Product findByProductId(Long id);
   List<Product> findByBrandId(Long id);
   List<Product> findByProductColor(String color);
}
