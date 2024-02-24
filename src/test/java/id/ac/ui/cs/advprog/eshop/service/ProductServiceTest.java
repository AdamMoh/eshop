package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepositoryImpl productRepositoryImpl;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        when(productRepositoryImpl.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepositoryImpl, times(1)).create(product);
    }

    @Test
    public void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepositoryImpl.findAll()).thenReturn(productList.iterator());

        List<Product> foundProducts = productService.findAll();

        assertEquals(2, foundProducts.size());
        verify(productRepositoryImpl, times(1)).findAll();
    }

    @Test
    public void testDeleteProduct() {
        String productId = "123";
        Product deletedProduct = new Product();
        when(productRepositoryImpl.delete(productId)).thenReturn(deletedProduct);

        Product result = productService.deleteData(productId);

        assertNotNull(result);
        assertEquals(deletedProduct, result);
        verify(productRepositoryImpl, times(1)).delete(productId);
    }


    @Test
    public void testGetProductById() {
        String productId = "456";
        Product expectedProduct = new Product();
        expectedProduct.setProductId(productId);
        when(productRepositoryImpl.findById(productId)).thenReturn(expectedProduct);

        Product retrievedProduct = productService.getById(productId);

        assertNotNull(retrievedProduct);
        assertEquals(expectedProduct, retrievedProduct);
        verify(productRepositoryImpl, times(1)).findById(productId);
    }

    @Test
    public void testUpdateProduct() {
        String productId = "789";
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        when(productRepositoryImpl.update(updatedProduct, productId)).thenReturn(updatedProduct);

        Product result = productService.update(updatedProduct, productId);

        assertNotNull(result);
        assertEquals(updatedProduct, result);
        verify(productRepositoryImpl, times(1)).update(updatedProduct, productId);
    }
}
