package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private ArrayList<Car> carArrayList;
    private int size;
    private Car car;
    private Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot(int size) {
        this.carArrayList = new ArrayList<>();
        this.size = size;
    }

    public ParkingLot() {
        this(10);
    }

    public ParkingTicket issueTicket(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket(true, false);

        ticketCarMap.put(parkingTicket, car);
        parkCar(car);
        return getAvailableParkingLotSpace() >= 0 ? parkingTicket : null;
    }

    private void parkCar(Car car) {
        carArrayList.add(car);
    }

    private int getAvailableParkingLotSpace() {
        return size - ticketCarMap.size();
    }

    public Car getCar(ParkingTicket parkingTicket) {
        car = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        removeCar(car);
        return car;
    }

    private void removeCar(Car car) {
        carArrayList.remove(car);
    }

    public boolean isFull() {
        return ticketCarMap.size() >= size;
    }

    public int getAvailableSpace() {
        return size - ticketCarMap.size();
    }

    public List<Car> getCars() {
        return carArrayList;
    }

    public int getAverageAvailableSlot() {
        return getAvailableParkingLotSpace() / size * 100;
    }

    Map<ParkingTicket, Car> getNumberOfParkedCars() {
        return ticketCarMap;
    }
}
