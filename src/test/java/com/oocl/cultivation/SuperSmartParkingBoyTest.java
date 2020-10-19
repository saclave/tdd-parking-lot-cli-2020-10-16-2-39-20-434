package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class SuperSmartParkingBoyTest {

    @Test
    void test_when_super_smart_parking_boy_parks_multiple_cars_in_multiple_parking_lots() throws ParkingSystemException {
        //Given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        ArrayList<Car> carArrayList = new ArrayList<>(asList(new Car(), new Car(), new Car()));
        ArrayList<ParkingLot> parkingLotArrayList = new ArrayList<>(asList(
                new ParkingLot(2, 0),
                new ParkingLot(3, 0)));

        //when
        superSmartParkingBoy.setMultipleParkingLots(parkingLotArrayList);
        superSmartParkingBoy.parkMultipleCars(carArrayList);
        int carsParkedPerLot[] = {1,2};

        //then
        assertArrayEquals(carsParkedPerLot, superSmartParkingBoy.getParkingLotCount());
    }
}
