package com.progmasters.moovsmart.dto;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdvertInitFormData {

    private List<PropertyTypeOption> propertyTypes = new ArrayList<>();
    private List<PropertyConditionOption> propertyConditionTypes = new ArrayList<>();
    private List<ParkingTypeOption> parkingTypes = new ArrayList<>();

    public PropertyAdvertInitFormData(List<PropertyTypeOption> propertyTypes,
                                      List<PropertyConditionOption> propertyConditionTypes,
                                      List<ParkingTypeOption> parkingTypes) {
        this.propertyTypes = propertyTypes;
        this.propertyConditionTypes = propertyConditionTypes;
        this.parkingTypes = parkingTypes;
    }

    public PropertyAdvertInitFormData() {
    }

    public List<PropertyTypeOption> getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(List<PropertyTypeOption> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public List<PropertyConditionOption> getPropertyConditionTypes() {
        return propertyConditionTypes;
    }

    public void setPropertyConditionTypes(List<PropertyConditionOption> propertyConditionTypes) {
        this.propertyConditionTypes = propertyConditionTypes;
    }


    public List<ParkingTypeOption> getParkingTypes() {
        return parkingTypes;
    }

    public void setParkingTypes(List<ParkingTypeOption> parkingTypes) {
        this.parkingTypes = parkingTypes;
    }
}
