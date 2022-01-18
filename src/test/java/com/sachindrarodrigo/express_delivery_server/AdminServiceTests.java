package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.AdminService;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AdminServiceTests {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TestUtil testUtil;

    private int centerId, vehicleId;

    @BeforeAll
    public void init() throws ExpressDeliveryException {
        centerId = testUtil.createServiceCenter();
        vehicleId = testUtil.createVehicleToBeDeleted();
        testUtil.addAgent();
    }

    @Test
    @Order(1)
    public void testAddDriver() throws MessagingException, ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahiru@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("location");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");

        DriverDetailDto driverDetailDto = new DriverDetailDto();
        driverDetailDto.setDOB("testDOB");
        driverDetailDto.setNIC("200220702973");
        driverDetailDto.setStatus("testStatus");
        driverDetailDto.setAddress("testAddress");

        UserDto result = driverService.addDriver(userDto, centerId);
        driverService.addDriverDetails(driverDetailDto, "lahiru@gmail.com", vehicleId);

        assertNotNull(result);

        System.out.println("[TEST] Adding Driver [PASSED]");
    }

    @Test
    @Order(2)
    public void testGetAllDrivers() throws ExpressDeliveryException {
        List<UserDto> results = adminService.getAllDrivers();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all Drivers [PASSED]");
    }

    @Test
    @Order(3)
    public void testGetALlServiceCenterDrivers() throws ExpressDeliveryException {
        ServiceCenterDto serviceCenterDto = new ServiceCenterDto();
        serviceCenterDto.setCentreId(centerId);
        List<UserDto> results = adminService.getServiceCenterDrivers(serviceCenterDto);

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all Drivers in service center [PASSED]");

    }

    @Test
    @Order(4)
    public void testGetAllAgents() throws ExpressDeliveryException {
        List<UserDto> results = adminService.getAllAgents();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all agents [PASSED]");
    }

    @Test
    @Order(5)
    public void testAddDriverWithSameEmail() throws MessagingException, ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahiru@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("location");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");

        DriverDetailDto driverDetailDto = new DriverDetailDto();
        driverDetailDto.setDOB("testDOB");
        driverDetailDto.setNIC("200220702973");
        driverDetailDto.setStatus("testStatus");
        driverDetailDto.setAddress("testAddress");

        boolean isTrue = false;

        try{
            driverService.addDriver(userDto, centerId);
            driverService.addDriverDetails(driverDetailDto, "lahiru@gmail.com", vehicleId);
        }catch (ExpressDeliveryException e){
            if (e.getMessage().equals("Email already in use")) isTrue = true;
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Attempt to add driver with existing email [PASSED]");
    }

    @Test
    @Order(6)
    public void testAddDriverWithSameNIC() throws MessagingException, ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahiru4@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("location");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");

        DriverDetailDto driverDetailDto = new DriverDetailDto();
        driverDetailDto.setDOB("testDOB");
        driverDetailDto.setNIC("200220702973");
        driverDetailDto.setStatus("testStatus");
        driverDetailDto.setAddress("testAddress");

        boolean isTrue = false;

        try{
            driverService.addDriver(userDto, centerId);
            driverService.addDriverDetails(driverDetailDto, "lahiru4@gmail.com", vehicleId);
        }catch (ExpressDeliveryException e){
            if (e.getMessage().equals("NIC already exists")) isTrue = true;
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Attempt to add driver with existing email [PASSED]");
    }

}
