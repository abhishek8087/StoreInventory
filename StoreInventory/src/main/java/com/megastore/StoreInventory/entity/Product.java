package com.megastore.StoreInventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Table(name = "Product")
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 12L;

    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name="product_generator", sequenceName = "product_seq", allocationSize=500)
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_color")
    private String productColor;
    @Column(name = "product_size")
    private Integer productSize;
    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;
    @Column(name = "brand_id", insertable = false, updatable = false)
    private Long brandId;
    @Column(name = "product_price")
    private Double productPrice;


    @OneToOne(mappedBy = "product")
    @JsonManagedReference(value = "prod-ref")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonBackReference(value = "brand-ref")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    @JsonBackReference(value = "category-ref")
    private Category category;

    public Product(Long productId, String productName, String productColor, Integer productSize, Long categoryId, Long brandId, Double productPrice, Brand brand, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.productPrice = productPrice;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productSize=" + productSize +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", productPrice=" + productPrice +
                ", brand=" + brand +
                ", category=" + category +
                '}';
    }
}
