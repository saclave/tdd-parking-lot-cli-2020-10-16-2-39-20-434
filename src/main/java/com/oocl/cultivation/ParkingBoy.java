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

    public Car fetch(ParkingTicket parkingTicket) {
        car = (Car) parkingLot.getCar(parkingTicket);
        return car;
    }
}
