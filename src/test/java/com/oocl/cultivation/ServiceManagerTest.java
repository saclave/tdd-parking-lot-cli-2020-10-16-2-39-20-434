package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceManagerTest {

    @Test
    void test_service_manager_adding_parking_boys_to_the_management_list(){
        //given
        ServiceManager serviceManager = new ServiceManager();
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (new ParkingBoy(), new ParkingBoy(), new ParkingBoy()));
        //when
        serviceManager.setManagementList(parkingBoyArrayList);
        //then
        assertNotNull(serviceManager.manageParkingBoys());
    }
}

//AC1. The parking lot service manager can add parking boys to management list.
// And the parking lot manager can specify a parking boy on the list to park or fetch the car
// (only from the parking lots managed by that parking boy).
