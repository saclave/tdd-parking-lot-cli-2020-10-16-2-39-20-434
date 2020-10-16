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
        this(10);
    }

    public ParkingTicket issueTicket(Car car) {
        parkingTicket = new ParkingTicket();
        carMap.put(parkingTicket, car);
        return getAvailableParkingLotSpace() < 0 ? parkingTicket : null;
    }

    private int getAvailableParkingLotSpace() {
        return carMap.size() - size;
    }

    public Car getCar(ParkingTicket parkingTicket) {
        car = carMap.get(parkingTicket);
        carMap.remove(parkingTicket);
        return car;
    }
}
