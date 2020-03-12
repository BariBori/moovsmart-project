import {ParkingTypeOptionItemModel} from "./parkingTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "./propertyConditionTypeOptionItem.model";
import {PropertyTypeOptionItemModel} from "./propertyTypeOptionItem.model";

export interface FormInitDataModel {
  parkingTypes: Array<ParkingTypeOptionItemModel>;
  propertyConditionTypes: Array<PropertyConditionTypeOptionItemModel>;
  propertyTypes: Array<PropertyTypeOptionItemModel>;
}
