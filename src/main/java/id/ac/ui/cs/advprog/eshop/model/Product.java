package id.ac.ui.cs.advprog.eshop.model;

import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Data
public class Product {
    private static int productIdCounter = 1;
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        this.productId = String.valueOf(productIdCounter++);
    }
}
