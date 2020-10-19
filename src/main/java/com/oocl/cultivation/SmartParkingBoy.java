package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;

import static com.oocl.cultivation.ParkingSystemException.NOT_ENOUGH_POSITION;

public class SmartParkingBoy extends ParkingBoy{
    private ArrayList<ParkingLot> parkingLotArrayList;

    public SmartParkingBoy(ParkingLot parkingLot){
        super(parkingLot);
    }

    @Override
    public ParkingLot findAvailableParkingLot() throws ParkingSystemException {
        return parkingLotArrayList.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableSpace))
                .orElseThrow(() -> new ParkingSystemException(NOT_ENOUGH_POSITION));
    }

    @Override
    public void setParkingLotArrayList(ArrayList<ParkingLot> parkingLotArrayList) {
        this.parkingLotArrayList = parkingLotArrayList;
    }
}
