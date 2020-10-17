package com.oocl.cultivation;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int numCarsParked;
    private int lotSize;
    private int size;
    private ParkingTicket parkingTicket;
    private Car car;
    private Map<ParkingTicket, Car> carMap = new HashMap<>();
    private boolean IsFull = false;

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

    public int getRemainingSlots() {
        return lotSize - numCarsParked;
    }

    public void setParkedCarCount() {
        if(this.numCarsParked == this.lotSize){
            this.IsFull = true;
        }
        else{
            numCarsParked += 1;
            lotSize -= 1;
        }
    }

    public int getNumCarsParked(){
        return numCarsParked;
    }

    public ArrayList<ParkingTicket> getParkingTickets(ArrayList<Car> carArrayList) {
        ArrayList<ParkingTicket> ticketArrayList = new ArrayList<>();
        for(int ticket=0; ticket < carArrayList.size(); ticket++){
            ticketArrayList.add(new ParkingTicket());
        }

        return ticketArrayList;
    }
}
