package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Documents;
import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DocumentsRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final MailRepository mailRepository;
    private final DocumentsRepository documentsRepository;
    private final ServiceCenterRepository serviceCenterRepository;

    public List<DocumentsDto> getDriverDocuments(String email) throws ExpressDeliveryException {
        User user = userRepository.findById(email).orElseThrow(() -> new ExpressDeliveryException("Driver not found"));
        return documentsRepository.findByUserEquals(user).stream().map(this::mapDocuments).collect(Collectors.toList());
    }

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));

        return _user.getFirstName() + " " + _user.getLastName();
    }

    @Transactional
    public List<MailDto> getAllNewShipmentsAdmin() {

        List<MailDto> list = mailRepository.findAll().stream().map(this::mapDto).collect(Collectors.toList());
        List<MailDto> newShipments = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i == 10) break;
            newShipments.add(list.get(i));
        }
//        Collections.reverse(recentUpcoming);
        return newShipments;
    }

    public List<MailDto> getServiceCenterPackages(ServiceCenterDto serviceCenterDto) throws ExpressDeliveryException {
        ServiceCentre serviceCentre = serviceCenterRepository.findById(serviceCenterDto.getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));

        return mailRepository.findAllByServiceCentre(serviceCentre).stream().map(this::mapDto).collect(Collectors.toList());
    }

    @Transactional
    public List<UserDto> getAllDrivers() throws ExpressDeliveryException {

        return userRepository.findByUserRoleEquals("driver").stream().map(this::mapUsers).collect(Collectors.toList());

    }

    public List<UserDto> getServiceCenterDrivers(ServiceCenterDto serviceCenterDto) throws ExpressDeliveryException {
        ServiceCentre serviceCentre = serviceCenterRepository.findById(serviceCenterDto.getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));

        return userRepository.findAllByServiceCentre(serviceCentre).stream().map(this::mapUsers).collect(Collectors.toList());
    }

    @Transactional
    public List<UserDto> getAllAgents() throws ExpressDeliveryException {

        return userRepository.findByUserRoleEquals("agent").stream().map(this::mapUsers).collect(Collectors.toList());

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

    private DocumentsDto mapDocuments(Documents documents){
        return new DocumentsDto(documents.getDocumentId(), documents.getDescription(), documents.getFileName(), documents.getFileSize(), documents.getUser());
    }

}
