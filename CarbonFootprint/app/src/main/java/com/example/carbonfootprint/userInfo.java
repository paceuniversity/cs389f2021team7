package com.example.carbonfootprint;

import java.io.Serializable;
import java.util.ArrayList;

public class userInfo implements Serializable {
    String countryCode, name, timestamp, demoTotal, avgValueWB, locationAvgTop, locationAvgBottom, pastResultsName, pastResultsTime, pastResultsLocation, pastResultsHousehold, pastResultsHomeEnergy, pastResultsTransportation, pastResultsWaste, pastResultsTotal, pastResultsLocationAvg, pastResultsLocationName;
    Boolean locationCheck = false;
    Boolean pastResultsCheck = false;
    Boolean savePastResultsCheck = true;
    double homeEnergyTotal, wasteTotal, naturalGas, electricity, fuelOil, propane, appAvgValue, transportationTotal;
    int id, householdNumber;
    ArrayList<String> xmlcountryname, xmlcountrycode;
    Boolean NaturalGasCheck, ElectricityCheck, FuelOilCheck, PropaneCheck, MetalCheck, GlassCheck, PlasticCheck, NewspaperCheck, MagazinesCheck, CarMilesCheck;
    Boolean CarMaintenanceCheck = true;
    String NaturalGasValue, ElectricityValue, FuelOilValue, PropaneValue, CarMilesValue;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "countryCode='" + countryCode + '\'' +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", demoTotal='" + demoTotal + '\'' +
                ", avgValueWB='" + avgValueWB + '\'' +
                ", locationAvgTop='" + locationAvgTop + '\'' +
                ", locationAvgBottom='" + locationAvgBottom + '\'' +
                ", locationCheck=" + locationCheck +
                ", homeEnergyTotal=" + homeEnergyTotal +
                ", wasteTotal=" + wasteTotal +
                ", naturalGas=" + naturalGas +
                ", electricity=" + electricity +
                ", fuelOil=" + fuelOil +
                ", propane=" + propane +
                ", id=" + id +
                ", householdNumber=" + householdNumber +
                ", transportationTotal=" + transportationTotal +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

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

    public double getTransportationTotal() {
        return transportationTotal;
    }

