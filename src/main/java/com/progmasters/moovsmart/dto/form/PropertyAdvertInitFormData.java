package com.progmasters.moovsmart.dto.form;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdvertInitFormData {

    private List<PropertyTypeOption> propertyType = new ArrayList<>();
    private List<PropertyConditionOption> propertyConditionType = new ArrayList<>();
    private List<ParkingTypeOption> parkingType = new ArrayList<>();

    public PropertyAdvertInitFormData(List<PropertyTypeOption> propertyTypes,
                                      List<PropertyConditionOption> propertyConditionTypes,
                                      List<ParkingTypeOption> parkingTypes) {
        this.propertyType = propertyTypes;
        this.propertyConditionType = propertyConditionTypes;
        this.parkingType = parkingTypes;
    }

    public PropertyAdvertInitFormData() {
    }

    public List<PropertyTypeOption> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(List<PropertyTypeOption> propertyType) {
        this.propertyType = propertyType;
    }

    public List<PropertyConditionOption> getPropertyConditionType() {
        return propertyConditionType;
    }

    public void setPropertyConditionType(List<PropertyConditionOption> propertyConditionType) {
        this.propertyConditionType = propertyConditionType;
    }

    public List<ParkingTypeOption> getParkingType() {
        return parkingType;
    }

    public void setParkingType(List<ParkingTypeOption> parkingType) {
        this.parkingType = parkingType;
    }
}
