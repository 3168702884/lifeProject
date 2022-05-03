package com.example.classicback;

import com.example.classicback.controller.CentralController.UserMangeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClassicBackendApplicationTests {
    @Autowired
    private UserMangeController userMangeController;

    @Test
    void contextLoads() {
        userMangeController.getAccess();
    }

}
