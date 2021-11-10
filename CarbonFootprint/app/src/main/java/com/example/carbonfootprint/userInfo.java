package com.example.carbonfootprint;

import java.io.Serializable;

public class userInfo implements Serializable {
    String countryCode, demoTotal, avgValueWB, locationAvgTop, locationAvgBottom;
    Boolean locationCheck = false;
    double homeEnergyTotal, wasteTotal;
    int householdNumber, transportationTotal;

    public int getTransportationTotal() {
        return transportationTotal;
    }

    public void setTransportationTotal(int transportationTotal) {
        this.transportationTotal = transportationTotal;
    }

    public int getHouseholdNumber() {
        return householdNumber;
    }

    public void setHouseholdNumber(int householdNumber) {
        this.householdNumber = householdNumber;
    }

    public double getHomeEnergyTotal() {
        return homeEnergyTotal;
    }

    public void setHomeEnergyTotal(double homeEnergyTotal) {
        this.homeEnergyTotal = homeEnergyTotal;
    }

    public double getWasteTotal() {
        return wasteTotal;
    }

    public void setWasteTotal(double wasteTotal) {
        this.wasteTotal = wasteTotal;
    }

    public Boolean getLocationCheck() {
        return locationCheck;
    }

    public void setLocationCheck(Boolean locationCheck) {
        this.locationCheck = locationCheck;
    }

    public String getAvgValueWB() {
        return avgValueWB;
    }

    public void setAvgValueWB(String avgValueWB) {
        this.avgValueWB = avgValueWB;
    }

    public String getLocationAvgTop() {
        return locationAvgTop;
    }

    public void setLocationAvgTop(String locationAvgTop) {
        this.locationAvgTop = locationAvgTop;
    }

    public String getLocationAvgBottom() {
        return locationAvgBottom;
    }

    public void setLocationAvgBottom(String locationAvgBottom) {
        this.locationAvgBottom = locationAvgBottom;
    }

    public String getDemoTotal() {
        return demoTotal;
    }

    public void setDemoTotal(String demoTotal) {
        this.demoTotal = demoTotal;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
