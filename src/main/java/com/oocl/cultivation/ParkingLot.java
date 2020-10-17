package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int numCarsParked;
    private int lotSize;
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

    public ParkingLot(int lotSize, int numCarsParked) {
        this.lotSize = lotSize;
        this.numCarsParked = numCarsParked;
    }

    public int getLotSize(){
        return lotSize;
    }

    public int getNumCarsParked(){
        return numCarsParked;
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

    public boolean isLotFull() {
        return this.numCarsParked > this.lotSize;
    }
}
