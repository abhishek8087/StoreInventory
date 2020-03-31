package com.megastore.StoreInventory.repository;


import com.megastore.StoreInventory.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {

    List<Category> findAll();

    Category findByCategoryId(Long id);

}
