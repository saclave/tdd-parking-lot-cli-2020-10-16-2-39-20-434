package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private ParkingTicket parkingTicket;
    private Car car;
    private Map<ParkingTicket, Car> carMap = new HashMap<>();

    public ParkingTicket issueTicket(Car car) {
        parkingTicket = new ParkingTicket();
        carMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car getCar(ParkingTicket parkingTicket) {
        car = carMap.get(parkingTicket);
        return car;
    }
}
