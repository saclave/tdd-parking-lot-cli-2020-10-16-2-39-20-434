package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoysTest {
    private Vehicle vehicle;
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private ParkingTicket parkingTicket;

    @BeforeEach
    void setup(){
        vehicle = new Vehicle();
        parkingLot = new ParkingLot();
        parkingTicket = new ParkingTicket(true, false);
    }

    @Test
    void test_when_parking_boy_parks_car_into_parking_lot_and_returns_parking_ticket() throws ParkingSystemException {
        //given
         parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(vehicle);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void test_when_fetching_car_with_parking_ticket_from_parking_lot() throws ParkingSystemException {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(vehicle);
        Vehicle fetchVehicle = parkingBoy.fetchCar(parkingTicket);
        //then
        assertEquals(vehicle, fetchVehicle);
    }

    @Test
    void test_when_2_cars_parked_2_cars_fetched_from_parking_ticket_by_parking_boy() throws ParkingSystemException {
        //given
        Vehicle vehicle2 = new Vehicle();
        parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingTicket = parkingBoy.parkCar(vehicle);
        Vehicle fetchVehicle = parkingBoy.fetchCar(parkingTicket);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(vehicle2);
        Vehicle fetchVehicle2 = parkingBoy.fetchCar(parkingTicket2);

        //then
        assertEquals(vehicle, fetchVehicle);
        assertEquals(vehicle2, fetchVehicle2);
    }

    @Test
    void test_when_wrong_ticket_issued_no_car_is_fetched() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingBoy.fetchCar(new ParkingTicket(true, false));
        });
    }

    @Test
    void test_when_no_ticket_return_null_car() throws ParkingSystemException {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(null);
        Vehicle fetchVehicle = parkingBoy.fetchCar(parkingTicket);
        //then
        assertNull(fetchVehicle);
    }

    @Test
    void test_when_ticket_is_used_return_null_car() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(vehicle);
        Vehicle fetchVehicle = parkingBoy.fetchCar(parkingTicket);
        parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void test_when_parking_lot_capacity_is_1_when_car_is_parked_already_no_more_additional_car_and_null_ticket() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
        //given
        Vehicle vehicle2 = new Vehicle();
        parkingLot = new ParkingLot(1);
        parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingBoy.parkCar(vehicle);
        parkingBoy.parkCar(vehicle2);
        });
    }

    //Story 2
    @Test
    void test_when_no_ticket_provided_throw_error(){
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
            parkingBoy = new ParkingBoy(parkingLot);
            //when
            parkingTicket = null;
            parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void test_when_given_wrong_ticket_throw_error() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
        parkingBoy = new ParkingBoy(parkingLot);
        parkingTicket = new ParkingTicket(true, false);

        //when
        parkingBoy.checkTicket(parkingTicket);
        parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void test_when_customer_does_not_provide_parking_ticket(){
        //then
        assertThrows(ParkingSystemException.class, () -> {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        parkingTicket = null;

        //when
        parkingBoy.checkTicket(parkingTicket);
        parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void test_when_parking_lot_is_full_throw_full_capacity(){
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
            Vehicle vehicle2 = new Vehicle();
            parkingLot = new ParkingLot(1);
            parkingBoy = new ParkingBoy(parkingLot);

            //when
            parkingBoy.parkCar(vehicle);
            parkingBoy.parkCar(vehicle2);
        });
    }

    //Story 3
    @Test
    void test_when_2_parking_lots_for_not_smart_parking_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        parkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Vehicle vehicle = new Vehicle();
            try {
                parkingBoy.parkCar(vehicle);
            } catch (ParkingSystemException e) {
                e.printStackTrace();
            }
        });

        int actual1 = parkingLot1.getNumberOfParkedCars().size();
        int actual2 = parkingLot2.getNumberOfParkedCars().size();
        //then
        assertEquals(2, actual1);
        assertEquals(1, actual2);
    }

    //Story 4
    @Test
    void test_when_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        smartParkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Vehicle vehicle = new Vehicle();
            try {
                smartParkingBoy.parkCar(vehicle);
            } catch (ParkingSystemException e) {
                e.printStackTrace();
            }
        });
        int actual1 = parkingLot1.getNumberOfParkedCars().size();
        int actual2 = parkingLot2.getNumberOfParkedCars().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }

    //Story 5
    @Test
    void test_when_super_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        superSmartParkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Vehicle vehicle = new Vehicle();
            try {
                superSmartParkingBoy.parkCar(vehicle);
            } catch (ParkingSystemException e) {
                e.printStackTrace();
            }
        });
        int actual1 = parkingLot1.getNumberOfParkedCars().size();
        int actual2 = parkingLot2.getNumberOfParkedCars().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }
}
