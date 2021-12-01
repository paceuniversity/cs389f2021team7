package com.example.carbonfootprint;

public class PastResultsData {
    private int id;
    private String name, time, total, location, household, transportation, homeEnergy, waste, locationAvg;

    public PastResultsData(int id, String name, String time, String location, String household, String homeEnergy, String transportation, String waste, String total, String locationAvg) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.total = total;
        this.location = location;
        this.household = household;
        this.transportation = transportation;
        this.homeEnergy = homeEnergy;
        this.waste = waste;
        this.locationAvg = locationAvg;
    }

    @Override
    public String toString() {
        return "PastResultsData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", total='" + total + '\'' +
                ", location='" + location + '\'' +
                ", household='" + household + '\'' +
                ", transportation='" + transportation + '\'' +
                ", homeEnergy='" + homeEnergy + '\'' +
                ", waste='" + waste + '\'' +
                ", locationAvg='" + locationAvg + '\'' +
                '}';
    }

    public PastResultsData() {
    }

    public int getId() {
        return id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getHomeEnergy() {
        return homeEnergy;
    }

    public void setHomeEnergy(String homeEnergy) {
        this.homeEnergy = homeEnergy;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getLocationAvg() {
        return locationAvg;
    }

    public void setLocationAvg(String locationAvg) {
        this.locationAvg = locationAvg;
    }
}
