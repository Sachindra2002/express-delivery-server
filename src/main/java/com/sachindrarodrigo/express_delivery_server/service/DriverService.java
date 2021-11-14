package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DriverDetailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Driver;
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
    private final ServiceCenterRepository serviceCenterRepository;

    public List<UserDto> getAllDrivers(){
        return userRepository.findByUserRoleEquals("driver").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    @Transactional
    public DriverDetail getDriverInfo(int driverId) throws ExpressDeliveryException {
        return driverDetailRepository.findById(driverId).orElseThrow(()-> new ExpressDeliveryException("Driver not found"));
    }

    @Transactional
    public UserDto addDriver(UserDto dto, DriverDetailDto driverDetailDto, String serviceCenter) throws ExpressDeliveryException {

        Optional<User> existing = userRepository.findById(dto.getEmail());

        if(existing.isPresent()){
            throw new ExpressDeliveryException("Email already in use");
        }

        User user = map(dto, serviceCenter);
        userRepository.save(user);

        return dto;
    }

    @Transactional
    public DriverDetailDto addDriverDetails(DriverDetailDto driverDetailDto, String email){
        DriverDetail driver = mapDriverDetail(driverDetailDto, email);
        driverDetailRepository.save(driver);

        return driverDetailDto;

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
}
