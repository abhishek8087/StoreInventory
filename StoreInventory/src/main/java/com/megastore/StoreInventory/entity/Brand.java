package com.megastore.StoreInventory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "Brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    @SequenceGenerator(name="brand_generator", sequenceName = "brand_seq", allocationSize=500)
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference(value = "brand-ref")
    private Set<Product> product;



    public Brand(Long brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
