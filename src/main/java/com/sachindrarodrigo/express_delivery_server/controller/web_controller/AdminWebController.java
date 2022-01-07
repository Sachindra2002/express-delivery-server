package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.*;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DriverDetailRepository;
import com.sachindrarodrigo.express_delivery_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Objects;


@Controller
@AllArgsConstructor
public class AdminWebController {

    private final DriverService driverService;
    private final AgentService agentService;
    private final ServiceCenterService serviceCenterService;
    private final VehicleService vehicleService;
    private final DisputeService disputeService;
    private final InquiryService inquiryService;
    private final StorageService storageService;
    private final MailService mailService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private final DriverDetailRepository driverDetailRepository;

    @GetMapping("/drivers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewAllDrivers() {
        return getAllDrivers();
    }

    @GetMapping("/agents")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewAllAgents() {
        return getAllAgents();
    }

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewAllCustomers() {
        return getAllCustomers();
    }

    public ModelAndView getAllDrivers() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage-drivers.jsp");
        mv.addObject("role", "admin");
        mv.addObject("driver_list", driverService.getAllDrivers());

        return mv;
    }

    public ModelAndView getAllAgents() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage-agents.jsp");
        mv.addObject("agent_list", agentService.getAllAgents());
        //Get list of service centers for "Add Driver"
        mv.addObject("centers", serviceCenterService.getAllServiceCenters());
        return mv;
    }

    public ModelAndView getAllCustomers() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customers.jsp");
        mv.addObject("customers", customerService.getAllCustomers());
        return mv;
    }

    @GetMapping("/view-driver")
    @PreAuthorize("hasAnyRole('ADMIN , AGENT')")
    public ModelAndView viewDriver(@RequestParam int driverId) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view-driver.jsp");
        mv.addObject("driver", driverService.getDriverInfo(driverId));
        return mv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-driver")
    public ModelAndView addDriver() {
        ModelAndView mv = new ModelAndView();
        DriverDetail driverDetail = new DriverDetail();
        User user = new User();
        mv.setViewName("add-driver.jsp");
        mv.addObject("driverDetail", driverDetail);
        mv.addObject("user", user);
        //Get list of service centers for "Add Driver"
        mv.addObject("centers", serviceCenterService.getAllServiceCenters());
        mv.addObject("vehicles", vehicleService.getAvailableVehicles());
        return mv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-agent")
    public ModelAndView addAgent() {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        mv.setViewName("add-agent.jsp");
        mv.addObject("user", user);
        //Get list of service centers for "Add Driver"
        mv.addObject("centers", serviceCenterService.getAllServiceCenters());
        return mv;
    }


    @PostMapping("/add-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addDriver(@Valid @ModelAttribute("user") User user, BindingResult bindingUser,
                                  @Valid @ModelAttribute("driverDetail") DriverDetail driverDetail, BindingResult bindingDriver,
                                  @RequestParam int center,
                                  @RequestParam int vehicle,
                                  @RequestParam String DOB,
                                  @RequestParam String NIC, @RequestParam String address, @RequestParam(value = "nicImage") MultipartFile nicImage,
                                  @RequestParam(value = "licence") MultipartFile licence, @RequestParam(value = "insurance") MultipartFile insurance,
                                  RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (bindingUser.hasErrors()) {
            mv.setViewName("add-driver.jsp");
            System.out.println("errors" + bindingUser.hasErrors() + bindingUser.getAllErrors());
        } else if (bindingDriver.hasErrors()) {
            mv.setViewName("add-driver.jsp");
            System.out.println("errors" + bindingDriver.hasErrors() + bindingDriver.getAllErrors());
        } else {
            try {

                UserDto userDto = new UserDto();
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setEmail(user.getEmail());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setLocation(user.getLocation());

                DriverDetailDto driverDetailDto = new DriverDetailDto();
                driverDetailDto.setDOB(DOB);
                driverDetailDto.setNIC(NIC);
                driverDetailDto.setAddress(address);

                driverService.addDriver(userDto, center);
                driverService.addDriverDetails(driverDetailDto, user.getEmail(), vehicle);

                DocumentsDto dto = new DocumentsDto();

                storageService.uploadNicFile(nicImage, user.getEmail(), dto);
                storageService.uploadLicenceFile(licence, user.getEmail(), dto);
                storageService.uploadInsuaranceFile(insurance, user.getEmail(), dto);

                redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Driver added successfully"));
                mv.setViewName("redirect:/drivers");


            } catch (ExpressDeliveryException | MessagingException e) {
                mv.addObject("error", new APIException(e.getMessage()));
            }
        }
        return mv;
    }

    @PostMapping("/upload-documents")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView uploadDocuments(@RequestParam int driverId, @RequestParam(value = "nicImage") MultipartFile nicImage,
                                        @RequestParam(value = "licence") MultipartFile licence, @RequestParam(value = "insurance") MultipartFile insurance,
                                        RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {

            DocumentsDto dto = new DocumentsDto();

            DriverDetail driverDetail = driverDetailRepository.findById(driverId).orElseThrow(() -> new ExpressDeliveryException("No driver found"));

            storageService.uploadNicFile(nicImage, driverDetail.getUser().getEmail(), dto);
            storageService.uploadLicenceFile(licence, driverDetail.getUser().getEmail(), dto);
            storageService.uploadInsuaranceFile(insurance, driverDetail.getUser().getEmail(), dto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Documents uploaded successfully"));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        }
        return mv;

    }

    @PostMapping("/update-phone-number")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateDriverPhoneNumber(@RequestParam int driverId, @RequestParam String phoneNumber, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        try {
            User user = new User();
            DriverDetailDto driverDetailDto = new DriverDetailDto();

            user.setPhoneNumber(phoneNumber);

            driverDetailDto.setDriverId(driverId);
            driverDetailDto.setUser(user);

            driverService.updateDriverPhoneNumber(driverDetailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Phone number updated successfully"));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        } catch (Exception e) {
            mv = viewDriver(driverId);
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        }

        return mv;
    }

    @PostMapping("/update-city-address")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateCityAndAddress(@RequestParam int driverId, @RequestParam String address, @RequestParam String city, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            User user = new User();
            user.setLocation(city);

            DriverDetailDto driverDetailDto = new DriverDetailDto();
            driverDetailDto.setDriverId(driverId);
            driverDetailDto.setAddress(address);
            driverDetailDto.setUser(user);

            driverService.updateCityAndAddress(driverDetailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("City and address updated successfully"));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        }
        return mv;
    }

    @PostMapping("/update-driver-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateDriverStatus(@RequestParam int driverId, @RequestParam String status, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        try {
            DriverDetailDto driverDetailDto = new DriverDetailDto();
            driverDetailDto.setDriverId(driverId);
            driverDetailDto.setStatus(status);
            driverService.updateStatus(driverDetailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Status updated successfully"));
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addAttribute("driverId", driverId);
            mv.setViewName("redirect:/view-driver");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PostMapping("/add-agent")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addAgent(@Valid @ModelAttribute("user") User user, BindingResult bindingUser, @RequestParam int center, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/agents");
        if (bindingUser.hasErrors()) {
            mv.setViewName("add-agent.jsp");
        } else {
            try {
                ServiceCentre serviceCentre = new ServiceCentre();
                serviceCentre.setCentreId(center);

                UserDto userDto = new UserDto();
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setEmail(user.getEmail());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setLocation(user.getLocation());
                userDto.setServiceCentre(serviceCentre);

                agentService.addAgent(userDto);

                redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Agent added successfully"));
                mv.setViewName("redirect:/agents");
            } catch (ExpressDeliveryException e) {
                if (Objects.equals(e.getMessage(), "Email already in use")) {
                    mv.setViewName("add-agent.jsp");
                    mv.addObject("emailError", "Email already in use");
                } else {
                    redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
                }

            }
        }

        return mv;
    }

    @GetMapping("/view-disputes")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewDisputes() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("dispute_list", disputeService.getAllDisputes());
            mv.setViewName("view-disputes.jsp");
        } catch (Exception e) {
            mv.setViewName("view-disputes.jsp");
        }
        return mv;
    }

    @GetMapping("/view-inquiries")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewInquires() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("inquiry_list", inquiryService.getAllInquiries());
            mv.setViewName("view-inquiries.jsp");
        } catch (Exception e) {
            mv.setViewName("view-inquiries.jsp");
        }
        return mv;
    }

    @PostMapping("/send-response")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView sendResponse(@RequestParam int disputeId, @RequestParam String response, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            DisputeDto disputeDto = new DisputeDto();
            disputeDto.setResponse(response);
            disputeDto.setDisputeId(disputeId);
            disputeService.respondDispute(disputeDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Responded successfully"));
            mv.setViewName("redirect:/view-disputes");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Something went wrong!"));
            mv.setViewName("redirect:/view-disputes");
        }
        return mv;
    }

    @PostMapping("/add-vehicle")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addVehicle(@RequestParam String vehicleType, @RequestParam String vehicleNumber, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleType(vehicleType);
            vehicleDto.setVehicleNumber(vehicleNumber);
            vehicleService.addVehicle(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle added sucessfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException("Vehicle Number already exists"));
            mv.setViewName("redirect:/vehicles");
        }
        return mv;
    }

    @PostMapping("/send-response-inquiry")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView sendResponseInquiry(@RequestParam int inquiryId, @RequestParam String response, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            InquiryDto inquiryDto = new InquiryDto();
            inquiryDto.setInquiryId(inquiryId);
            inquiryDto.setResponse(response);
            inquiryService.respondInquiry(inquiryDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Responded successfully"));
            mv.setViewName("redirect:/view-inquiries");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new SimpleMessageDto("Something went wrong!"));
            mv.setViewName("redirect:/view-inquiries");
        }
        return mv;
    }

    @GetMapping("/service-centers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewServiceCenters() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("centers.jsp");
            mv.addObject("centers", serviceCenterService.getAllServiceCenters());
        } catch (Exception e) {
            mv.addObject("error", new SimpleMessageDto("Something went wrong!"));
            mv.setViewName("redirect:/home-admin");
        }
        return mv;
    }

    @GetMapping("/vehicles")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewVehicles() {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("vehicles.jsp");
            mv.addObject("vehicles", vehicleService.getAllVehicles());
        } catch (Exception e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/home-admin");
        }

        return mv;
    }

    @PostMapping("/delete-vehicle")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteVehicle(@RequestParam int vehicleId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleId(vehicleId);
            vehicleService.deleteVehicle(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle removed successfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (Exception e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/vehicles");
        }

        return mv;
    }

    @PostMapping("/blacklist-vehicle")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setBlacklist(@RequestParam int vehicleId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleId(vehicleId);
            vehicleService.setBlacklist(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle blacklisted successfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/vehicles");
        }

        return mv;
    }

    @PostMapping("/remove-vehicle-blacklist")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView removeVehicleBlacklist(@RequestParam int vehicleId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleId(vehicleId);
            vehicleService.removeBlacklist(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle blacklist removed successfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/vehicles");
        }

        return mv;
    }

    @PostMapping("/set-available-vehicle")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setVehicleAvailable(@RequestParam int vehicleId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleId(vehicleId);
            vehicleService.setVehicleAvailable(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle status updated successfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/vehicles");
        }

        return mv;
    }

    @PostMapping("/set-unavailable-vehicle")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setVehicleUnAvailable(@RequestParam int vehicleId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicleId(vehicleId);
            vehicleService.setVehicleUnAvailable(vehicleDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Vehicle status updated successfully"));
            mv.setViewName("redirect:/vehicles");
        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/vehicles");
        }

        return mv;
    }

    @GetMapping("/view-center")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewServiceCenter(@RequestParam int centerId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            ServiceCenterDto serviceCenterDto = new ServiceCenterDto();
            serviceCenterDto.setCentreId(centerId);
            mv.setViewName("view-service-center.jsp");
            mv.addObject("center", serviceCenterService.viewServiceCenter(serviceCenterDto));
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/service-centers");
        }

        return mv;

    }

    @GetMapping("/view-customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewCustomer(@RequestParam String email, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            UserDto userDto = new UserDto();
            userDto.setEmail(email);
            mv.setViewName("view-customer.jsp");
            mv.addObject("customer", customerService.getCustomer(userDto));
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/service-centers");
        }

        return mv;
    }

    @PostMapping("/delete-agent")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteAgent(@RequestParam String email, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            UserDto userDto = new UserDto();
            userDto.setEmail(email);
            agentService.deleteAgent(userDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Agent removed successfully"));
            mv.setViewName("redirect:/agents");
        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException("Something went wrong!"));
            mv.setViewName("redirect:/agents");
        }

        return mv;
    }

    @PostMapping("/toggle-blacklist")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView toggleBlacklist(@RequestParam String email, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            UserDto userDto = new UserDto();
            userDto.setEmail(email);
            customerService.toggleBlacklist(userDto);
            mv.setViewName("redirect:/view-customer");
            redirectAttributes.addAttribute("email", email);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Successfully Updated Status"));
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addAttribute("email", email);
            mv.setViewName("redirect:/view-customer");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PostMapping("/add-service-center")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addServiceCenter(@RequestParam String city, @RequestParam String center, @RequestParam String address, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            ServiceCenterDto serviceCenterDto = new ServiceCenterDto();
            serviceCenterDto.setCity(city);
            serviceCenterDto.setAddress(address);
            serviceCenterDto.setCenter(center);
            serviceCenterService.addServiceCenter(serviceCenterDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Successfully added new Service center"));
            mv.setViewName("redirect:/service-centers");
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/service-centers");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PostMapping("/remove-service-center")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView removeServiceCenter(@RequestParam int centerId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            ServiceCenterDto serviceCenterDto = new ServiceCenterDto();
            serviceCenterDto.setCentreId(centerId);
            serviceCenterService.removeServiceCenter(serviceCenterDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Successfully Removed"));
            mv.setViewName("redirect:/service-centers");
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/service-centers");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @GetMapping("/all-shipments")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewAllShipments(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("mail_list", mailService.getAllShipments());
            mv.setViewName("view-shipments.jsp");
        } catch (Exception e) {
            mv.setViewName("redirect:/admin-home");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;

    }

}
