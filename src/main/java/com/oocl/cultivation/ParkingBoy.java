package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(vehicle == null){
            throw new RuntimeException();
        }

        ParkingLot parkingLot = findAvailableParkingLot();
        return parkingLot.issueTicket(vehicle);
    }

    public ParkingLot findAvailableParkingLot() throws ParkingSystemException {
        return parkingLotArrayList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(() -> new ParkingSystemException(NOT_ENOUGH_POSITION));
    }

    public List<ParkingLot> getParkingLot() {
        return parkingLotArrayList;
    }

    //create util
    public Vehicle fetchCar(ParkingTicket parkingTicket) {
        return parkingLotArrayList.stream()
                .filter(parkingLot -> parkingLot.getTicketCarMap().containsKey(checkTicket(parkingTicket)))
                .map(parkingLot -> parkingLot.getCar(parkingTicket))
                .findFirst()
                .orElseThrow(() -> new ParkingSystemException(UNRECOGNIZED_PARKING_TICKET));
    }

    public ParkingTicket checkTicket(ParkingTicket parkingTicket) throws ParkingSystemException {
        return Optional.ofNullable(parkingTicket)
                .orElseThrow(() -> new ParkingSystemException(PROVIDE_PARKING_TICKET));
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
