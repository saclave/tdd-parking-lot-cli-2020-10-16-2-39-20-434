package com.oocl.cultivation;

import java.util.List;

import static com.oocl.cultivation.ParkingSystemException.NOT_ENOUGH_POSITION;

public class SuperSmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLotArrayList;

    @Override
    public ParkingLot findAvailableParkingLot() throws ParkingSystemException {
        return parkingLotArrayList.stream().reduce((parkingLot, parkingLot2) -> parkingLot.getAverageAvailableSlot() >
                parkingLot2.getAverageAvailableSlot() ? parkingLot : parkingLot2)
                .orElseThrow(() -> new ParkingSystemException(NOT_ENOUGH_POSITION));
    }

    @Override
    public void setParkingLotArrayList(List<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
