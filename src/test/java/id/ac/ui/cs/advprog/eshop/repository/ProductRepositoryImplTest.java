package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @InjectMocks
    ProductRepositoryImpl productRepositoryImpl;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();

        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepositoryImpl.create(product);

        Iterator<Product> productIterator = productRepositoryImpl.findAll();

        assertTrue(productIterator.hasNext());
        Product savedproduct = productIterator.next();
        assertEquals(product.getProductId(), savedproduct.getProductId());
        assertEquals(product.getProductName(), savedproduct.getProductName());
        assertEquals(product.getProductQuantity(), savedproduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {

        Product product1 = new Product();

        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepositoryImpl.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepositoryImpl.create(product2);

        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void deleteProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepositoryImpl.create(product1);

        productRepositoryImpl.delete(product1.getProductId());

        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductMoreThanOne() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepositoryImpl.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepositoryImpl.create(product2);

        productRepositoryImpl.delete(product1.getProductId());

        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNotExist() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepositoryImpl.create(product);

        Product dummyProduct = new Product();
        dummyProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepositoryImpl.delete(dummyProduct.getProductId());

        Iterator<Product> productIterator = productRepositoryImpl.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductPositive() {
        // Create a product and add it to the repository
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepositoryImpl.create(product);

        // Modify the product's name
        product.setProductName("Sampo Cap Usep");

        // Update the product in the repository
        productRepositoryImpl.update(product, product.getProductId());

        // Retrieve the product from the repository
        Product updatedProduct = productRepositoryImpl.findById(product.getProductId());

        // Check if the product name has been updated
        assertEquals("Sampo Cap Usep", updatedProduct.getProductName());
    }

    @Test
    void testEditProductNegative() {
        // Create a product and add it to the repository
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepositoryImpl.create(product);

        Product newProduct = new Product();
        newProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        newProduct.setProductName("Sampo Cap Usep");
        newProduct.setProductQuantity(50);

        productRepositoryImpl.update(newProduct, newProduct.getProductId());

        Product retrievedProduct = productRepositoryImpl.findById(product.getProductId());

        assertEquals("Sampo Cap Bambang", retrievedProduct.getProductName());
        assertEquals(100, retrievedProduct.getProductQuantity());
    }

}
