//package com.oocl.cultivation;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static java.util.Arrays.asList;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ServiceManagerTest {
//    private ParkingBoy parkingBoy1 = new ParkingBoy();
//    private ParkingBoy parkingBoy2 = new ParkingBoy();
//    private ParkingBoy parkingBoy3 = new ParkingBoy();
//    private ParkingLot parkingLot1;
//    private ParkingLot parkingLot2;
//    private ParkingLot parkingLot3;
//    private Vehicle vehicle = new Vehicle();
//    private ServiceManager serviceManager = new ServiceManager();
//
//    @BeforeEach
//    void setup(){
//    }
//
//    @Test
//    void when_service_manager_adding_parking_boys_to_the_management_list(){
//        //given
//        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
//                (parkingBoy1, parkingBoy2, parkingBoy3));
//        //when
//        serviceManager.setManagementList(parkingBoyArrayList);
//        //then
//        assertNotNull(serviceManager.manageParkingBoys());
//    }
//
//    @Test
//    void when_to_specify_which_parking_boy_can_park_car_by_service_manager_then_return_ticket_from_parking_boy() throws ParkingSystemException {
//        //given
//        parkingLot1 = new ParkingLot(1);
//        parkingLot2 = new ParkingLot(2);
//        parkingLot3 = new ParkingLot(3);
//        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
//                (parkingBoy1, parkingBoy2, parkingBoy3));
//
//        //when
//        parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2, parkingLot3)));
//        serviceManager.setManagementList(parkingBoyArrayList);
//
//        //then
//        assertNotNull(serviceManager.assignParkingBoyToPark(vehicle, parkingBoy1, parkingLot1));
//    }
//
//    @Test
//    void when_to_specify_which_parking_boy_can_fetch_car_by_service_manager_then_return_car_from_parking_boy() throws ParkingSystemException {
//        //given
//        parkingLot1 = new ParkingLot(1);
//        parkingLot2 = new ParkingLot(2);
//        parkingLot3 = new ParkingLot(3);
//        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
//                (parkingBoy1, parkingBoy2, parkingBoy3));
//
//        //when
//        parkingBoy1.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2, parkingLot3)));
//        parkingBoy2.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
//        serviceManager.setManagementList(parkingBoyArrayList);
//        //change this method
//        ParkingTicket parkingTicket = serviceManager.assignParkingBoyToPark(vehicle, parkingBoy1, parkingLot1);
//
//        //then
//        assertNotNull(serviceManager.assignParkBoyToFetch(parkingTicket, parkingBoy2, parkingLot1));
//    }
//
//    @Test
//    void when_service_manager_give_no_ticket_if_parking_boy_to_park_is_not_in_management_list_then_return_null() throws ParkingSystemException {
//        //given
//        parkingLot1 = new ParkingLot(1);
//        ArrayList<ParkingBoy> parkingBoyArrayList = new ArrayList<>(asList
//                (parkingBoy1, parkingBoy2));
//
//        //when
//        parkingBoy3.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1)));
//        serviceManager.setManagementList(parkingBoyArrayList);
//
//        //then
//        assertNull(serviceManager.assignParkingBoyToPark(vehicle, parkingBoy3, parkingLot1));
//    }
//
//    @Test
//    void when_park_car_by_service_manager_from_managed_parking_lot_then_return_ticket_for_service_manager() throws ParkingSystemException {
//        //given
//        parkingLot1 = new ParkingLot(1);
//        parkingLot2 = new ParkingLot(2);
//
//        //when
//        serviceManager.setMultipleParkingLots(new ArrayList<>(asList(parkingLot1, parkingLot2)));
//
//        //then
//        assertNotNull(serviceManager.parkCar(vehicle));
//    }
//
//}
//
