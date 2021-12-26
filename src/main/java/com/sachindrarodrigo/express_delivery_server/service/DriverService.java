package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DriverDetailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DriverService {

    private final UserRepository userRepository;
    private final DriverDetailRepository driverDetailRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailRepository mailRepository;
    private final ServiceCenterRepository serviceCenterRepository;
    private final EmailService emailService;

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));
        return _user.getFirstName();
    }

    public List<UserDto> getAllDrivers(){
        return userRepository.findByUserRoleEquals("driver").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    @Transactional
    public DriverDetail getDriverInfo(int driverId) throws ExpressDeliveryException {
        return driverDetailRepository.findById(driverId).orElseThrow(()-> new ExpressDeliveryException("Driver not found"));
    }

    @Transactional
    public void addDriver(UserDto dto, DriverDetailDto driverDetailDto, String serviceCenter) throws ExpressDeliveryException, MessagingException {

        Optional<User> existing = userRepository.findById(dto.getEmail());

        if(existing.isPresent()){
            throw new ExpressDeliveryException("Email already in use");
        }

        User user = map(dto, serviceCenter);
        emailService.sendSimpleMessage(dto.getEmail(), "Driver account registered, password is the email");
        userRepository.save(user);

    }

    @Transactional
    public void addDriverDetails(DriverDetailDto driverDetailDto, String email){
        DriverDetail driver = mapDriverDetail(driverDetailDto, email);
        driverDetailRepository.save(driver);

    }

    public List<MailDto> getAllAssignedPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByDriverDetailAndStatusEquals(user.getDriverDetail(), "Assigned").stream().map(this::mapDto).collect(Collectors.toList());
        List<MailDto> recentAssignedPackages = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i == 10) break;
            recentAssignedPackages.add(list.get(i));
        }

        Collections.reverse(recentAssignedPackages);

        return recentAssignedPackages;
    }

    public List<MailDto> getAllAcceptedPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByDriverDetailAndStatusEquals(user.getDriverDetail(), "Driver Accepted").stream().map(this::mapDto).collect(Collectors.toList());

        Collections.reverse(list);

        return list;
    }

    public List<MailDto> getAllStartedPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByDriverDetailAndStatusEquals(user.getDriverDetail(), "Delivery Started").stream().map(this::mapDto).collect(Collectors.toList());

        Collections.reverse(list);

        return list;
    }

    public List<MailDto> getPickedUpPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByDriverDetailAndStatusEquals(user.getDriverDetail(), "Package picked up").stream().map(this::mapDto).collect(Collectors.toList());

        Collections.reverse(list);

        return list;
    }

    public Optional<UserDto> getUserDetails() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        return userRepository.findById(auth.getName()).map(this::mapUsers);
    }

    private DriverDetail mapDriverDetail(DriverDetailDto driverDetailDto, String email){

        User user = userRepository.findById(email).orElseThrow(()-> new ExpressionException("User not found"));

        return DriverDetail.builder().address(driverDetailDto.getAddress())
                .DOB(driverDetailDto.getDOB())
                .NIC(driverDetailDto.getNIC())
                .status("Unavailable")
                .user(user).build();
    }

    private User map(UserDto userDto, String serviceCenter){

        ServiceCentre serviceCentre = serviceCenterRepository.findByCentreEquals(serviceCenter);

        return User.builder().firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .location(userDto.getLocation())
                .userRole("driver")
                .password(passwordEncoder.encode(userDto.getEmail()))
                .serviceCentre(serviceCentre)
                .build();
    }

    //Method to map data transfer object to domain class
    private UserDto mapUsers(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLocation(), user.getPhoneNumber(), user.getUserRole(), user.getServiceCentre(), user.getDriverDetail());
    }

    //Method to map data transfer object to domain class
    private MailDto mapDto(Mail mail) {


        return new MailDto(mail.getMailId(), mail.getPickupAddress(), mail.getReceiverAddress(), mail.getReceiverFirstName(), mail.getReceiverLastName(),
                mail.getReceiverPhoneNumber(), mail.getReceiverEmail(), mail.getReceiverCity(), mail.getParcelType(), mail.getWeight(),
                mail.getPieces(), mail.getPaymentMethod(), mail.getDate(), mail.getTime(), mail.getTotalCost(), mail.getStatus(), mail.getDescription(),
                mail.getUser(), mail.getMailTracking(), mail.getDriverDetail(), mail.getTransportationStatus(),mail.getServiceCentre(),mail.getDropOffDate(),mail.getCreatedAt());
    }

    public void acceptPackage(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        mail.setStatus("Driver Accepted");
        mailRepository.save(mail);
    }

    public void startPackage(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        mail.setStatus("Delivery Started");
        mailRepository.save(mail);
    }

    public void confirmPickupPackage(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        mail.setStatus("Package picked up");
        mailRepository.save(mail);
    }
}
