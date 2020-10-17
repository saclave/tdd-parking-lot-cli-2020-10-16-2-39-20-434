package com.oocl.cultivation;

import java.util.ArrayList;

public class ServiceManager extends ParkingBoy{
    private ArrayList<ParkingBoy> managementList;

    public void setManagementList(ArrayList<ParkingBoy> managementList) {
        this.managementList = managementList;
    }

    public ArrayList<ParkingBoy> manageParkingBoys() {
        return this.managementList;
    }
}
