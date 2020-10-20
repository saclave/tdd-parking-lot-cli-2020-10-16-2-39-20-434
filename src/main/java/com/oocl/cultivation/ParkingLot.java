package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Map<ParkingTicket, Vehicle> ticketCarMap = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
        this.ticketCarMap = new HashMap<>();
    }

    //add constant name for default size
    public ParkingLot() {
        this.size = DEFAULT_SIZE;
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        ParkingTicket parkingTicket = new ParkingTicket();

        ticketCarMap.put(parkingTicket, vehicle);
        return parkingTicket;
    }


    private int getAvailableParkingLotSpace() {
        return size - ticketCarMap.size();
    }

    public Vehicle getCar(ParkingTicket parkingTicket) {
        Vehicle vehicle = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return vehicle;
    }


    public boolean isFull() {
        return ticketCarMap.size() >= size;
    }

    public int getAvailableSpace() {
        return size - ticketCarMap.size();
    }


    public int getAverageAvailableSlot() {
        return getAvailableParkingLotSpace() / size * 100;
    }

    Map<ParkingTicket, Vehicle> getTicketCarMap() {
        return ticketCarMap;
    }
}
