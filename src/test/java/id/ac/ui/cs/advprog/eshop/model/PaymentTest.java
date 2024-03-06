package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentTest {

    @Test
    public void testPaymentModel() {
        String id = "123456";
        String method = "VOUCHER";
        String status = "PAID";

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VOUCHER", "ESHOP1234ABC5678");

        Payment payment = new Payment(id, method, status, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("VOUCHER"));
    }

    @Test
    void testCreatePaymentSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("123456789", "VOUCHER", "SUCCESS", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("123456789", "VOUCHER", "REJECTED", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentDefaultStatus() {

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("123456789", "VOUCHER", "CHECKING_STATUS", paymentData);

        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("CHECKING_STATUS", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));

    }
    @Test
    void testCreatePaymentCashDefaultStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("note", "gift");
        Payment payment = new Payment("123456789", "CASH", paymentData);

        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));
        assertEquals("CHECKING_STATUS", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("CASH", payment.getMethod());
        assertEquals("gift", payment.getPaymentData().get("note"));
    }

    @Test
    void testSetStatusToReject(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("123456789", "VOUCHER", paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, ()-> {
            Payment payment = new Payment("123456789", "focer", paymentData);
        });
    }
    @Test
    void testSetStatusToInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOP1234ABC5678");
        Payment payment = new Payment("123456789", "VOUCHER", paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("focer"));

    }

}
