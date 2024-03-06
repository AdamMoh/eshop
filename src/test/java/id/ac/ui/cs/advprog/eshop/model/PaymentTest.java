package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("VOUCHER", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment(id, method, status, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("VOUCHER"));
        assertEquals("tnb", payment.getPaymentData().get("address"));
        assertEquals("10000", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testCreatePaymentSuccessStatus(){
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectStatus(){
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentDefaultStatus() {

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", PaymentMethod.VOUCHER.getValue(), PaymentStatus.CHECKING.getValue(), paymentData);

        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("CHECKING_STATUS", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        assertEquals("tnb", payment.getPaymentData().get("address"));
        assertEquals("10000", payment.getPaymentData().get("deliveryFee"));

    }
    @Test
    void testCreatePaymentCashDefaultStatus(){
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("note", "gift");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", "CASH_ON_DELIVERY", "CHECKING_STATUS", paymentData);

        assertEquals("CHECKING_STATUS", payment.getStatus());
        assertEquals("123456789", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals("gift", payment.getPaymentData().get("note"));
        assertEquals("tnb", payment.getPaymentData().get("address"));
        assertEquals("10000", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testSetStatusToReject(){
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("123456789", PaymentMethod.VOUCHER.getValue(), paymentData);

        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");

        assertThrows (IllegalArgumentException.class, ()-> {
            Payment payment = new Payment("123456789", "focer", paymentData);
        });
    }
    @Test
    void testSetStatusToInvalidStatus() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherId", "ESHOP1234ABC5678");
        paymentData.put("address", "tnb");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("123456789", PaymentMethod.VOUCHER.getValue(), PaymentStatus.CHECKING.getValue(), paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("focer"));

    }

}
