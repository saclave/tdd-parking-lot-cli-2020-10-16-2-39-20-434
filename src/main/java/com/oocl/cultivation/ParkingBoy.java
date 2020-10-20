package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static com.oocl.cultivation.ParkingSystemException.MISSING_CAR;
import static com.oocl.cultivation.ParkingSystemException.NOT_ENOUGH_POSITION;

public class ParkingBoy {
    private List<ParkingLot> parkingLotArrayList;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLotArrayList = new ArrayList<>();
        this.parkingLotArrayList.add(parkingLot);
    }

    public ParkingBoy() {
        this(new ParkingLot());
    }

    public ParkingTicket parkCar(Vehicle vehicle) throws ParkingSystemException {
        if(vehicle == null){
            throw new ParkingSystemException(MISSING_CAR);
        }

        ParkingLot parkingLot = findAvailableParkingLot();
        return ParkAndFetch.of()
                .setParkingLot(parkingLot)
                .parkCar(vehicle);
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

    public Vehicle fetchCar(ParkingTicket parkingTicket) {
        return ParkAndFetch.of()
                .setParkingLotList(parkingLotArrayList)
                .fetchCar(parkingTicket);
    }

    public ParkingBoy setMultipleParkingLots(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
        return this;
    }

    public void setParkingLotArrayList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
