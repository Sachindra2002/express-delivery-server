package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.*;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AgentService {

    private final UserRepository userRepository;
    private final MailRepository mailRepository;
    private final MailTrackingRepository mailTrackingRepository;
    private final ServiceCenterRepository serviceCenterRepository;
    private final DriverDetailRepository driverDetailRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllAgents(){
        return userRepository.findByUserRoleEquals("agent").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    public void addAgent(UserDto userDto, String serviceCenter) throws ExpressDeliveryException {
        Optional<User> existing = userRepository.findById(userDto.getEmail());

        if(existing.isPresent()){
            throw new ExpressDeliveryException("Email already in use");
        }

        User user = map(userDto, serviceCenter);
        userRepository.save(user);
    }

    private User map(UserDto userDto, String serviceCenter){

        ServiceCentre serviceCentre = serviceCenterRepository.findByCentreEquals(serviceCenter);

        return User.builder().firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .location(userDto.getLocation())
                .userRole("agent")
                .password(passwordEncoder.encode(userDto.getEmail()))
                .serviceCentre(serviceCentre)
                .build();
    }

    //Method to map data transfer object to domain class
    private UserDto mapUsers(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLocation(), user.getPhoneNumber(), user.getUserRole(), user.getServiceCentre(), user.getDriverDetail());
    }

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));
        return _user.getFirstName();
    }

    @Transactional
    public List<MailDto> getAllNewShipmentsAdmin() throws ExpressDeliveryException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(_user.getServiceCentre().getCentreId());

        return mailRepository.findByServiceCentreAndStatusEquals(serviceCentre, "Processing").stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<MailDto> getAllNewAcceptedShipmentsAdmin() throws ExpressDeliveryException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(_user.getServiceCentre().getCentreId());

        return mailRepository.findByServiceCentreAndStatusEquals(serviceCentre, "Accepted").stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<DriverDetailDto> getAllAvailableDrivers() throws ExpressDeliveryException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(_user.getServiceCentre().getCentreId());

        return driverDetailRepository.findByStatusAndUserServiceCentre("Available", serviceCentre).stream().map(this::mapDriverDto).collect(Collectors.toList());

    }

    public void acceptParcel(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        mail.setStatus("Accepted");
        mailTracking.setStatus2("Your order has been successfully verified");
        mailTracking.setStatus2Date(Date.from(Instant.now()));
        mailTrackingRepository.save(mailTracking);
        mailRepository.save(mail);
    }

    public void rejectParcel(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        mail.setStatus("Rejected");
        mailTracking.setStatus2("Your order has been rejected");
        mailTracking.setStatus2Date(Date.from(Instant.now()));
        mailTrackingRepository.save(mailTracking);
        mailRepository.save(mail);
    }

    public void assignDriver(int mailId, int driverId, String date) throws ExpressDeliveryException, ParseException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        DriverDetail driverDetail = driverDetailRepository.findById(driverId).orElseThrow(()-> new ExpressDeliveryException("Driver Not found"));
        mail.setDriverDetail(driverDetail);
        mail.setStatus("Assigned");
        mail.setDropOffDate(LocalDate.parse(date));
        mailTracking.setStatus3("Driver assigned to pick up package");
        mailTracking.setStatus3Date(Date.from(Instant.now()));
        mailRepository.save(mail);
        mailTrackingRepository.save(mailTracking);
    }

    //Method to map data transfer object to domain class
    private MailDto mapDto(Mail mail) {
        return new MailDto(mail.getMailId(), mail.getPickupAddress(), mail.getReceiverAddress(), mail.getReceiverFirstName(), mail.getReceiverLastName(),mail.getReceiverPhoneNumber(), mail.getReceiverEmail(), mail.getReceiverCity(), mail.getParcelType(), mail.getWeight(),
                mail.getPieces(), mail.getPaymentMethod(), mail.getDate(), mail.getTime(), mail.getTotalCost(), mail.getStatus(), mail.getDescription(), mail.getUser(), mail.getMailTracking(), mail.getServiceCentre(),mail.getDropOffDate(),mail.getCreatedAt());
    }

    private DriverDetailDto mapDriverDto(DriverDetail driverDetail){
        return new DriverDetailDto(driverDetail.getDriverId(), driverDetail.getStatus(), driverDetail.getUser(), driverDetail.getVehicle(), driverDetail.getNIC(), driverDetail.getDOB(),
                driverDetail.getAddress());
    }
}