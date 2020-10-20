package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static com.oocl.cultivation.ParkingSystemException.*;

public class ServiceManager{
    private ArrayList<ParkingLot> parkingLotArrayList;
    private List<ParkingBoy> managementList;

    public List<ParkingBoy> manageParkingBoys() {
        return this.managementList;
    }

    public ServiceManager(ParkingLot parkingLot) {
        this.parkingLotArrayList = new ArrayList<>();
        this.parkingLotArrayList.add(parkingLot);
    }

    public ServiceManager setManagementList (List<ParkingBoy> managementList){
        this.managementList = managementList;
        return this;
    }

    public ServiceManager() {
        this(new ParkingLot());
    }

    public ParkingTicket parkCar(Vehicle vehicle) {
        if(vehicle == null){
            throw new ParkingSystemException(MISSING_CAR);
        }

        ParkingLot parkingLot = findAvailableParkingLot();
        return ParkAndFetch.of()
                .setParkingLot(parkingLot)
                .parkCar(vehicle);
    }

    public ParkingLot findAvailableParkingLot() {
        return parkingLotArrayList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(() -> new ParkingSystemException(NOT_ENOUGH_POSITION));
    }

    public Vehicle fetchCar(ParkingTicket parkingTicket) {
        return ParkAndFetch.of()
                .setParkingLotList(parkingLotArrayList)
                .fetchCar(parkingTicket);
    }
}
