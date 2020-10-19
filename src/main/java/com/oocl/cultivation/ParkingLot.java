package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private ArrayList<Vehicle> vehicleArrayList;
    private int size;
    private Map<ParkingTicket, Vehicle> ticketCarMap = new HashMap<>();

    public ParkingLot(int size) {
        this.vehicleArrayList = new ArrayList<>();
        this.size = size;
    }

    public ParkingLot() {
        this(10);
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        ParkingTicket parkingTicket = new ParkingTicket(true, false);

        ticketCarMap.put(parkingTicket, vehicle);
        parkCar(vehicle);
        return getAvailableParkingLotSpace() >= 0 ? parkingTicket : null;
    }

    private void parkCar(Vehicle vehicle) {
        vehicleArrayList.add(vehicle);
    }

    private int getAvailableParkingLotSpace() {
        return size - ticketCarMap.size();
    }

    public Vehicle getCar(ParkingTicket parkingTicket) {
        Vehicle vehicle = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        removeCar(vehicle);
        return vehicle;
    }

    private void removeCar(Vehicle vehicle) {
        vehicleArrayList.remove(vehicle);
    }

    public boolean isFull() {
        return ticketCarMap.size() >= size;
    }

    public int getAvailableSpace() {
        return size - ticketCarMap.size();
    }

    public List<Vehicle> getCars() {
        return vehicleArrayList;
    }

    public int getAverageAvailableSlot() {
        return getAvailableParkingLotSpace() / size * 100;
    }

    Map<ParkingTicket, Vehicle> getNumberOfParkedCars() {
        return ticketCarMap;
    }
}
