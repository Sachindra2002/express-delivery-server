package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import com.sachindrarodrigo.express_delivery_server.service.ServiceCenterService;
import com.sachindrarodrigo.express_delivery_server.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class AdminWebController {

    private final DriverService driverService;
    private final ServiceCenterService serviceCenterService;
    private final StorageService storageService;

    @GetMapping("/drivers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewAllDrivers() {
        return getAllDrivers();
    }

    public ModelAndView getAllDrivers() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage-drivers.jsp");
        mv.addObject("driver_list", driverService.getAllDrivers());

        //Get list of service centers for "Add Driver" Modal
        mv.addObject("centers", serviceCenterService.getAllServiceCenters());
        return mv;
    }

    @GetMapping("/view-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView viewDriver(@RequestParam int driverId) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view-driver.jsp");
        mv.addObject("driver", driverService.getDriverInfo(driverId));
        return mv;
    }

    @PostMapping("/add-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addDriver(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String phone, @RequestParam String location,
                                  @RequestParam String center, @RequestParam String dob, @RequestParam String nic, @RequestParam String address, @RequestParam(value = "nicImage") MultipartFile nicImage,
                                  @RequestParam(value = "licence") MultipartFile licence, @RequestParam(value = "insurance") MultipartFile insurance,
                                  RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {

            UserDto userDto = new UserDto();
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setEmail(email);
            userDto.setPhoneNumber(phone);
            userDto.setLocation(location);

            DriverDetailDto driverDetailDto = new DriverDetailDto();
            driverDetailDto.setDOB(dob);
            driverDetailDto.setNIC(nic);
            driverDetailDto.setAddress(address);

            driverService.addDriver(userDto, driverDetailDto, center);
            driverService.addDriverDetails(driverDetailDto, email);

            DocumentsDto dto = new DocumentsDto();

            storageService.uploadNicFile(nicImage, email, dto);
            storageService.uploadLicenceFile(licence, email, dto);
            storageService.uploadInsuaranceFile(insurance, email, dto);

            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Driver added successfully"));
            mv.setViewName("redirect:/drivers");


        } catch (ExpressDeliveryException e) {
            mv.addObject("error", new APIException(e.getMessage()));
        }

        return mv;
    }

}
