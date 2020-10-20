package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static com.oocl.cultivation.ParkingSystemException.*;

public class ParkAndFetch {

    private List<ParkingLot> parkingLotArrayList;
    private ParkingLot parkingLot;

    private ParkAndFetch() { }

    static ParkAndFetch of() {
        return new ParkAndFetch();
    }

    ParkAndFetch setParkingLotList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

    ParkAndFetch setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        return this;
    }

    ParkingTicket parkCar(Vehicle vehicle) {
        return parkingLot.issueTicket(vehicle);
    }

    public Vehicle fetchCar(ParkingTicket parkingTicket) {
        return parkingLotArrayList.stream()
                .filter(lot -> lot.getTicketCarMap().containsKey(checkTicket(parkingTicket)))
                .map(lot -> lot.getCar(parkingTicket))
                .findFirst()
                .orElseThrow(() -> new ParkingSystemException(UNRECOGNIZED_PARKING_TICKET));
    }

    private ParkingTicket checkTicket(ParkingTicket parkingTicket) {
        return Optional.ofNullable(parkingTicket)
                .orElseThrow(() -> new ParkingSystemException(PROVIDE_PARKING_TICKET));
    }

}
