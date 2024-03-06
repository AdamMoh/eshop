package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    List<Payment> paymentData;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        paymentData = new ArrayList<>();

        HashMap<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("123456789","VOUCHER", paymentData1);
        paymentData.add(payment1);

        HashMap<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "tnb");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("123456489",PaymentMethod.COD.getValue(), paymentData2);
        paymentData.add(payment2);

    }

    @Test
    void testSaveCreate(){
        Payment payment = paymentData.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(paymentData.get(0).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("voucherCode"));
        assertEquals(payment.getPaymentData().get("voucherCode"), findResult.getPaymentData().get("voucherCode") );
        assertEquals(16, findResult.getPaymentData().get("voucherCode").length());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testSaveVoucherIdInvalid(){
        HashMap<String, String> paymentDataInvalid= new HashMap<>();
        paymentDataInvalid.put("voucherCode", "xxxxxx");
        Payment paymentInvalid = new Payment("123456789",PaymentMethod.VOUCHER.getValue(), paymentDataInvalid);
        paymentRepository.save(paymentInvalid);

        Payment findResult = paymentRepository.findById(paymentInvalid.getId());

        assertEquals(paymentInvalid.getId(), findResult.getId());
        assertEquals(paymentInvalid.getMethod(), findResult.getMethod());
        assertEquals(paymentInvalid.getPaymentData().get("voucherCode"), findResult.getPaymentData().get("voucherCode") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testSaveCODNullDeliveryFee(){
        HashMap<String, String> testPaymentDataInvalid= new HashMap<>();
        testPaymentDataInvalid.put("address", "tnb");
        testPaymentDataInvalid.put("deliveryFee", null);
        Payment invalidData = new Payment("123456789",PaymentMethod.COD.getValue(), testPaymentDataInvalid);
        paymentRepository.save(invalidData);

        Payment findResult = paymentRepository.findById(invalidData.getId());

        assertEquals(invalidData.getId(), findResult.getId());
        assertEquals(invalidData.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("address"));
        assertTrue(findResult.getPaymentData().containsKey("deliveryFee"));
        assertTrue(findResult.getPaymentData().containsValue(null));
        assertEquals(invalidData.getPaymentData().get("address"), findResult.getPaymentData().get("address") );
        assertEquals(invalidData.getPaymentData().get("deliveryFee"), findResult.getPaymentData().get("deliveryFee") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testSaveCODNullInAddress(){
        HashMap<String, String> testPaymentDataInvalid= new HashMap<>();
        testPaymentDataInvalid.put("address", null);
        testPaymentDataInvalid.put("deliveryFee", "100000");
        Payment invalidData = new Payment("123456789",PaymentMethod.COD.getValue(), testPaymentDataInvalid);
        paymentRepository.save(invalidData);

        Payment findResult = paymentRepository.findById(invalidData.getId());

        assertEquals(invalidData.getId(), findResult.getId());
        assertEquals(invalidData.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("address"));
        assertTrue(findResult.getPaymentData().containsKey("deliveryFee"));
        assertTrue(findResult.getPaymentData().containsValue(null));
        assertEquals(invalidData.getPaymentData().get("address"), findResult.getPaymentData().get("address") );
        assertEquals(invalidData.getPaymentData().get("deliveryFee"), findResult.getPaymentData().get("deliveryFee") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testSaveCODEmptyInPaymentData(){
        HashMap<String, String> paymentDataInvalid= new HashMap<>();
        paymentDataInvalid.put("address", "");
        paymentDataInvalid.put("deliveryFee", "10000");
        Payment paymentInvalid = new Payment("123456789", PaymentMethod.COD.getValue(), paymentDataInvalid);
        paymentRepository.save(paymentInvalid);

        Payment findResult = paymentRepository.findById(paymentInvalid.getId());
        assertEquals(paymentInvalid.getId(), findResult.getId());
        assertEquals(paymentInvalid.getMethod(), findResult.getMethod());
        assertTrue(findResult.getPaymentData().containsKey("address"));
        assertTrue(findResult.getPaymentData().containsKey("deliveryFee"));
        assertTrue(findResult.getPaymentData().containsValue(""));
        assertEquals(paymentInvalid.getPaymentData().get("address"), findResult.getPaymentData().get("address") );
        assertEquals(paymentInvalid.getPaymentData().get("deliveryFee"), findResult.getPaymentData().get("deliveryFee") );
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfFound(){
        for (Payment payment: paymentData){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(paymentData.get(0).getId());

        assertEquals(paymentData.get(0).getId(), findResult.getId());
        assertEquals(paymentData.get(0).getMethod(), findResult.getMethod());
        assertEquals(paymentData.get(0).getStatus(), findResult.getStatus());
        assertEquals(paymentData.get(0).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment: paymentData){
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("xxxxx");
        assertNull(findResult);
    }

    @Test
    void testFindAllIfNotEmpty(){
        for (Payment payment: paymentData){
            paymentRepository.save(payment);
        }

        List <Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
    }

    @Test
    void testFindAllIfEmpty(){
        List<Payment> payments = paymentRepository.findAll();

        assertTrue(payments.isEmpty());
    }

}
