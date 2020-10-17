package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private ParkingTicket parkingTicket;
    private Car car;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLot.issueTicket(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) throws ParkingTicketException {
        if(checkTicket(parkingTicket)){
            throw new ParkingTicketException("Unrecognized parking ticket.");
        }
        car = parkingLot.getCar(parkingTicket);
        return car;
    }

    private boolean isNoTicket(ParkingTicket parkingTicket) {
        return parkingTicket == null;
    }

    public boolean checkTicket(ParkingTicket parkingTicket) {
        if(isNoTicket(parkingTicket)){
            return true;
        }
        else if(parkingTicket.isProvided() && parkingTicket.isUsed()){
            return true;
        }
        else if(!parkingTicket.isProvided() && parkingTicket.isUsed()){
            return true;
        }else{
            return false;
        }
    }
}
