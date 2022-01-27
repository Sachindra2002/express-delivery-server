package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.VehicleDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.security.WithCustomUser;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
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
public class MailServiceTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private TestUtil testUtil;

    private int mailId;

    @BeforeAll
    public void init() throws ExpressDeliveryException {
        testUtil.registerUser();
    }

    @Test
    @Order(1)
    @WithCustomUser()
    public void testSendMail() throws ExpressDeliveryException, MessagingException {
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
    @Order(2)
    public void testGetAllShipments() {
        List<MailDto> results = mailService.getAllShipments();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all Shipments [PASSED]");
    }

    @Test
    @Order(3)
    @WithCustomUser
    public void testAllOutgoingShipments() throws ExpressDeliveryException {
        List<MailDto> results = mailService.getAllRecentOutgoingPackages();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all outgoing Shipments [PASSED]");

    }

    @Test
    @Order(4)
    @WithCustomUser
    public void testAllIncomingShipments() throws ExpressDeliveryException {
        List<MailDto> results = mailService.getAllRecentUpcomingPackages();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all outgoing Shipments [PASSED]");
    }

    @Test
    @Order(5)
    public void testCancelShipment() throws ExpressDeliveryException {
        MailDto result = mailService.cancelParcel(mailId);

        assertNotNull(result);
        System.out.println("[TEST] Cancel Shipments [PASSED]");
    }
}
