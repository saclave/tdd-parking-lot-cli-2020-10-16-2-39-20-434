package com.oocl.cultivation;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{
    private int temp = 0;
    private int ctr = 0;

    @Override
    public ArrayList<ParkingTicket> parkMultipleCars(ArrayList<Car> carArrayList) throws ParkingSystemException {
        parkIntoMultipleLots();
        if(temp > 0){
            this.checkParkingLot().get(ctr).setParkedCarCount();
            this.parkCar(carArrayList.get(carArrayList.size()-1));
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
