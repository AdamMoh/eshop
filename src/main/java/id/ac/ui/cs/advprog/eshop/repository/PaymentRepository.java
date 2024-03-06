package id.ac.ui.cs.advprog.eshop.repository;

\
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentCollection = new ArrayList<>();

    public Payment save(Payment payment){
        return null;
    }
    public Payment findById(String id){
        return null;
    }
    public List<Payment> findAll(){
        return null;
    }

    private String checkVoucherId(String voucherId){
        return null;
    }

    private String checkCashData(Map<String, String> paymentData){
        return null;
    }
}
