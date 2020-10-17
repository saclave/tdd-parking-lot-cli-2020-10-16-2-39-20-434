package com.oocl.cultivation;

import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {
    private double temp;
    private int ctr = 0;

    @Override
    public ArrayList<ParkingTicket> parkMultipleCars(ArrayList<Car> carArrayList) throws ParkingSystemException {
        for(int car=0; car<carArrayList.size(); car++) {
            temp = 0;
            checkMultipleParkingAvailability();
            if (temp > 0) {
                this.getParkingLot().get(ctr).setParkedCarCount();
                this.parkCar(carArrayList.get(car));
            }
            else{
                this.checkIfFull(getParkingLot().get(ctr));
            }
        }
        return parkingLot.getParkingTickets(carArrayList);
    }

    private void checkMultipleParkingAvailability() {
        for(int lot = 0; lot < this.getParkingLot().size(); lot++){
            if(temp < getAvailableSlots(lot)){
                temp = (getAvailableSlots(lot));
                ctr = lot;
            }
        }
    }

    private double getAvailableSlots(int lot) {
        return this.getParkingLot().get(lot).getRemainingSlots() * 1.0 / this.getParkingLot().get(lot).getLotSize();
    }
}
