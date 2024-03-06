package id.ac.ui.cs.advprog.eshop.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@Getter
//@Setter
@Builder
public class Payment {

        private String id;
        private String method;
        private String status;
        private HashMap<String, String> paymentData;

        public Payment(String id, String method, HashMap<String, String> paymentData) {
            String[] methodList = {"CASH", "COD", "BANK", "VOUCHER"};

            this.id = id;
            this.status = "WAITING_PAYMENT";

            if(Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))){
                throw new IllegalArgumentException();
            }else{
                this.method = method;
            }

            if(paymentData.isEmpty()){
                throw new IllegalArgumentException();
            }else{
                this.paymentData = paymentData;
            }
        }

    public Payment(String id, String method, String status, HashMap<String, String> paymentData) {
        String[] methodList = {"CASH", "COD", "BANK", "VOUCHER"};
        this.id = id;
        this.status = status;

        if(Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))){
            throw new IllegalArgumentException();
        }else{
            this.method = method;
        }

        if(paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            this.paymentData = paymentData;
        }
    }
    public void setStatus(String status){
        String[] statusList = {"REJECTED", "SUCCESS"};

        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        }else{
            this.status = status;
        }
    }

}
