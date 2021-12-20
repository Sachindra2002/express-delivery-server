package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailTrackingRepository;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final UserRepository userRepository;
    private final MailTrackingRepository mailTrackingRepository;
    private final ServiceCenterRepository serviceCenterRepository;

    @Transactional
    public void sendMail(MailDto dto) throws ExpressDeliveryException {
        Mail mail = map(dto);
        mailRepository.save(mail);
        createTracking(mail.getMailId());
    }

    public void createTracking(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        mailTrackingRepository.save(MailTracking.builder().mail(mail)
                .deliveryPartner("LK-EXPRESS-DELIVERY")
                .status1("Processing Started - Thank you for using Express Delivery")
                .build());
    }

    public void cancelParcel(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));
        MailTracking mailTracking = mailTrackingRepository.findByMail(mail);
        mailTracking.setStatus2("Order cancelled successfully");
        mailTracking.setStatus2Date(Date.from(Instant.now()));
        mail.setStatus("Cancelled");
        mailTrackingRepository.save(mailTracking);
        mailRepository.save(mail);
    }

    public List<MailDto> getAllRecentUpcomingPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByReceiverEmailEquals(user.getEmail()).stream().map(this::mapDto).collect(Collectors.toList());
        List<MailDto> recentUpcoming = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i == 10) break;
            recentUpcoming.add(list.get(i));
        }

        Collections.reverse(recentUpcoming);

        return recentUpcoming;
    }

    public List<MailDto> getAllRecentOutgoingPackages() throws ExpressDeliveryException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new ExpressDeliveryException("User not found"));
        List<MailDto> list = mailRepository.findByUserEquals(user).stream().map(this::mapDto).collect(Collectors.toList());
        List<MailDto> recentOutgoing = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i == 10) break;
            recentOutgoing.add(list.get(i));
        }
        Collections.reverse(recentOutgoing);

        return recentOutgoing;
    }

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));

        return _user.getEmail();
    }

    private Mail map(MailDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getName());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        ServiceCentre serviceCentre = serviceCenterRepository.findByCityEquals(user.getLocation());

        return Mail.builder().user(user)
                .pickupAddress(dto.getPickupAddress())
                .receiverAddress(dto.getReceiverAddress())
                .receiverFirstName(dto.getReceiverFirstName())
                .receiverLastName(dto.getReceiverLastName())
                .receiverPhoneNumber(dto.getReceiverPhoneNumber())
                .receiverEmail(dto.getReceiverEmail())
                .receiverCity(dto.getReceiverCity())
                .parcelType(dto.getParcelType())
                .weight(dto.getWeight())
                .pieces(dto.getPieces())
                .paymentMethod(dto.getPaymentMethod())
                .date(dto.getDate())
                .time(dto.getTime())
                .totalCost(dto.getTotalCost())
                .status("Processing")
                .serviceCentre(serviceCentre)
                .transportationStatus("Pick Up")
                .description(dto.getDescription()).build();
    }

    //Method to map data transfer object to domain class
    private MailDto mapDto(Mail mail) {


        return new MailDto(mail.getMailId(), mail.getPickupAddress(), mail.getReceiverAddress(), mail.getReceiverFirstName(), mail.getReceiverLastName(),
                mail.getReceiverPhoneNumber(), mail.getReceiverEmail(), mail.getReceiverCity(), mail.getParcelType(), mail.getWeight(),
                mail.getPieces(), mail.getPaymentMethod(), mail.getDate(), mail.getTime(), mail.getTotalCost(), mail.getStatus(), mail.getDescription(),
                mail.getUser(), mail.getMailTracking(), mail.getDriverDetail(), mail.getTransportationStatus(),mail.getServiceCentre(),mail.getDropOffDate(),mail.getCreatedAt());
    }
}
