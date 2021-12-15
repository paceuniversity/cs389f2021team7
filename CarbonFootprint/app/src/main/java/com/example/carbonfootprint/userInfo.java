package com.example.carbonfootprint;

import java.io.Serializable;
import java.util.ArrayList;

public class userInfo implements Serializable {
    String countryCode, name, timestamp, demoTotal, avgValueWB, locationAvgTop, locationAvgBottom, pastResultsName, pastResultsTime, pastResultsLocation, pastResultsHousehold, pastResultsHomeEnergy, pastResultsTransportation, pastResultsWaste, pastResultsTotal, pastResultsLocationAvg, pastResultsLocationName, PrimaryHeat;
    Boolean locationCheck = false;
    Boolean pastResultsCheck = false;
    Boolean savePastResultsCheck = true;
    Boolean unitsLocationCheck = true;
    double homeEnergyTotal, wasteTotal, naturalGas, electricity, fuelOil, propane, appAvgValue, transportationTotal;
    int id;
    int householdNumber = 0;
    ArrayList<String> xmlcountryname, xmlcountrycode;
    Boolean NaturalGasCheck, ElectricityCheck, FuelOilCheck, PropaneCheck, MetalCheck, GlassCheck, PlasticCheck, NewspaperCheck, MagazinesCheck, CarMilesCheck;
    Boolean CarMaintenanceCheck = true;
    String NaturalGasValue, ElectricityValue, FuelOilValue, PropaneValue, CarMilesValue;
    boolean metricSystem, imperialSystem;
    boolean heq1, heq2, heq3, heq4, rheq1, rheq2, rheq3, rheq4, rheq5, rheq6, rheq7, heqcomplete;
    boolean tq1, tq2, tq3, tq4, tq5, tq6, tq7, tqcomplete;
    boolean wqcomplete;
    boolean retrieveCheck = false;
    boolean disableFirebase = false;

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

    public boolean isMetricSystem() {
        return metricSystem;
    }

    public void setMetricSystem(boolean metricSystem) {
        this.metricSystem = metricSystem;
    }

    public boolean isImperialSystem() {
        return imperialSystem;
    }

    public void setImperialSystem(boolean imperialSystem) {
        this.imperialSystem = imperialSystem;
    }

    public boolean isHeq1() {
        return heq1;
    }

    public void setHeq1(boolean heq1) {
        this.heq1 = heq1;
    }

    public boolean isHeq2() {
        return heq2;
    }

    public void setHeq2(boolean heq2) {
        this.heq2 = heq2;
    }

    public boolean isHeq3() {
        return heq3;
    }

    public void setHeq3(boolean heq3) {
        this.heq3 = heq3;
    }

    public boolean isHeq4() {
        return heq4;
    }

    public void setHeq4(boolean heq4) {
        this.heq4 = heq4;
    }

    public boolean isRheq1() {
        return rheq1;
    }

    public void setRheq1(boolean rheq1) {
        this.rheq1 = rheq1;
    }

    public boolean isRheq2() {
        return rheq2;
    }

    public void setRheq2(boolean rheq2) {
        this.rheq2 = rheq2;
    }

    public boolean isRheq3() {
        return rheq3;
    }

    public void setRheq3(boolean rheq3) {
        this.rheq3 = rheq3;
    }

    public boolean isRheq4() {
        return rheq4;
    }

    public void setRheq4(boolean rheq4) {
        this.rheq4 = rheq4;
    }

    public boolean isRheq5() {
        return rheq5;
    }

    public void setRheq5(boolean rheq5) {
        this.rheq5 = rheq5;
    }

    public boolean isRheq6() {
        return rheq6;
    }

    public void setRheq6(boolean rheq6) {
        this.rheq6 = rheq6;
    }

    public boolean isRheq7() {
        return rheq7;
    }

    public void setRheq7(boolean rheq7) {
        this.rheq7 = rheq7;
    }

    public boolean isHeqcomplete() {
        return heqcomplete;
    }

    public void setHeqcomplete(boolean heqcomplete) {
        this.heqcomplete = heqcomplete;
    }

    public boolean isTq1() {
        return tq1;
    }

    public void setTq1(boolean tq1) {
        this.tq1 = tq1;
    }

    public boolean isTq2() {
        return tq2;
    }

    public void setTq2(boolean tq2) {
        this.tq2 = tq2;
    }

    public boolean isTq3() {
        return tq3;
    }

    public void setTq3(boolean tq3) {
        this.tq3 = tq3;
    }

    public boolean isTq4() {
        return tq4;
    }

    public void setTq4(boolean tq4) {
        this.tq4 = tq4;
    }

    public boolean isTq5() {
        return tq5;
    }

    public void setTq5(boolean tq5) {
        this.tq5 = tq5;
    }

    public boolean isTq6() {
        return tq6;
    }

    public void setTq6(boolean tq6) {
        this.tq6 = tq6;
    }

    public boolean isTq7() {
        return tq7;
    }

    public void setTq7(boolean tq7) {
        this.tq7 = tq7;
    }

    public boolean isTqcomplete() {
        return tqcomplete;
    }

    public void setTqcomplete(boolean tqcomplete) {
        this.tqcomplete = tqcomplete;
    }

    public boolean isWqcomplete() {
        return wqcomplete;
    }

    public void setWqcomplete(boolean wqcomplete) {
        this.wqcomplete = wqcomplete;
    }

    public Boolean getUnitsLocationCheck() {
        return unitsLocationCheck;
    }

    public void setUnitsLocationCheck(Boolean unitsLocationCheck) {
        this.unitsLocationCheck = unitsLocationCheck;
    }
    public String getPrimaryHeat(){return PrimaryHeat;}

    public void setPrimaryHeat(String primaryHeat){PrimaryHeat = primaryHeat;}

    public boolean isRetrieveCheck() {
        return retrieveCheck;
    }

    public void setRetrieveCheck(boolean retrieveCheck) {
        this.retrieveCheck = retrieveCheck;
    }

    public boolean isDisableFirebase() {
        return disableFirebase;
    }

    public void setDisableFirebase(boolean disableFirebase) {
        this.disableFirebase = disableFirebase;
    }
}
