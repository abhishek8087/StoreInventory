package com.megastore.StoreInventory.service;

import com.megastore.StoreInventory.entity.Brand;
import com.megastore.StoreInventory.entity.Category;
import com.megastore.StoreInventory.entity.Product;
import com.megastore.StoreInventory.entity.Store;
import com.megastore.StoreInventory.repository.BrandRepo;
import com.megastore.StoreInventory.repository.CategoryRepository;
import com.megastore.StoreInventory.repository.ProductRepo;
import com.megastore.StoreInventory.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepo brandRepo;


    @Autowired
    StoreRepo storeRepo;

    @Transactional
    public Product createProduct(Product product){
       return productRepo.save(product);
    }

    public Category getByCategoryId(Long id){
        return categoryRepository.findByCategoryId(id);
    }

    public Brand getByBrandId(Long id){
        return brandRepo.findByBrandId(id);
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Store addStore(Store store){
        store.setProduct(productRepo.findByProductId(store.getProductId()));
        return storeRepo.save(store);
    }


    public Map<String, List<Product>> groupByColor(){
        List<Product> products = productRepo.findAll();
        System.out.println(products);
        Map<String, List<Product>> groupByColor =
                products.stream().collect(Collectors.groupingBy(w -> w.getProductColor()));
        System.out.println(groupByColor);
        return groupByColor;
    }

    public Map<Double, List<Product>> groupByPrice(){
        List<Product> products = productRepo.findAll();
        System.out.println(products);
        Map<Double, List<Product>> groupByPrice =
                products.stream().collect(Collectors.groupingBy(w -> w.getProductPrice()));
        System.out.println(groupByPrice);
        return groupByPrice;
    }

    public Map<String, List<Product>> groupByBrand(){
        List<Product> products = productRepo.findAll();
        System.out.println(products);
        Map<String, List<Product>> groupByBrand =
                products.stream().collect(Collectors.groupingBy(w -> w.getBrand().getBrandName()));
        System.out.println(groupByBrand);
        return groupByBrand;
    }




    public Map<Integer, List<Product>> groupBySize(){

        List<Product> products = productRepo.findAll();
        System.out.println(products);
        Map<Integer, List<Product>> groupBySize =
                products.stream().collect(Collectors.groupingBy(w -> w.getProductSize()));
        System.out.println(groupBySize);
        return groupBySize;
    }

    public Product getBySKU(Long id){
        return productRepo.findByProductId(id);
    }

    public long countByStore(String storeName){
        return storeRepo.countByStoreName(storeName);
    }


    public void setup(){
        Random random = new Random();
        String[] brands = {"Puma", "Addidas", "Nike", "Tommy","Monte"};

        for(String brand : brands){

            brandRepo.save(new Brand(null,brand));
        }


        String[] categories = {"Caps", " Shirts", " Pants", "Sweaters", " Shoes"};

        for(String category : categories){
            categoryRepository.save(new Category(null,category));
        }

        String[] productNames = {"Sleeves" , " SleeveLess", "Skinny", "Fit", "Test"};
        Double[] productPrice = {100.23,109.34,11223.4,12.3,11.2};
        String[] colorNames = {"Red", "Blue", "Green", "Yellow","Purple"};
        Integer[] size = {42,41,32,33,36};



        for(int i=0 ; i<10 ; i++ ){

            Category category = new Category();
            category.setCategoryId(new Long(random.nextInt(3)+1));
            Brand brand = new Brand();
            brand.setBrandId(new Long(random.nextInt(3)+1));
            Product product = new Product(null,productNames[random.nextInt(4)],colorNames[random.nextInt(4)],size[random.nextInt(4)]
                    ,new Long(random.nextInt(4)),new Long(random.nextInt(4)),productPrice[random.nextInt(4)],brand,category);

            productRepo.save(product);

        }
        int i =1;
        while(i<10){
            storeRepo.save(new Store(null,"bigBazar",new Long(random.nextInt(9))));
            i++;
        }
    }







}
