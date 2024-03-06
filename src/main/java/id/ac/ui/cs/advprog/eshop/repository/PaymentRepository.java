package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentList = new ArrayList<>();

    public Payment save(Payment payment){
        if (payment.getMethod().equals(PaymentMethod.VOUCHER.getValue())){
            String status = checkVoucherCode(payment.getPaymentData().get("voucherCode"));
            payment.setStatus(status);

        } else if (payment.getMethod().equals(PaymentMethod.COD.getValue())) {
            String status = checkCODPaymentData(payment.getPaymentData(), payment.getMethod());
            payment.setStatus(status);

        }

        paymentList.add(payment);
        return payment;
    }
    public Payment findById(String id){
        for (Payment savedPayment: paymentList){
            if (savedPayment.getId().equals(id)){
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> findAll(){
        return new ArrayList<>(paymentList);
    }

    private String checkVoucherCode(String voucherCode){
        int numCounter = 0;

        for (int i = 0; i< voucherCode.length(); i++){
            if (Character.isDigit(voucherCode.charAt(i))){
                numCounter += 1;
            }
        }

        if (voucherCode.matches("^ESHOP")  && voucherCode.length() == 16 && numCounter == 8){
            return PaymentStatus.SUCCESS.getValue();
        } else {
            return PaymentStatus.REJECTED.getValue();
        }
    }

    private String checkCODPaymentData(HashMap<String, String> paymentData, String method){
        if(paymentData.containsValue(null) || paymentData.containsValue("") && method.equals(PaymentMethod.COD.getValue())){
            return PaymentStatus.REJECTED.getValue();
        }else{
            return PaymentStatus.SUCCESS.getValue();
        }
    }
}
