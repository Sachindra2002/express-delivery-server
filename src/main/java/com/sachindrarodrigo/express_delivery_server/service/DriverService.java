package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.*;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final VehicleRepository vehicleRepository;
    private final EmailService emailService;

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        return _user.getFirstName();
    }

    public List<UserDto> getAllDrivers() {
        return userRepository.findByUserRoleEquals("driver").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    public List<UserDto> getAvailableDrivers() {
        return userRepository.findAllByDriverDetail_Status("Available").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    public void updateServiceCenter(UserDto userDto) throws ExpressDeliveryException {
        User user = userRepository.findById(userDto.getEmail()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        ServiceCentre serviceCentre = serviceCenterRepository.findById(userDto.getServiceCentre().getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));

        user.setServiceCentre(serviceCentre);
        userRepository.save(user);
    }

    public void updateDriverVehicle(UserDto userDto) throws ExpressDeliveryException {
        DriverDetail driverDetail = driverDetailRepository.findById(userDto.getDriverDetail().getDriverId()).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        Vehicle vehicle1 = vehicleRepository.findById(userDto.getDriverDetail().getVehicle().getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle not found"));
        vehicle1.setStatus("taken");
        driverDetail.setVehicle(vehicle1);
        driverDetailRepository.save(driverDetail);
        vehicleRepository.save(vehicle1);
    }

    @Transactional
    public DriverDetail getDriverInfo(int driverId) throws ExpressDeliveryException {
        return driverDetailRepository.findById(driverId).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
    }

    @Transactional
    public void updateDriverPhoneNumber(DriverDetailDto driverDetailDto) {
        User user = userRepository.findByDriverDetail_DriverId(driverDetailDto.getDriverId());

        user.setPhoneNumber(driverDetailDto.getUser().getPhoneNumber());
        userRepository.save(user);
    }

    @Transactional
    public void updateCityAndAddress(DriverDetailDto driverDetailDto) throws ExpressDeliveryException {
        User user = userRepository.findByDriverDetail_DriverId(driverDetailDto.getDriverId());

        user.setLocation(driverDetailDto.getUser().getLocation());
        userRepository.save(user);

        DriverDetail driverDetail = driverDetailRepository.findById(driverDetailDto.getDriverId()).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        driverDetail.setAddress(driverDetailDto.getAddress());
        driverDetailRepository.save(driverDetail);
    }

    @Transactional
    public void updateStatus(DriverDetailDto driverDetailDto) throws ExpressDeliveryException {
        DriverDetail driverDetail = driverDetailRepository.findById(driverDetailDto.getDriverId()).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        driverDetail.setStatus(driverDetailDto.getStatus());
        driverDetailRepository.save(driverDetail);
    }

    @Transactional
    public void addDriver(UserDto dto, int centerId) throws ExpressDeliveryException, MessagingException {

        Optional<User> existing = userRepository.findById(dto.getEmail());

        if (existing.isPresent()) {
            throw new ExpressDeliveryException("Email already in use");
        }

        User user = map(dto, centerId);
        emailService.sendSimpleMessage(dto.getEmail(), "Driver account registered, password is the email");
        userRepository.save(user);

    }

    @Transactional
    public void addDriverDetails(DriverDetailDto driverDetailDto, String email, int vehicleId) throws ExpressDeliveryException {
        DriverDetail driver = mapDriverDetail(driverDetailDto, email, vehicleId);
        driverDetailRepository.save(driver);

    }

    @Transactional
    public void addDriverDetail(DriverDetail driverDetailDto, String email, int vehicleId) throws ExpressDeliveryException {
        DriverDetail driver = mapDriverDetails(driverDetailDto, email, vehicleId);
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

    private DriverDetail mapDriverDetail(DriverDetailDto driverDetailDto, String email, int vehicleId) throws ExpressDeliveryException {

        User user = userRepository.findById(email).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ExpressDeliveryException("Vehicle not found"));

        vehicle.setStatus("taken");
        vehicleRepository.save(vehicle);

        return DriverDetail.builder().address(driverDetailDto.getAddress())
                .DOB(driverDetailDto.getDOB())
                .NIC(driverDetailDto.getNIC())
                .status("Unavailable")
                .vehicle(vehicle)
                .user(user).build();
    }

    private DriverDetail mapDriverDetails(DriverDetail driverDetailDto, String email, int vehicleId) throws ExpressDeliveryException {

        User user = userRepository.findById(email).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ExpressDeliveryException("Vehicle not found"));

        vehicle.setStatus("taken");
        vehicleRepository.save(vehicle);

        return DriverDetail.builder().address(driverDetailDto.getAddress())
                .DOB(driverDetailDto.getDOB())
                .NIC(driverDetailDto.getNIC())
                .status("Unavailable")
                .vehicle(vehicle)
                .user(user).build();
    }

    private User map(UserDto userDto, int centerId) throws ExpressDeliveryException {

        ServiceCentre serviceCentre = serviceCenterRepository.findById(centerId).orElseThrow(() -> new ExpressDeliveryException("No center found"));

        return User.builder().firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .location(userDto.getLocation())
                .userRole("driver")
                .isBanned(false)
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
                mail.getUser(), mail.getMailTracking(), mail.getDriverDetail(), mail.getTransportationStatus(), mail.getServiceCentre(), mail.getDropOffDate(), mail.getCreatedAt());
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