    public void setTransportationTotal(double transportationTotal) {
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

    public String getPastResultsName() {
        return pastResultsName;
    }

    public void setPastResultsName(String pastResultsName) {
        this.pastResultsName = pastResultsName;
    }

    public String getPastResultsTime() {
        return pastResultsTime;
    }

    public void setPastResultsTime(String pastResultsTime) {
        this.pastResultsTime = pastResultsTime;
    }

    public String getPastResultsLocation() {
        return pastResultsLocation;
    }

    public void setPastResultsLocation(String pastResultsLocation) {
        this.pastResultsLocation = pastResultsLocation;
    }

    public String getPastResultsHousehold() {
        return pastResultsHousehold;
    }

    public void setPastResultsHousehold(String pastResultsHousehold) {
        this.pastResultsHousehold = pastResultsHousehold;
    }

    public String getPastResultsHomeEnergy() {
        return pastResultsHomeEnergy;
    }

    public void setPastResultsHomeEnergy(String pastResultsHomeEnergy) {
        this.pastResultsHomeEnergy = pastResultsHomeEnergy;
    }

    public String getPastResultsTransportation() {
        return pastResultsTransportation;
    }

    public void setPastResultsTransportation(String pastResultsTransportation) {
        this.pastResultsTransportation = pastResultsTransportation;
    }

    public String getPastResultsWaste() {
        return pastResultsWaste;
    }

    public void setPastResultsWaste(String pastResultsWaste) {
        this.pastResultsWaste = pastResultsWaste;
    }

    public String getPastResultsTotal() {
        return pastResultsTotal;
    }

    public void setPastResultsTotal(String pastResultsTotal) {
        this.pastResultsTotal = pastResultsTotal;
    }

    public double getAppAvgValue() {
        return appAvgValue;
    }

    public void setAppAvgValue(double appAvgValue) {
        this.appAvgValue = appAvgValue;
    }

    public Boolean getPastResultsCheck() {
        return pastResultsCheck;
    }

    public void setPastResultsCheck(Boolean pastResultsCheck) {
        this.pastResultsCheck = pastResultsCheck;
    }

    public Boolean getSavePastResultsCheck() {
        return savePastResultsCheck;
    }

    public void setSavePastResultsCheck(Boolean savePastResultsCheck) {
        this.savePastResultsCheck = savePastResultsCheck;
    }

    public ArrayList<String> getXmlcountryname() {
        return xmlcountryname;
    }

    public void setXmlcountryname(ArrayList<String> xmlcountryname) {
        this.xmlcountryname = xmlcountryname;
    }

    public ArrayList<String> getXmlcountrycode() {
        return xmlcountrycode;
    }

    public void setXmlcountrycode(ArrayList<String> xmlcountrycode) {
        this.xmlcountrycode = xmlcountrycode;
    }

    public String getPastResultsLocationAvg() {
        return pastResultsLocationAvg;
    }

    public void setPastResultsLocationAvg(String pastResultsLocationAvg) {
        this.pastResultsLocationAvg = pastResultsLocationAvg;
    }

    public String getPastResultsLocationName() {
        return pastResultsLocationName;
    }

    public void setPastResultsLocationName(String pastResultsLocationName) {
        this.pastResultsLocationName = pastResultsLocationName;
    }

    public Boolean getNaturalGasCheck() {
        return NaturalGasCheck;
    }

    public void setNaturalGasCheck(Boolean naturalGasCheck) {
        NaturalGasCheck = naturalGasCheck;
    }

    public Boolean getElectricityCheck() {
        return ElectricityCheck;
    }

    public void setElectricityCheck(Boolean electricityCheck) {
        ElectricityCheck = electricityCheck;
    }

    public Boolean getFuelOilCheck() {
        return FuelOilCheck;
    }

    public void setFuelOilCheck(Boolean fuelOilCheck) {
        FuelOilCheck = fuelOilCheck;
    }

    public Boolean getPropaneCheck() {
        return PropaneCheck;
    }

    public void setPropaneCheck(Boolean propaneCheck) {
        PropaneCheck = propaneCheck;
    }

    public Boolean getMetalCheck() {
        return MetalCheck;
    }

    public void setMetalCheck(Boolean metalCheck) {
        MetalCheck = metalCheck;
    }

    public Boolean getGlassCheck() {
        return GlassCheck;
    }

    public void setGlassCheck(Boolean glassCheck) {
        GlassCheck = glassCheck;
    }

    public Boolean getPlasticCheck() {
        return PlasticCheck;
    }

    public void setPlasticCheck(Boolean plasticCheck) {
        PlasticCheck = plasticCheck;
    }

    public Boolean getNewspaperCheck() {
        return NewspaperCheck;
    }

    public void setNewspaperCheck(Boolean newspaperCheck) {
        NewspaperCheck = newspaperCheck;
    }

    public Boolean getMagazinesCheck() {
        return MagazinesCheck;
    }

    public void setMagazinesCheck(Boolean magazinesCheck) {
        MagazinesCheck = magazinesCheck;
    }

    public Boolean getCarMilesCheck() {
        return CarMilesCheck;
    }

    public void setCarMilesCheck(Boolean carMilesCheck) {
        CarMilesCheck = carMilesCheck;
    }

    public Boolean getCarMaintenanceCheck() {
        return CarMaintenanceCheck;
    }

    public void setCarMaintenanceCheck(Boolean carMaintenanceCheck) {
        CarMaintenanceCheck = carMaintenanceCheck;
    }

    public String getNaturalGasValue() {
        return NaturalGasValue;
    }

    public void setNaturalGasValue(String naturalGasValue) {
        NaturalGasValue = naturalGasValue;
    }

    public String getElectricityValue() {
        return ElectricityValue;
    }

    public void setElectricityValue(String electricityValue) {
        ElectricityValue = electricityValue;
    }

    public String getFuelOilValue() {
        return FuelOilValue;
    }

    public void setFuelOilValue(String fuelOilValue) {
        FuelOilValue = fuelOilValue;
    }

    public String getPropaneValue() {
        return PropaneValue;
    }

    public void setPropaneValue(String propaneValue) {
        PropaneValue = propaneValue;
    }

    public String getCarMilesValue() {
        return CarMilesValue;
    }

    public void setCarMilesValue(String carMilesValue) {
        CarMilesValue = carMilesValue;
    }
}
