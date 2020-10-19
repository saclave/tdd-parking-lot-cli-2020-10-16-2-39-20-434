package com.oocl.cultivation;

import java.util.ArrayList;

import static com.oocl.cultivation.ParkingSystemException.NOT_ENOUGH_POSITION;

public class SuperSmartParkingBoy extends ParkingBoy {
    private ArrayList<ParkingLot> parkingLotArrayList;

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingLot findAvailableParkingLot() throws ParkingSystemException {
        return parkingLotArrayList.stream().reduce((parkingLot, parkingLot2) -> parkingLot.getAverageAvailableSlot() >
                parkingLot2.getAverageAvailableSlot() ? parkingLot : parkingLot2)
                .orElseThrow(() -> new ParkingSystemException(NOT_ENOUGH_POSITION));
    }

    @Override
    public void setParkingLotArrayList(ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
