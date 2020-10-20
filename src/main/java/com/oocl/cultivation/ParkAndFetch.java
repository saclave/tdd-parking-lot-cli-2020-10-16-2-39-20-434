package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static com.oocl.cultivation.ParkingSystemException.*;

public class ParkAndFetch {

    private List<ParkingLot> parkingLotArrayList;
    private ParkingLot parkingLot;

    private ParkAndFetch() { }

    public static ParkAndFetch of() {
        return new ParkAndFetch();
    }

    public ParkAndFetch setParkingLotList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

    public ParkAndFetch setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        return this;
    }

    public ParkingTicket parkCar(Vehicle vehicle) throws ParkingSystemException {
        return parkingLot.issueTicket(vehicle);
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

}
