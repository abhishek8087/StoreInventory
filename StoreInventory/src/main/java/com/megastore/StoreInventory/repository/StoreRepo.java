package com.megastore.StoreInventory.repository;

import com.megastore.StoreInventory.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends CrudRepository<Store, Long> {

    long countByStoreName(String storeName);

}
