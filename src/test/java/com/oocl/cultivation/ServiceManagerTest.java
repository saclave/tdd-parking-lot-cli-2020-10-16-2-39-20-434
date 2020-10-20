package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceManagerTest {
    private ParkingBoy parkingBoy1 = new ParkingBoy();
    private ParkingBoy parkingBoy2 = new ParkingBoy();
    private ParkingBoy parkingBoy3 = new ParkingBoy();
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingLot parkingLot3;
    private Vehicle vehicle = new Vehicle();
    private ServiceManager serviceManager = new ServiceManager();

    @BeforeEach
    void setup(){
    }

    @Test
    void should_return_list_when_service_manager_adding_parking_boys_to_the_management_list(){
        //given
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2, parkingBoy3));
        //when
        serviceManager.setManagementList(parkingBoyArrayList);
        //then
        assertNotNull(serviceManager.manageParkingBoys());
    }

    @Test
    void should_return_ticket_from_parking_boy_when_to_specify_which_parking_boy_can_park_car_by_service_manager() throws ParkingSystemException {
        //given
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);
        parkingLot3 = new ParkingLot(3);
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2, parkingBoy3));

        //when
        parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2, parkingLot3)));
        serviceManager.setManagementList(parkingBoyArrayList);

        //then
        assertNotNull(serviceManager.parkCar(vehicle));
    }

    @Test
    void should_return_car_from_parking_boy_when_to_specify_which_parking_boy_can_fetch_car_by_service_manager() throws ParkingSystemException {
        //given
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);
        parkingLot3 = new ParkingLot(3);
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2, parkingBoy3));

        //when
        parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2, parkingLot3)));
        parkingBoy2.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
        serviceManager.setManagementList(parkingBoyArrayList);

        //then
        assertNotNull(serviceManager.parkCar(vehicle));
    }

    @Test
    void should_return_ticket_for_service_managerwhen_park_car_by_service_manager_from_managed_parking_lot() throws ParkingSystemException {
        //given
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);

        //when
        ServiceManager serviceManager = new ServiceManager(parkingLot1);

        //then
        assertNotNull(serviceManager.parkCar(vehicle));
    }

}

