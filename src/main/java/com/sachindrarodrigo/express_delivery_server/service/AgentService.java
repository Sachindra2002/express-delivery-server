package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.*;
import com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
    private final DocumentsRepository documentsRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllAgents() {
        return userRepository.findByUserRoleEquals("agent").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    public void addAgent(UserDto userDto) throws ExpressDeliveryException {
        Optional<User> existing = userRepository.findById(userDto.getEmail());

        if (existing.isPresent()) {
            throw new ExpressDeliveryException("Email already in use");
        }
        if (isPhoneExist(userDto)) {
            throw new ExpressDeliveryException("Phone number already exists");
        }

        User user = map(userDto);
        userRepository.save(user);
    }

    private boolean isPhoneExist(UserDto dto) {
        Optional<User> existing = userRepository.findByPhoneNumberEquals(dto.getPhoneNumber());

        return existing.isPresent();
    }

    public void deleteAgent(UserDto userDto) throws ExpressDeliveryException {
        User user = userRepository.findById(userDto.getEmail()).orElseThrow(() -> new ExpressDeliveryException("Agent not found"));
        userRepository.delete(user);
    }

    @Transactional
    public UserDto updateCenterAgent(UserDto userDto) throws ExpressDeliveryException {
        User user = userRepository.findById(userDto.getEmail()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        ServiceCentre serviceCentre = serviceCenterRepository.findById(userDto.getServiceCentre().getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));
        user.setServiceCentre(serviceCentre);
        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    private User map(UserDto userDto) throws ExpressDeliveryException {

        ServiceCentre serviceCentre = serviceCenterRepository.findById(userDto.getServiceCentre().getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));

        return User.builder().firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .location(userDto.getLocation())
                .userRole("agent")
                .isBanned(false)
                .password(passwordEncoder.encode(userDto.getEmail()))
                .serviceCentre(serviceCentre)
                .build();
    }

    //Method to map data transfer object to domain class
    private UserDto mapUsers(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLocation(), user.getPhoneNumber(), user.getUserRole(), user.getServiceCentre(), user.getDriverDetail());
    }

    private DocumentsDto mapDocuments(Documents documents) {
        return new DocumentsDto(documents.getDocumentId(), documents.getDescription(), documents.getFileName(), documents.getFileSize(), documents.getUser());
    }

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        return _user.getFirstName();
    }

    @Transactional
    public List<MailDto> getAllNewShipmentsAdmin() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(user.getServiceCentre().getCentreId());

        return mailRepository.findByServiceCentreAndStatusEquals(serviceCentre, "Processing").stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<MailDto> getAllNewAcceptedShipmentsAdmin() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(user.getServiceCentre().getCentreId());

        return mailRepository.findByServiceCentreAndStatusEquals(serviceCentre, "Accepted").stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<MailDto> getTransitPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(user.getServiceCentre().getCentreId());

        return mailRepository.findByServiceCentreAndStatusEquals(serviceCentre, "In Transit").stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<DriverDetailDto> getAllAvailableDrivers() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(user.getServiceCentre().getCentreId());

        return driverDetailRepository.findByStatusAndUserServiceCentre("Available", serviceCentre).stream().map(this::mapDriverDto).collect(Collectors.toList());

    }

    @Transactional
    public List<UserDto> getAllDrivers() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));

        ServiceCentre serviceCentre = serviceCenterRepository.getById(user.getServiceCentre().getCentreId());

        return userRepository.findByUserRoleEqualsAndDriverDetail_StatusAndServiceCentre("driver", "Available", serviceCentre).stream().map(this::mapUsers).collect(Collectors.toList());

    }

    public List<DocumentsDto> getDriverDocuments(String email) throws ExpressDeliveryException {
        User user = userRepository.findById(email).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        return documentsRepository.findByUserEquals(user).stream().map(this::mapDocuments).collect(Collectors.toList());
    }

    public MailDto acceptParcel(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        mail.setStatus("Accepted");
        mailTracking.setStatus2("Your order has been successfully verified");
        mailTracking.setStatus2Date(Date.from(Instant.now()));
        mailTrackingRepository.save(mailTracking);
        mailRepository.save(mail);

        MailDto mailDto = new MailDto();
        mailDto.setMailId(mail.getMailId());
        return mailDto;
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

    public MailDto assignDriver(int mailId, int driverId, String date) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        DriverDetail driverDetail = driverDetailRepository.findById(driverId).orElseThrow(() -> new ExpressDeliveryException("Driver Not found"));
        mail.setDriverDetail(driverDetail);
        mail.setStatus("Assigned");
        mail.setTransportationStatus("Pick Up");
        mail.setDropOffDate(date);
        mailTracking.setStatus3("Driver assigned to pick up package");
        mailTracking.setStatus3Date(Date.from(Instant.now()));
        mailRepository.save(mail);
        mailTrackingRepository.save(mailTracking);

        MailDto mailDto = new MailDto();
        mailDto.setMailId(mail.getMailId());
        return mailDto;
    }

    public MailDto changeDriver(MailDto mailDto) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailDto.getMailId()).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        DriverDetail driverDetail = driverDetailRepository.findById(mailDto.getDriverDetail().getDriverId()).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        mail.setDriverDetail(driverDetail);
        mailRepository.save(mail);

        MailDto dto = new MailDto();
        dto.setMailId(mail.getMailId());
        return dto;
    }

    //Method to map data transfer object to domain class
    private MailDto mapDto(Mail mail) {
        return new MailDto(mail.getMailId(), mail.getPickupAddress(), mail.getReceiverAddress(), mail.getReceiverFirstName(), mail.getReceiverLastName(),
                mail.getReceiverPhoneNumber(), mail.getReceiverEmail(), mail.getReceiverCity(), mail.getParcelType(), mail.getWeight(),
                mail.getPieces(), mail.getPaymentMethod(), mail.getDate(), mail.getTime(), mail.getTotalCost(), mail.getStatus(), mail.getDescription(),
                mail.getUser(), mail.getMailTracking(), mail.getDriverDetail(), mail.getTransportationStatus(), mail.getServiceCentre(), mail.getDropOffDate(), mail.getCreatedAt());
    }

    private DriverDetailDto mapDriverDto(DriverDetail driverDetail) {
        return new DriverDetailDto(driverDetail.getDriverId(), driverDetail.getStatus(), driverDetail.getUser(), driverDetail.getVehicle(), driverDetail.getNIC(), driverDetail.getDOB(),
                driverDetail.getAddress());
    }
}
