package com.example.carbonfootprint;

import java.io.Serializable;

public class userInfo implements Serializable {
    String countryCode, demoTotal, avgValueWB, locationAvgTop, locationAvgBottom, units;
    Boolean locationCheck = false;
    double homeEnergyTotal, wasteTotal, naturalGas, electricity, fuelOil, propane, acThermostat, winterThermostat, reduceLighting, coldWater;
    double naturalGas2;
    double electricity2;
    double fuelOil2;
    double propane2;
    int energyStarRefridgeCO2;
    int energyStarWindow;
    int energyStarFurnaceBoiler = 0;
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

    public double getAcThermostat(){return acThermostat;}

    public void setAcThermostat(double acThermostat){ this.acThermostat = acThermostat;}

    public double getWinterThermostat(){return winterThermostat;}

    public void setWinterThermostat(double winterThermostat){ this.winterThermostat = winterThermostat;}

    public double getReduceLighting(){return reduceLighting;}

    public void setReduceLighting(double reduceLighting){ this.reduceLighting = reduceLighting;}

    public double getColdWater(){return coldWater;}

    public void setColdWater(double coldWater){ this.coldWater = coldWater;}

    public int getEnergyStarRefridgeCO2(){return energyStarRefridgeCO2;}

    public void setEnergyStarRefridgeCO2(int energyStarRefridgeCO2){ this.energyStarRefridgeCO2 = energyStarRefridgeCO2;}

    public int getEnergyStarWindow(){return energyStarWindow;}

    public void setEnergyStarWindow(int energyStarWindow){ this.energyStarWindow = energyStarWindow;}

    public int getEnergyStarFurnaceBoiler(){return energyStarFurnaceBoiler;}

    public void setEnergyStarFurnaceBoiler(int energyStarFurnaceBoiler){ this.energyStarFurnaceBoiler = energyStarFurnaceBoiler;}
}
