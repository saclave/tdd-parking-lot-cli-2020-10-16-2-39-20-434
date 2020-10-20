package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartParkingBoyTest {

    @Test
    void when_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots_which_has_most_cases() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(parkingLot1, parkingLot2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

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
        int actual1 = parkingLot1.getTicketCarMap().size();
        int actual2 = parkingLot2.getTicketCarMap().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }
}
