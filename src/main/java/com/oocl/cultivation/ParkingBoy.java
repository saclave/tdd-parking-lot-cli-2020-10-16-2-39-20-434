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
        checkTicket(parkingTicket);
        car = parkingLot.getCar(parkingTicket);
        return car;
    }

    private boolean isNoTicket(ParkingTicket parkingTicket) {
        return parkingTicket == null;
    }

    public void checkTicket(ParkingTicket parkingTicket) throws ParkingTicketException {
        if (isNoTicket(parkingTicket)) {
            throw new ParkingTicketException("Please provide your parking ticket.");
        } else if (parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingTicketException("Unrecognized parking ticket.");
        } else if (!parkingTicket.isProvided() && parkingTicket.isUsed()) {
            throw new ParkingTicketException("Unrecognized parking ticket.");
        }
    }
}
