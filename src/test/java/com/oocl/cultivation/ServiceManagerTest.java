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
    void test_to_specify_which_parking_boy_can_park_car_by_service_manager() throws ParkingSystemException {
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
        assertNotNull(serviceManager.assignParkingBoyToPark(vehicle, parkingBoy1, parkingLot1));
    }

    @Test
    void test_to_specify_which_parking_boy_can_fetch_car_by_service_manager() throws ParkingSystemException {
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
        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(vehicle, parkingBoy1, parkingLot1);

        //then
        assertNotNull(serviceManager.assignParkBoyToFetch(parkingTicket, parkingBoy2, parkingLot1));
    }

    @Test
    void test_service_manager_give_no_ticket_if_parking_boy_to_park_is_not_in_management_list() throws ParkingSystemException {
        //given
        parkingLot1 = new ParkingLot(1);
        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                (parkingBoy1, parkingBoy2));

        //when
        parkingBoy3.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
        serviceManager.setManagementList(parkingBoyArrayList);

        //then
        assertNull(serviceManager.assignParkingBoyToPark(vehicle, parkingBoy3, parkingLot1));
    }

    @Test
    void test_park_car_by_service_manager_from_managed_parking_lot() throws ParkingSystemException {
        //given
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);

        //when
        serviceManager.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2)));

        //then
        assertNotNull(serviceManager.parkCar(vehicle));
    }

    @Test
    void test_when_park_of_parking_boy_throw_error_from_wrong_ticket(){
        //then
        assertThrows(ParkingSystemException.class, () -> {
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
            ParkingTicket parkingTicket = new ParkingTicket(true, true);
            serviceManager.assignParkBoyToFetch(parkingTicket, parkingBoy2, parkingLot1);
        });
    }

    @Test
    void test_when_parking_lot_capacity_is_1_when_car_is_parked_already_throw_error() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
            parkingLot1 = new ParkingLot(1);
            ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                    (parkingBoy1, parkingBoy2, parkingBoy3));

            //when
            parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
            serviceManager.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
            serviceManager.setManagementList(parkingBoyArrayList);

            serviceManager.assignParkingBoyToPark(vehicle, parkingBoy1, parkingLot1);
            serviceManager.parkCar(vehicle);
        });
    }

    @Test
    void test_when_no_ticket_provided_throw_error(){
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
            parkingLot1 = new ParkingLot(1);
            ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
                    (parkingBoy1, parkingBoy2, parkingBoy3));

            //when
            parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
            serviceManager.setManagementList(parkingBoyArrayList);

            serviceManager.assignParkBoyToFetch(null, parkingBoy1, parkingLot1);
        });
    }
}

