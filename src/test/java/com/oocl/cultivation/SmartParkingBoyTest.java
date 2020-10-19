package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SmartParkingBoyTest {

    @Test
    void test_when_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() throws ParkingSystemException {
        //Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ArrayList<Car> carArrayList = new ArrayList<>(asList(new Car(), new Car(), new Car()));
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(
                new ParkingLot(3, 0),
                new ParkingLot(5, 0),
                new ParkingLot(3, 0)));

        //when
        smartParkingBoy.setMultipleParkingLots(parkingLotArrayList);
        smartParkingBoy.parkMultipleCars(carArrayList);
        int carsParkedPerLot[] = {0, 3, 0};

        //then
        assertArrayEquals(carsParkedPerLot, smartParkingBoy.getParkingLotCount());
    }
}
