package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public String deleteData(int id) {
        Product dataDetail = productRepository.findById(id);
        List<Product> productIterator = findAll();

        productIterator.remove(dataDetail);

        return "Sukses";
    }

    @Override
    public Product getById(String dataId) {
        return productRepository.findById(Integer.parseInt(dataId));
    }

    @Override
    public Product update(Product updatedProduct, String newDataId) {
        return productRepository.update(updatedProduct, newDataId);
    }
}
