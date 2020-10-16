package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private Car car;
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private ParkingTicket parkingTicket;

    @BeforeEach
    void setup(){
        car = new Car();
        parkingLot = new ParkingLot();
        parkingTicket = new ParkingTicket();
    }

    @Test
    void test_when_parking_boy_parks_car_into_parking_lot_and_returns_parking_ticket() {
        //given
         parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void test_when_fetching_car_with_parking_ticket_from_parking_lot(){
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        //then
        assertEquals(car, fetchCar);
    }

    @Test
    void test_when_2_cars_parked_2_cars_fetched_from_parking_ticket_by_parking_boy(){
        //given
        Car car2 = new Car();
        parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingTicket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        Car fetchCar2 = parkingBoy.fetch(parkingTicket2);

        //then
        assertEquals(car, fetchCar);
        assertEquals(car2, fetchCar2);
    }

    @Test
    void test_when_wrong_ticket_issued_no_car_is_fetched(){
        //given
        parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingTicket = new ParkingTicket();
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        //then
        assertNull(fetchCar);
    }
}
