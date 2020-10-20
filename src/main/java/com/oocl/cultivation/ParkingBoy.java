package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static com.oocl.cultivation.ParkingSystemException.*;

public class ParkingBoy {
    private List<ParkingLot> parkingLotArrayList;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLotArrayList = new ArrayList<>();
        this.parkingLotArrayList.add(parkingLot);
    }

    public ParkingBoy() {
        this(new ParkingLot());
    }

    //create util
    public ParkingTicket parkCar(Vehicle vehicle) throws ParkingSystemException {
        ParkingLot parkingLot = findAvailableParkingLot();
        if (parkingLot == null) {
            throw new ParkingSystemException(NOT_ENOUGH_POSITION);
        }
       ParkingTicket parkingTicket = parkingLot.issueTicket(vehicle);

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

    public List<ParkingLot> getParkingLot() {
        return parkingLotArrayList;
    }

    //create util
    public Vehicle fetchCar(ParkingTicket parkingTicket) throws ParkingSystemException {
        checkTicket(parkingTicket);
        for(ParkingLot parkingLot : parkingLotArrayList){
            //just use size of the map
            for(Vehicle vehicle : parkingLot.getCarList()){
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
    //merge exceptions

    public ParkingBoy setMultipleParkingLots(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

    public void setParkingLotArrayList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
