package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.security.WithCustomUser;
import com.sachindrarodrigo.express_delivery_server.service.DisputeService;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class DisputeServiceTests {

    @Autowired
    private DisputeService disputeService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TestUtil testUtil;

    private int mailId, disputeId;

    @BeforeAll
    public void init() throws ExpressDeliveryException {
        testUtil.registerUser();
    }

    @WithCustomUser
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
    }

    @Test
    @Order(1)
    public void testAddDispute() throws ExpressDeliveryException, MessagingException {
        testSendMail();

        DisputeDto disputeDto = new DisputeDto();
        disputeDto.setDisputeType("other");
        disputeDto.setDescription("description");
        DisputeDto result = disputeService.openDispute(disputeDto, mailId);

        assertNotNull(result);

        System.out.println("[TEST] Open Dispute [PASSED]");
    }
}
