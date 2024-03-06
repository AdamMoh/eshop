package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.HashMap;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment addPayment(Order order, String method, HashMap<String, String> paymentData) {
        return null;
    }

    @Override
    public Payment getPayment(String id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }
}
