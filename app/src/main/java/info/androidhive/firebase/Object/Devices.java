package info.androidhive.firebase.Object;

/**
 * Created by radek on 10.10.16.
 Aplikacja Radosława Subczynskiego
 */

public class Devices {

    private String deviceName;
    private String stocktakingData;
    private String location;
    private String stocktakingPerson;
    private String mark;
    private String model;
    private String serialNumber;
    private String typeDevice;
    private String itemListEmployees = "";

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStocktakingData() {
        return stocktakingData;
    }

    public void setStocktakingData(String stocktakingData) {
        this.stocktakingData = stocktakingData;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStocktakingPerson() {
        return stocktakingPerson;
    }

    public void setStocktakingPerson(String stocktakingPerson) {
        this.stocktakingPerson = stocktakingPerson;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTypeDevice() {
        return typeDevice;
    }

    public void setTypeDevice(String typeDevice) {
        this.typeDevice = typeDevice;
    }

    public String getItemListEmployees() {
        return itemListEmployees;
    }

    public void setItemListEmployees(String itemListEmployees) {
        this.itemListEmployees = itemListEmployees;
    }
}
