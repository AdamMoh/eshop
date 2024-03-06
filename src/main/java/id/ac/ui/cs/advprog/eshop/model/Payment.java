package id.ac.ui.cs.advprog.eshop.model;



import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;

@Getter
@Builder
public class Payment {

        private String id;
        private String method;
        private String status;
        private HashMap<String, String> paymentData;

        public Payment(String id, String method, HashMap<String, String> paymentData) {

            this.id = id;
            this.status = PaymentStatus.CHECKING.getValue();

            if(!PaymentMethod.contains(method)){
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
        this.id = id;
        this.status = status;

        if(!PaymentMethod.contains(method)){
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

        if(!PaymentStatus.contains(status)){
            throw new IllegalArgumentException();
        }else{
            this.status = status;
        }
    }

}
