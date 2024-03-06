package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;
    List<Payment> paymentList;

    List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setUp(){
        paymentList = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(3);
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        HashMap<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), paymentData1);
        paymentList.add(payment1);

        HashMap<String, String> paymentData2 = new HashMap<>();
        paymentData1.put("address", "tnb");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078",PaymentMethod.COD.getValue(), paymentData2);
        paymentList.add(payment2);
    }

    @Test
    void testSetStatusSuccess(){
        Payment payment = paymentList.get(1);
        Order order = orders.get(1);

        doReturn(payment).when(paymentRepository).save(payment);
        doReturn(order).when(orderRepository).save(order);
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(paymentRepository,times(1)).save(any(Payment.class));
        verify(orderRepository,times(1)).save(any(Order.class));
        verify(orderRepository,times(1)).findById(any(String.class));


        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }
    @Test
    void testSetStatusRejected(){
        Payment payment = paymentList.get(1);
        Order order = orders.get(1);

        doReturn(payment).when(paymentRepository).save(payment);
        doReturn(order).when(orderRepository).save(order);
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository,times(1)).save(any(Payment.class));
        verify(orderRepository,times(1)).save(any(Order.class));

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusInvalid(){
        Payment payment = paymentList.getFirst();
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "xxxxx"));
    }

    @Test
    void testAddPayment(){
        Payment payment = paymentList.getFirst();
        Order order = orders.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testFindByIdIfNotFound(){
        doReturn(null).when(paymentRepository).findById("focer");
        assertNull(paymentService.getPayment("focer"));
    }

    @Test
    void testGetAllPaymentIfNotEmpty(){
        doReturn(paymentList).when(paymentRepository).findAll();
        List <Payment> results = paymentService.getAllPayments();

        assertEquals(paymentList.size(), results.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty(){
        List <Payment> results = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();

        assertTrue(results.isEmpty());
    }

}