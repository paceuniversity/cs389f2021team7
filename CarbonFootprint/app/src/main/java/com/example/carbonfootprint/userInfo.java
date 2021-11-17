package com.example.carbonfootprint;

import java.io.Serializable;

public class userInfo implements Serializable {
    String countryCode, demoTotal, avgValueWB, locationAvgTop, locationAvgBottom, units;
    Boolean locationCheck = false;
    double homeEnergyTotal, wasteTotal, naturalGas, electricity, fuelOil, propane;
    int householdNumber, transportationTotal;

    public double getNaturalGas() {
        return naturalGas;
    }

    public void setNaturalGas(double naturalGas) {
        this.naturalGas = naturalGas;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public double getFuelOil() {
        return fuelOil;
    }

    public void setFuelOil(double fuelOil) {
        this.fuelOil = fuelOil;
    }

    public double getPropane() {
        return propane;
    }

    public void setPropane(double propane) {
        this.propane = propane;
    }

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

    public String getUnits(){ return units;}

    public void setUnits(String units){this.units = units;}
}
