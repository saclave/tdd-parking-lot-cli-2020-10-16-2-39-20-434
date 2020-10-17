package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceManagerTest {
    private ParkingBoy parkingBoy1 = new ParkingBoy();
    private ParkingBoy parkingBoy2 = new ParkingBoy();
    private ParkingBoy parkingBoy3 = new ParkingBoy();
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingLot parkingLot3;
    private Car car = new Car();
    private ServiceManager serviceManager = new ServiceManager();

    @BeforeEach
    void setup(){
    }

    @Test
    void test_service_manager_adding_parking_boys_to_the_management_list(){
        //given
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2, parkingBoy3));
        //when
        serviceManager.setManagementList(parkingBoyArrayList);
        //then
        assertNotNull(serviceManager.manageParkingBoys());
    }

    @Test
    void test_to_specify_which_parking_boy_can_park_car_by_service_manager(){
        //given
        parkingLot1 = new ParkingLot(1,0);
        parkingLot2 = new ParkingLot(2, 0);
        parkingLot3 = new ParkingLot(3, 0);
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2, parkingBoy3));

        parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2, parkingLot3)));
        serviceManager.setManagementList(parkingBoyArrayList));

        assertNotNull(serviceManager.assignParkingBoy(1, car, parkingBoy1));
    }
}

//AC1. The parking lot service manager can add parking boys to management list.
// And the parking lot manager can specify a parking boy on the list to park or fetch the car
// (only from the parking lots managed by that parking boy).
