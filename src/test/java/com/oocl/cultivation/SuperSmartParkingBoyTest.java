package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SuperSmartParkingBoyTest {

    @Test
    void when_super_smart_parking_boy_parks_cars_in_multiple_lots_and_gets_the_average_of_space_with_most_slots() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
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
        int actual1 = parkingLot1.getTicketCarMap().size();
        int actual2 = parkingLot2.getTicketCarMap().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }
}
