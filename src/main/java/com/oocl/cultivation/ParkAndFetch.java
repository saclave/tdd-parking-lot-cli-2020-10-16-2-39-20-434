package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static com.oocl.cultivation.ParkingSystemException.*;

public abstract class ParkAndFetch {

    private List<ParkingLot> parkingLotArrayList;

    private ParkAndFetch() { }

    public ParkAndFetch setParkingLotList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

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

    public abstract void setParkingLotArrayList(List<ParkingLot> parkingLotArrayList);
}
