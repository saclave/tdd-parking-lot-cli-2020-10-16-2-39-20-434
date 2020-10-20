package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

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
        return ParkAndFetch.of()
                .setParkingLotList(parkingLotArrayList)
                .parkCar(vehicle);
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
