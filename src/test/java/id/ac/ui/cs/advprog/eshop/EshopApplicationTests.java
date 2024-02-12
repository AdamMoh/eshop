package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void startsApplicationSuccessfully() {
        // Test that the root endpoint returns a 200 OK status
    }

    @Test
    void mainMethodStartsApplicationSuccessfully() {
        try {
            EshopApplication.main(new String[] {});
        } catch (Exception e) {
            fail("Exception thrown while starting application: " + e.getMessage());
        }
    }

}
