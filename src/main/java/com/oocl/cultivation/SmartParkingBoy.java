package com.oocl.cultivation;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{
    private int temp = 0;
    private int ctr = 0;

    @Override
    public ArrayList<ParkingTicket> parkMultipleCars(ArrayList<Car> carArrayList) throws ParkingSystemException {
        for(int car=0; car<carArrayList.size(); car++) {
            parkIntoMultipleLots();
            if (temp > 0) {
                this.checkParkingLot().get(ctr - 1).setParkedCarCount();
                this.parkCar(carArrayList.get(car));
            }
        }
        return parkingLot.getParkingTickets(carArrayList);
    }

    private void parkIntoMultipleLots() {
        for(ParkingLot lot : this.checkParkingLot()){
            if(temp < lot.getRemainingSlots()){
                temp = lot.getRemainingSlots();
                ctr += 1;
            }
        }
    }
}
