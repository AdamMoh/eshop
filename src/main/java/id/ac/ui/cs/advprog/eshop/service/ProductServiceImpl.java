package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public Product create(Product product) {
        productRepositoryImpl.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product deleteData(String id) {
        return productRepositoryImpl.delete(id);
    }

    @Override
    public Product getById(String dataId) {
        return productRepositoryImpl.findById(dataId);
    }

    @Override
    public Product update(Product updatedProduct, String newDataId) {
        return productRepositoryImpl.update(updatedProduct, newDataId);
    }
}
