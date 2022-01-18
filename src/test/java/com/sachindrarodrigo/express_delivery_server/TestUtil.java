package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.dto.*;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.security.WithCustomUser;
import com.sachindrarodrigo.express_delivery_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
@AllArgsConstructor
public class TestUtil {

    @Autowired
    private final VehicleService vehicleService;

    @Autowired
    private final MailService mailService;

    @Autowired
    private final RegistrationService registrationService;

    @Autowired
    private final AgentService agentService;

    @Autowired
    private final ServiceCenterService serviceCenterService;

    @Autowired
    private final DisputeService disputeService;

    @Autowired
    private final DriverService driverService;


    public String registerUser() throws ExpressDeliveryException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahiru@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("Negombo");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");

        registrationService.registerUser(userDto);

        return "lahiru@gmail.com";
    }

    public void addAgent() throws ExpressDeliveryException {
        ServiceCentre serviceCenterDto = new ServiceCentre();
        serviceCenterDto.setCentreId(1);

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

    public int addDriver(int centerId) throws ExpressDeliveryException, MessagingException {
        UserDto userDto = new UserDto();
        userDto.setEmail("lahirudriver@gmail.com");
        userDto.setPhoneNumber("0715560355");
        userDto.setLocation("location");
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPassword("password");

        DriverDetailDto driverDetailDto = new DriverDetailDto();
        driverDetailDto.setDOB("testDOB");
        driverDetailDto.setNIC("200220702973");
        driverDetailDto.setStatus("Available");
        driverDetailDto.setAddress("testAddress");

        UserDto result = driverService.addDriver(userDto, centerId);
        DriverDetailDto driverDetailDto1 = driverService.addDriverDetails(driverDetailDto, "lahirudriver@gmail.com", createVehicleToBeDeleted());

        return driverDetailDto1.getDriverId();
    }

    public void assignDriver() {

    }

    public int createServiceCenter() throws ExpressDeliveryException {
        ServiceCenterDto serviceCenterDto = new ServiceCenterDto();
        serviceCenterDto.setCenter("TestCenter2");
        serviceCenterDto.setAddress("TestAddress");
        serviceCenterDto.setCity("Negombo");

        ServiceCenterDto result = serviceCenterService.addServiceCenter(serviceCenterDto);
        System.out.println("center" + result.getCentreId());
        return result.getCentreId();
    }

    @WithCustomUser(username = "lahiruagent@gmail.com")
    public void createShipment() throws ExpressDeliveryException {
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

        mailService.sendMail(mailDto);
    }

    public int createVehicleToBeDeleted() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleType("Lorry");
        vehicleDto.setVehicleNumber("JB 1224");
        vehicleDto.setStatus("available");

        VehicleDto result = vehicleService.addVehicle(vehicleDto);
        System.out.println("vehicle" + result.getVehicleId());
        return result.getVehicleId();
    }

    public int createDispute(int mailId) throws ExpressDeliveryException {
        DisputeDto disputeDto = new DisputeDto();
        disputeDto.setDisputeType("other");
        disputeDto.setDescription("description");

        DisputeDto result = disputeService.openDispute(disputeDto, mailId);

        return result.getDisputeId();
    }

}
