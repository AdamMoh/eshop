package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> foundProducts = productService.findAll();

        assertEquals(2, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        String productId = "123";
        Product deletedProduct = new Product(); // Create a dummy product for deletion
        when(productRepository.delete(productId)).thenReturn(deletedProduct);

        // Act
        Product result = productService.deleteData(productId);

        // Assert
        assertNotNull(result);
        assertEquals(deletedProduct, result);
        verify(productRepository, times(1)).delete(productId);
    }


    @Test
    public void testGetProductById() {
        String productId = "456";
        Product expectedProduct = new Product();
        expectedProduct.setProductId(productId); // Set product ID
        when(productRepository.findById(productId)).thenReturn(expectedProduct);

        Product retrievedProduct = productService.getById(productId);

        assertNotNull(retrievedProduct);
        assertEquals(expectedProduct, retrievedProduct);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testUpdateProduct() {
        String productId = "789";
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        when(productRepository.update(updatedProduct, productId)).thenReturn(updatedProduct);

        Product result = productService.update(updatedProduct, productId);

        assertNotNull(result);
        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).update(updatedProduct, productId);
    }
}
