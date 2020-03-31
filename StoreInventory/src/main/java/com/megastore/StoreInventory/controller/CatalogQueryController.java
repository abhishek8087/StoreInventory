package com.megastore.StoreInventory.controller;

import com.megastore.StoreInventory.entity.Brand;
import com.megastore.StoreInventory.entity.Category;
import com.megastore.StoreInventory.entity.Product;
import com.megastore.StoreInventory.entity.Store;
import com.megastore.StoreInventory.repository.BrandRepo;
import com.megastore.StoreInventory.repository.CategoryRepository;
import com.megastore.StoreInventory.repository.ProductRepo;
import com.megastore.StoreInventory.repository.StoreRepo;
import com.megastore.StoreInventory.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class CatalogQueryController {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private StoreRepo storeRepo;


    @GetMapping("/category/all")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getByCategoryName(@PathVariable Long id){
        return categoryRepository.findByCategoryId(id);
    }

    @GetMapping("/product/all")
    public List<Product> getAllProducts(){
        return catalogService.getAllProducts();
    }
    @PostMapping("/product/add")
    public Product createProduct(@RequestBody Product product) throws Exception {

        Category category = catalogService.getByCategoryId(product.getCategoryId());
        Brand brand = catalogService.getByBrandId(product.getBrandId());
        if(category != null && brand != null){
            product.setCategory(category);
            product.setBrand(brand);
            return catalogService.createProduct(product);
        }else
            throw new Exception("Either brand or category does not exist");

    }

    @PostMapping(value = "/brand/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand addBrand(@RequestBody Brand brand){
        return brandRepo.save(brand);
    }

    @PostMapping("/category/add")
    public Category addCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @PostMapping("/store/add")
    public Store addStore(@RequestBody Store store){
        return catalogService.addStore(store);
    }



    //4.	Group by Size
    @GetMapping("/size/group")
    public Map<Integer, List<Product>> grpBySize(){
        return catalogService.groupBySize();
    }
    //3.	Group by Color
    @GetMapping("/color/group")
    public Map<String, List<Product>> groupByColor(){
        return catalogService.groupByColor();
    }

    //2.	Group by price
    @GetMapping("/price/group")
    public Map<Double, List<Product>> groupByPrice(){
        return catalogService.groupByPrice();
    }

    //1.	Group by brand / client
    @GetMapping("/brand/group")
    public Map<String, List<Product>> groupByBrand(){
        return catalogService.groupByBrand();
    }
    //5.	Get by SKU
    @GetMapping("/product/{id}")
    public Product getBySKU(@PathVariable Long id){
        return catalogService.getBySKU(id);
    }

    //6.	Available number of product by seller
    @GetMapping("/{storeName}/product/count")
    public long numberOfProducts(@PathVariable String storeName){
        return catalogService.countByStore(storeName);
    }

    @GetMapping("/setup")
    public void setup(){
         catalogService.setup();
    }

}
