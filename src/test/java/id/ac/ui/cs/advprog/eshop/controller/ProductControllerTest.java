package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        assertEquals("ProductList", viewName);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testProductDelete() {
        String productId = "123";
        String viewName = productController.productDelete(productId);
        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).deleteData(productId);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        when(productService.getById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(model, productId);

        assertEquals("EditProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        String productId = "123";
        Product product = new Product();

        String viewName = productController.editProductPost(product, productId);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).update(product, productId);
    }
}
