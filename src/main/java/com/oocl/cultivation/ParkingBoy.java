package com.oocl.cultivation;

import java.util.ArrayList;

import static com.oocl.cultivation.ParkingSystemException.*;

public class ParkingBoy {
    public ParkingLot parkingLot;
    public ParkingTicket parkingTicket;
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
        parkingLot = findAvailableParkingLot();
        if (parkingLot == null) {
            throw new ParkingSystemException(NOT_ENOUGH_POSITION);
        }
        parkingTicket = parkingLot.issueTicket(car);

        return parkingTicket;
    }

    public ParkingLot findAvailableParkingLot() throws ParkingSystemException {
        for(ParkingLot parkingLot : parkingLotArrayList){
            if(!parkingLot.isFull()){
                return parkingLot;
            }
        }
        return null;
    }

    public ArrayList<ParkingTicket> parkMultipleCars(ArrayList<Car> carArrayList) throws ParkingSystemException {
        for(Car car : carArrayList){
            parkIntoMultipleLots(car);
        }
        if(carArrayList.size() > 0){
            this.checkIfFull(getParkingLot().get(0));
        }
        return parkingLot.getParkingTickets(carArrayList);
    }

    public boolean checkIfFull(ParkingLot parkingLot) throws ParkingSystemException {
        if(parkingLot.isFull()){
            throw new ParkingSystemException(NOT_ENOUGH_POSITION);
        }
        else{
            return false;
        }
    }

    public ArrayList<ParkingLot> getParkingLot() {
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
        for(ParkingLot parkingLot : parkingLotArrayList){
            for(Car car : parkingLot.getCars()){
                car = parkingLot.getCar(parkingTicket);
                return car;
            }
        }
        throw new ParkingSystemException(UNRECOGNIZED_PARKING_TICKET);
    }

    public void checkTicket(ParkingTicket parkingTicket) throws ParkingSystemException {
        if (parkingTicket == null) {
            throw new ParkingSystemException(PROVIDE_PARKING_TICKET);
        } else if (parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException(UNRECOGNIZED_PARKING_TICKET);
        } else if (!parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException(UNRECOGNIZED_PARKING_TICKET);
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

    public void setParkingLotArrayList(ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
