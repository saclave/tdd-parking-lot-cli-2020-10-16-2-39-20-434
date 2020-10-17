package com.oocl.cultivation;

import java.util.ArrayList;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private ParkingTicket parkingTicket;
    private Car car;
    private ArrayList<ParkingLot> parkingLotArrayList;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLotArrayList = new ArrayList<>();
        this.parkingLotArrayList.add(parkingLot);
    }

    public ParkingBoy() {
        this(new ParkingLot());
    }

    public ParkingTicket parkCar(Car car) throws ParkingSystemException {
        parkingTicket = parkingLot.issueTicket(car);

        if(parkingTicket == null) {
            throw new ParkingSystemException("Not enough position");
        }
        return parkingTicket;
    }

    public ParkingTicket parkMultipleCars(ArrayList<Car> carArrayList) throws ParkingSystemException {
        for(Car car : carArrayList){
            parkIntoMultipleLots(car);
        }
        if(carArrayList.size() > 0){
            this.checkParkingLot();
        }
        return parkingTicket;
    }

    private ArrayList<ParkingLot> checkParkingLot() {
        return parkingLotArrayList;
    }

    private void parkIntoMultipleLots(Car car) throws ParkingSystemException {
        for(ParkingLot lot : parkingLotArrayList){
            if(lot.getRemainingSlots() > 0){
                lot.setParkedCarCount();
                this.parkCar(car);
                break;
            }
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) throws ParkingSystemException {
        checkTicket(parkingTicket);
        car = parkingLot.getCar(parkingTicket);
        return car;
    }

    public void checkTicket(ParkingTicket parkingTicket) throws ParkingSystemException {
        if (parkingTicket == null) {
            throw new ParkingSystemException("Please provide your parking ticket.");
        } else if (parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException("Unrecognized parking ticket.");
        } else if (!parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException("Unrecognized parking ticket.");
        }
    }

    public ParkingBoy setMultipleParkingLots(ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

    public int[] getParkingLotCount() {
        int[] lotSpace = new int[parkingLotArrayList.size()];
        for(int size = 0; size < parkingLotArrayList.size(); size++){
            lotSpace[size] = parkingLotArrayList.get(size).getNumCarsParked();
        }
        return lotSpace;
    }

}
