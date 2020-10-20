package com.oocl.cultivation;

import java.util.List;

//remove inheritance + use park&fetch Util
public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> managementList;

    public void setManagementList(List<ParkingBoy> managementList) {
        this.managementList = managementList;
    }

    public List<ParkingBoy> manageParkingBoys() {
        return this.managementList;
    }

    //no need to pass ParkingBoy
    public ParkingTicket assignParkingBoyToPark(Vehicle vehicle, ParkingBoy parkingBoy, ParkingLot parkingLot) throws ParkingSystemException {
       //merge condition
        if (isParkingBoyInList(parkingBoy)) {
            if(isParkingLotOwnedByParkingBoy(parkingBoy, parkingLot)){
                    return parkingBoy.parkCar(vehicle);
                }
            }
        return null;
    }

    private boolean isParkingLotOwnedByParkingBoy(ParkingBoy parkingBoy, ParkingLot parkingLot) {
        return parkingBoy.getParkingLot().stream()
                .anyMatch(lot -> lot == parkingLot);
    }

    private boolean isParkingBoyInList(ParkingBoy parkingBoy) {
        return managementList.stream()
                .anyMatch(list -> list == parkingBoy);
    }

    //merge condition
    public Vehicle assignParkBoyToFetch(ParkingTicket parkingTicket, ParkingBoy parkingBoy, ParkingLot parkingLot) throws ParkingSystemException {
        if (isParkingBoyInList(parkingBoy)) {
            if(isParkingLotOwnedByParkingBoy(parkingBoy, parkingLot)){
                return parkingBoy.fetchCar(parkingTicket);
            }
        }
        return null;
    }
}
