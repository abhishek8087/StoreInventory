package com.megastore.StoreInventory.controller;

import com.megastore.StoreInventory.entity.Brand;
import com.megastore.StoreInventory.entity.Category;
import com.megastore.StoreInventory.entity.Product;
import com.megastore.StoreInventory.entity.Store;
import com.megastore.StoreInventory.repository.StoreRepo;
import com.megastore.StoreInventory.service.CatalogService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogControllerTest {

    @Autowired
    private StoreRepo storeRepo;

    Product product;

    Brand brand;

    Category category;

    Store store;

    @LocalServerPort
    private int port;

    @Autowired
    CatalogQueryController catalogQueryController;


    @Autowired
    TestRestTemplate restTemplate;

    Map<String,List<Product>> groupBrand;

    @MockBean
    CatalogService catalogService;

    @BeforeEach
    void initSet(){
        groupBrand = new HashMap<>();

        brand = new Brand(1L,"Puma");
        category = new Category(1L, "Tshirt");
        product = new Product(1L,"Tshirt Sleeve","Red",32,1L,1L,1002.00,brand,category);
        store = new Store(1L,"bigBazar",1L);
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        groupBrand.put("Puma",products);
        Mockito.when(catalogService.addStore(any(Store.class))).thenReturn(store);
        Mockito.when(catalogService.groupByBrand()).thenReturn(groupBrand);
    }

    @Test
    public void contextLoads(){
        Assertions.assertThat(catalogQueryController).isNotNull();
    }

    @Test
    public void getsStore(){
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:9090/catalog/bigBazar/product/count",Integer.class)).isEqualTo(9);
    }

    @Test
    void addStoreTest(){
        Store store = new Store();
        Assertions.assertThat(catalogService.addStore(store)).isEqualTo(this.store);
    }

    @Test
    void groupByBrandTest(){
        Assertions.assertThat(catalogService.groupByBrand()).isEqualTo(groupBrand);
    }





}
