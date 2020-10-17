package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    public boolean isFull = false;
    private ArrayList<Car> carArrayList;
    private int numCarsParked;
    private int size;
    private ParkingTicket parkingTicket;
    private Car car;
    private Map<ParkingTicket, Car> carMap = new HashMap<>();

    public ParkingLot(int size) {
        this.carArrayList = new ArrayList<>();
        this.size = size;
    }

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int lotSize, int numCarsParked) {
        this.size = lotSize;
        this.numCarsParked = numCarsParked;
        this.carArrayList = new ArrayList<>();
    }

    public ParkingTicket issueTicket(Car car) {
        parkingTicket = new ParkingTicket(true, false);

        carMap.put(parkingTicket, car);
        parkCar(car);
        return getAvailableParkingLotSpace() >= 0 ? parkingTicket : null;
    }

    private void parkCar(Car car) {
        carArrayList.add(car);
    }

    private int getAvailableParkingLotSpace() {
        return size - carMap.size();
    }

    public Car getCar(ParkingTicket parkingTicket) {
        car = carMap.get(parkingTicket);
        carMap.remove(parkingTicket);
        removeCar(car);
        return car;
    }

    private void removeCar(Car car) {
        carArrayList.remove(car);
    }

    public int getRemainingSlots() {
        return size - numCarsParked;
    }

    public int getLotSize(){
        return size;
    }

    public void setParkedCarCount() {
        if(this.numCarsParked == this.size){
            return;
        }
        else{
            numCarsParked += 1;
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

    public boolean isFull() {
        return carArrayList.size() >= size;
    }

    public List<Car> getCars() {
        return carArrayList;
    }
}
