package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {

    public Product create(Product product);
    public List<Product> findAll();

    public String deleteData(String id);

    public Product getById(String dataId);

    public Product update(Product updatedProduct, String newDataId);
}
