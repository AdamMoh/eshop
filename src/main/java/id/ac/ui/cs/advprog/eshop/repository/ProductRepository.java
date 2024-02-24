package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductRepository {
    public Product create(Product product);
    public Product findById(String id);
    public Product update(Product updatedProduct, String newDataId);
    public Product delete(String id);
}
