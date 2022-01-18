package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TestUtil testUtil;

    private String email;

    @BeforeAll
    public void init() throws ExpressDeliveryException {
        email = testUtil.registerUser();
    }

    @Test
    @Order(1)
    public void testGetAllCustomers(){
        List<UserDto> results = customerService.getAllCustomers();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all Customers [PASSED]");
    }

    @Test
    @Order(2)
    public void testSetBlacklistUser() throws ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        UserDto result = customerService.toggleBlacklist(userDto);

        assertNotNull(result);

        System.out.println("[TEST] Blacklist customer [PASSED]");
    }
}
