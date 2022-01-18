package com.sachindrarodrigo.express_delivery_server;

import com.sachindrarodrigo.express_delivery_server.dto.VehicleDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.VehicleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class VehicleServiceTests {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TestUtil testUtil;

    private int vehicleId, deleteVehicleId;

    @BeforeAll
    public void init() throws ExpressDeliveryException {
        deleteVehicleId = testUtil.createVehicleToBeDeleted();
    }

    @Test
    @Order(1)
    public void testAddVehicle() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleNumber("TestNumber");
        vehicleDto.setVehicleType("TestType");
        vehicleDto.setStatus("TestStatus");

        VehicleDto result = vehicleService.addVehicle(vehicleDto);

        assertNotNull(result);

        System.out.println("[TEST] Adding vehicle [PASSED]");
    }

    @Test
    @Order(2)
    public void testGetAllVehicles() {
        List<VehicleDto> results = vehicleService.getAllVehicles();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all vehicles [PASSED]");
    }

    @Test
    @Order(3)
    public void testGetAllAvailableVehicles() {
        List<VehicleDto> results = vehicleService.getAvailableVehicles();

        boolean isTrue = results.size() > 0;

        assertTrue(isTrue);

        System.out.println("[TEST] Get all available vehicles [PASSED]");
    }

    @Test
    @Order(4)
    public void testSetVehicleUnavailable() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(deleteVehicleId);
        VehicleDto result = vehicleService.setVehicleUnAvailable(vehicleDto);

        assertNotNull(result);
        System.out.println("[TEST] Set Vehicle Unavailable [PASSED]");
    }

    @Test
    @Order(5)
    public void testSetVehicleAvailable() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(deleteVehicleId);
        VehicleDto result = vehicleService.setVehicleAvailable(vehicleDto);

        assertNotNull(result);
        System.out.println("[TEST] Set Vehicle Available [PASSED]");
    }

    @Test
    @Order(6)
    public void testSetVehicleBlacklist() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(deleteVehicleId);
        VehicleDto result = vehicleService.setBlacklist(vehicleDto);

        assertNotNull(result);
        System.out.println("[TEST] Set Vehicle Blacklist [PASSED]");
    }

    @Test
    @Order(7)
    public void testRemoveVehicleBlacklist() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(deleteVehicleId);
        VehicleDto result = vehicleService.removeBlacklist(vehicleDto);

        assertNotNull(result);
        System.out.println("[TEST] Remove Vehicle Blacklist [PASSED]");
    }

    @Test
    @Order(8)
    public void testDeleteVehicle() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(deleteVehicleId);
        vehicleService.deleteVehicle(vehicleDto);
        List<VehicleDto> results = vehicleService.getAllVehicles();

        boolean isTrue = true;

        for (VehicleDto dto : results) {
            if (dto.getVehicleId() == deleteVehicleId) {
                isTrue = false;
                break;
            }
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Delete Vehicle [PASSED]");
    }

    @Test
    public void testAddVehicleWithSameVehicleNumber() throws ExpressDeliveryException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleNumber("TestNumber");
        vehicleDto.setVehicleType("TestType");
        vehicleDto.setStatus("TestStatus");

        boolean isTrue = false;

        try{
            vehicleService.addVehicle(vehicleDto);
        }catch (ExpressDeliveryException e){
            if (e.getMessage().equals("Vehicle number already exists")) isTrue = true;
        }

        assertTrue(isTrue);

        System.out.println("[TEST] Attempt to add vehicle with existing vehicle number [PASSED]");
    }
}
