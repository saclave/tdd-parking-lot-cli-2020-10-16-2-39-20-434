package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoysTest {
    private Car car;
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private ParkingTicket parkingTicket;

    @BeforeEach
    void setup(){
        car = new Car();
        parkingLot = new ParkingLot();
        parkingTicket = new ParkingTicket(true, false);
    }

    @Test
    void test_when_parking_boy_parks_car_into_parking_lot_and_returns_parking_ticket() throws ParkingSystemException {
        //given
         parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void test_when_fetching_car_with_parking_ticket_from_parking_lot() throws ParkingSystemException {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);
        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void test_when_2_cars_parked_2_cars_fetched_from_parking_ticket_by_parking_boy() throws ParkingSystemException {
        //given
        Car car2 = new Car();
        parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        Car fetchCar2 = parkingBoy.fetchCar(parkingTicket2);

        //then
        assertEquals(car, fetchCar);
        assertEquals(car2, fetchCar2);
    }

    @Test
    void test_when_wrong_ticket_issued_no_car_is_fetched() throws ParkingSystemException {
        //then
        assertThrows(ParkingSystemException.class, () -> {
            //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        Car fetchCar = parkingBoy.fetchCar(new ParkingTicket());
        });
    }

    @Test
    void test_when_no_ticket_return_null_car() throws ParkingSystemException {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(null);
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);
        //then
        assertNull(fetchCar);
    }

    @Test
    void test_when_ticket_is_used_return_null_car() throws ParkingSystemException {
        //then
        assertThrows(ParkingSystemException.class, () -> {
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetchCar(parkingTicket);
        parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void test_when_parking_lot_capacity_is_1_when_car_is_parked_already_no_more_additional_car_and_null_ticket() {
        //then
        assertThrows(ParkingSystemException.class, () -> {
        //given
        Car car2 = new Car();
        parkingLot = new ParkingLot(1);
        parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingBoy.parkCar(car);
        parkingBoy.parkCar(car2);
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
            Car car2 = new Car();
            parkingLot = new ParkingLot(1);
            parkingBoy = new ParkingBoy(parkingLot);

            //when
            parkingBoy.parkCar(car);
            parkingBoy.parkCar(car2);
        });
    }

    //Story 3
    @Test
    void test_when_2_parking_lots_for_not_smart_parking_boy() throws ParkingSystemException {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        parkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Car car = new Car();
            try {
                parkingBoy.parkCar(car);
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
    void test_when_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() throws ParkingSystemException {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        superSmartParkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Car car = new Car();
            try {
                superSmartParkingBoy.parkCar(car);
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
    void test_when_super_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() throws ParkingSystemException {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));

        //when
        superSmartParkingBoy.setParkingLotArrayList(parkingLotArrayList);
        IntStream.range(0, 3).forEach(cars -> {
            Car car = new Car();
            try {
                superSmartParkingBoy.parkCar(car);
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
