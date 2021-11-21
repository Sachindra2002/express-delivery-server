package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


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

    @GetMapping("/add-driver")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        DriverDetail driverDetail = new DriverDetail();
        User user = new User();
        mv.setViewName("add-driver.jsp");
        mv.addObject("driverDetail", driverDetail);
        mv.addObject("user", user);
        //Get list of service centers for "Add Driver"
        mv.addObject("centers", serviceCenterService.getAllServiceCenters());

        return mv;
    }

    @PostMapping("/add-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addDriver(@Valid @ModelAttribute("user") User user, BindingResult bindingUser,
                                  @Valid @ModelAttribute("driverDetail") DriverDetail driverDetail, BindingResult bindingDriver,
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

                driverService.addDriver(userDto, driverDetailDto, user.getLocation());
                driverService.addDriverDetails(driverDetailDto, user.getEmail());

                DocumentsDto dto = new DocumentsDto();

                storageService.uploadNicFile(nicImage, user.getEmail(), dto);
                storageService.uploadLicenceFile(licence, user.getEmail(), dto);
                storageService.uploadInsuaranceFile(insurance, user.getEmail(), dto);

                redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Driver added successfully"));
                mv.setViewName("redirect:/drivers");


            } catch (ExpressDeliveryException e) {
                mv.addObject("error", new APIException(e.getMessage()));
            }
        }
        return mv;
    }

}
