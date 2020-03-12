import {ParkingTypeOptionItemModel} from "./parkingTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "./propertyConditionTypeOptionItem.model";
import {PropertyTypeOptionItemModel} from "./propertyTypeOptionItem.model";

export interface FormInitDataModel {
  parkingType: Array<ParkingTypeOptionItemModel>;
  propertyConditionType: Array<PropertyConditionTypeOptionItemModel>;
  propertyType: Array<PropertyTypeOptionItemModel>;
}
