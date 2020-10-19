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

    public ParkingTicket parkCar(Vehicle vehicle) throws ParkingSystemException {
        parkingLot = findAvailableParkingLot();
        if (parkingLot == null) {
            throw new ParkingSystemException(NOT_ENOUGH_POSITION);
        }
        parkingTicket = parkingLot.issueTicket(vehicle);

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

    public ArrayList<ParkingLot> getParkingLot() {
        return parkingLotArrayList;
    }

    public Vehicle fetchCar(ParkingTicket parkingTicket) throws ParkingSystemException {
        checkTicket(parkingTicket);
        for(ParkingLot parkingLot : parkingLotArrayList){
            for(Vehicle vehicle : parkingLot.getCars()){
                vehicle = parkingLot.getCar(parkingTicket);
                return vehicle;
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

    public void setParkingLotArrayList(ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
