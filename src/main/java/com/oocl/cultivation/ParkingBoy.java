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

    public Car fetchCar(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException {
        if(isTicketValid(parkingTicket)){
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
        car = parkingLot.getCar(parkingTicket);
        return car;
    }

    private boolean isTicketValid(ParkingTicket parkingTicket) {
        return parkingTicket == null;
    }
}
