package serenitylabs.tutorials.insurance.car.domain;

/**
 * Created by sapurani on 10/6/2016.
 */
public class VehicleInformation {
    Manufacturer manufacturer;
    String model;
    String registrationYear;
    String numberOfDoorsOrStyle;
    String transmission;
    String engineCapacity;
    String trim;

    public String getRegistrationYear() {
        return registrationYear;
    }

    public String getNumberOfDoorsOrStyle() {
        return numberOfDoorsOrStyle;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public String getTrim() {
        return trim;
    }

    public VehicleInformation(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public VehicleInformation() {

    }

    public  VehicleInformation withManufacturer(Manufacturer manufacturer) {
        this.manufacturer =manufacturer;
        return  this;
    }

    public VehicleInformation andModel(String model) {
        this.model=model;
        return this;
    }

    public VehicleInformation andRegistrationYear(String registrationYear) {
        this.registrationYear=registrationYear;
        return this;
    }

    public VehicleInformation andNumberOfDoorsOrStyle(String numberOfDoorsOrStyle) {
        this.numberOfDoorsOrStyle=numberOfDoorsOrStyle;
        return  this;
    }

    public VehicleInformation andTransmission(String transmission) {
        this.transmission=transmission;
        return this;
    }

    public VehicleInformation andEngineCapacity(String engineCapacity) {
        this.engineCapacity=engineCapacity;
        return this;
    }

    public String getManufacturer() {
        return manufacturer.name();
    }

    public String getModel() {
        return model;
    }

    public VehicleInformation andTrim(String trim) {
        this.trim=trim;
        return this;
    }
}


