package com.megastore.StoreInventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "Store")
public class Store {

    @Column(name = "store_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_generator")
    @SequenceGenerator(name="store_generator", sequenceName = "store_seq", allocationSize=500)
    private Long storeId;
    @Column(name = "store_name")
    private String storeName;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @JsonBackReference(value = "prod-ref")
    private Product product;



    public Store(Long storeId, String storeName, Long productId) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
