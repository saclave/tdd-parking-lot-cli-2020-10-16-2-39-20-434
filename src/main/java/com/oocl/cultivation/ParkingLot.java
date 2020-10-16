package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int size;
    private ParkingTicket parkingTicket;
    private Car car;
    private Map<ParkingTicket, Car> carMap = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public ParkingLot() {

    }

    public ParkingTicket issueTicket(Car car) {
        parkingTicket = new ParkingTicket();
        carMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car getCar(ParkingTicket parkingTicket) {
        car = carMap.get(parkingTicket);
        carMap.remove(parkingTicket);
        return car;
    }
}
