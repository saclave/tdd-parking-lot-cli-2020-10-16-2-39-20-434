package com.oocl.cultivation;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;

public class ServiceManager extends ParkingBoy{
    private ArrayList<ParkingBoy> managementList;
    private static final int PARK = 1;
    private static final int FETCH = 0;

    public void setManagementList(ArrayList<ParkingBoy> managementList) {
        this.managementList = managementList;
    }

    public ArrayList<ParkingBoy> manageParkingBoys() {
        return this.managementList;
    }

    public Object assignParkingBoy(int indicator, Car car, ParkingBoy parkingBoy, ParkingLot parkingLot) throws ParkingSystemException {
        if (isParkingBoyInList(parkingBoy)) {
            if(indicator == PARK){
                if(isParkingLotOwnedByParkingBoy(parkingBoy, parkingLot)){
                    return parkingBoy.parkCar(car);
                }
            }
            else if(indicator == FETCH){

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
}
