package com.oocl.cultivation;

public class ParkingSystemException extends RuntimeException {
    public static final String NOT_ENOUGH_POSITION =  "Not enough position.";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    public static final String PROVIDE_PARKING_TICKET = "Please provide your parking ticket.";
    public static final String MISSING_CAR = "Car is missing";

    public ParkingSystemException(String message) {
        super(message);
    }
}

