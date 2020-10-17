package com.oocl.cultivation;

import java.util.ArrayList;

public class ServiceManager extends ParkingBoy{
    private ArrayList<ParkingBoy> managementList;

    public void setManagementList(ArrayList<ParkingBoy> managementList) {
        this.managementList = managementList;
    }

    public ArrayList<ParkingBoy> manageParkingBoys() {
        return this.managementList;
    }

    public ParkingTicket assignParkingBoyToPark(Car car, ParkingBoy parkingBoy, ParkingLot parkingLot) throws ParkingSystemException {
        if (isParkingBoyInList(parkingBoy)) {
            if(isParkingLotOwnedByParkingBoy(parkingBoy, parkingLot)){
                    return parkingBoy.parkCar(car);
                }
            } else {
            return null;
        }
        return null;
    }

    private boolean isParkingLotOwnedByParkingBoy(ParkingBoy parkingBoy, ParkingLot parkingLot) {
        return parkingBoy.getParkingLot().stream()
                .anyMatch(lot -> lot == parkingLot);
    }

    private boolean isParkingBoyInList(ParkingBoy parkingBoy) {
        return managementList.stream()
                .anyMatch(managementList -> managementList == parkingBoy);
    }

    public Car assignParkBoyToFetch(ParkingTicket parkingTicket, ParkingBoy parkingBoy, ParkingLot parkingLot) throws ParkingSystemException {
        if (isParkingBoyInList(parkingBoy)) {
            if(isParkingLotOwnedByParkingBoy(parkingBoy, parkingLot)){
                return parkingBoy.fetchCar(parkingTicket);
            }
        } else {
            return null;
        }
        return null;
    }
}
