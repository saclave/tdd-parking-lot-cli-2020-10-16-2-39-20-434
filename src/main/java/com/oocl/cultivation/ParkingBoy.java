package com.oocl.cultivation;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    private ParkingTicket parkingTicket;
    private Car car;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket parkCar(Car car) throws ParkingSystemException {
        parkingTicket = parkingLot.issueTicket(car);

        if(parkingTicket == null) {
            throw new ParkingSystemException("Not enough position");
        }
        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) throws ParkingSystemException {
        checkTicket(parkingTicket);
        car = parkingLot.getCar(parkingTicket);
        return car;
    }

    private boolean isNoTicket(ParkingTicket parkingTicket) {
        return parkingTicket == null;
    }

    public void checkTicket(ParkingTicket parkingTicket) throws ParkingSystemException {
        if (isNoTicket(parkingTicket)) {
            throw new ParkingSystemException("Please provide your parking ticket.");
        } else if (parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException("Unrecognized parking ticket.");
        } else if (!parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingSystemException("Unrecognized parking ticket.");
        }
    }
}
