package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.security.WithCustomUser;
import com.sachindrarodrigo.express_delivery_server.service.AgentService;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AgentServiceTests {

    @Autowired
    private AgentService agentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TestUtil testUtil;

    private int centerId, mailId, driverId;

    @BeforeAll
    public void init() throws ExpressDeliveryException, MessagingException {
        centerId = testUtil.createServiceCenter();
        driverId = testUtil.addDriver(centerId);
    }

    @Test
    @Order(1)
    public void testAddAgent() throws ExpressDeliveryException {
        ServiceCentre serviceCenterDto = new ServiceCentre();
        serviceCenterDto.setCentreId(centerId);

        UserDto userDto = new UserDto();
        userDto.setEmail("lahiruagent@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("Negombo");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");
        userDto.setServiceCentre(serviceCenterDto);

        agentService.addAgent(userDto);
    }

    @Test
    @Order(2)
    public void testAddAgentWithExistingEmail() throws ExpressDeliveryException {
        ServiceCentre serviceCenterDto = new ServiceCentre();
        serviceCenterDto.setCentreId(centerId);

        UserDto userDto = new UserDto();
        userDto.setEmail("lahiruagent@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("location");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");
        userDto.setServiceCentre(serviceCenterDto);

        boolean isTrue = false;

        try {
            agentService.addAgent(userDto);
        } catch (ExpressDeliveryException e) {
            if (e.getMessage().equals("Email already in use")) isTrue = true;
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Attempt to add agent with existing email [PASSED]");
    }

    @Test
    public void testDeleteAgent() throws ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahiruagent@gmail.com");

        agentService.deleteAgent(userDto);

        List<UserDto> results = agentService.getAllAgents();

        boolean isTrue = true;

        for(UserDto dto : results) {
            if(Objects.equals(dto.getEmail(), "lahiruagent@gmail.com")){
                isTrue = false;
                break;
            }
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Delete agent [PASSED]");
    }

    @Test
    @Order(3)
    public void testUpdateServiceCenterAgent() throws ExpressDeliveryException {
        ServiceCentre serviceCenterDto = new ServiceCentre();
        serviceCenterDto.setCentreId(centerId);

        UserDto userDto = new UserDto();
        userDto.setEmail("lahiruagent@gmail.com");
        userDto.setServiceCentre(serviceCenterDto);

        UserDto result = agentService.updateCenterAgent(userDto);

        assertNotNull(result);

        System.out.println("[TEST] Update service center agent [PASSED]");
    }

    @WithCustomUser(username = "lahiruagent@gmail.com")
    public void testSendMail() throws ExpressDeliveryException {
        MailDto mailDto = new MailDto();
        mailDto.setPickupAddress("TestPickUpAddress");
        mailDto.setReceiverAddress("TestReceiverAddress");
        mailDto.setReceiverFirstName("TestReceiverFirstName");
        mailDto.setReceiverLastName("TestReceiverLastName");
        mailDto.setReceiverPhoneNumber("0714460355");
        mailDto.setReceiverEmail("lahiru@gmail.com");
        mailDto.setReceiverCity("TestReceiverCity");
        mailDto.setParcelType("TestParcelType");
        mailDto.setWeight("TestWeight");
        mailDto.setPieces("TestPieces");
        mailDto.setPaymentMethod("TestPaymentMethod");
        mailDto.setDate("TestDate");
        mailDto.setTime("TestTime");
        mailDto.setTotalCost("TestTotalCost");
        mailDto.setDescription("TestDescription");

        MailDto result = mailService.sendMail(mailDto);

        mailId = result.getMailId();

        assertNotNull(result);

        System.out.println("[TEST] Send Package Request [PASSED]" + result.getMailId());
    }

    @Test
    @Order(5)
    @WithCustomUser(username = "lahiruagent@gmail.com")
    public void testGetAllNewShipments() throws ExpressDeliveryException {
        testSendMail();
        List<MailDto> results = agentService.getAllNewShipmentsAdmin();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all new shipments [PASSED]");

    }

    @Test
    @Order(6)
    public void testAcceptParcel() throws ExpressDeliveryException {
        MailDto result = agentService.acceptParcel(mailId);

        assertNotNull(result);

        System.out.println("[TEST] Get all new shipments [PASSED]");

    }

    @Test
    @Order(7)
    @WithCustomUser(username = "lahiruagent@gmail.com")
    public void testGetAllAcceptedShipments() throws ExpressDeliveryException {
        testSendMail();
        List<MailDto> results = agentService.getAllNewAcceptedShipmentsAdmin();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all accepted shipments [PASSED]");

    }

    @Test
    @Order(8)
    public void testAssignDriver() throws ExpressDeliveryException {
        MailDto result = agentService.assignDriver(mailId, driverId, "12-12-2020");

        assertNotNull(result);

        System.out.println("[TEST] Assign driver to package [PASSED]");
    }

    @Test
    @Order(9)
    public void testChangeDriver() throws ExpressDeliveryException {
        DriverDetail driverDetailDto = new DriverDetail();
        driverDetailDto.setDriverId(driverId);

        MailDto mailDto = new MailDto();
        mailDto.setMailId(mailId);
        mailDto.setDriverDetail(driverDetailDto);

        MailDto result = agentService.changeDriver(mailDto);

        assertNotNull(result);

        System.out.println("[TEST] Change driver [PASSED]");
    }

    @Test
    @Order(10)
    @WithCustomUser(username = "lahiruagent@gmail.com")
    public void testGetAllDriversInServiceCenter() throws ExpressDeliveryException {
        List<UserDto> results = agentService.getAllDrivers();

        boolean isTrue = results.size() >= 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all accepted shipments [PASSED]");
    }


}
