package com.oocl.cultivation;

public class ParkingTicket {
    private boolean isUsed;
    private boolean isProvided;

    public ParkingTicket(boolean isProvided, boolean isUsed){
        this.isUsed = isUsed;
        this.isProvided = isProvided;
    }

    public boolean isProvided() {
        return isProvided;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
