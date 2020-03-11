import {ParkingTypeOptionItemModel} from "./parkingTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "./propertyConditionTypeOptionItem.model";
import {PropertyConstructionTypeOptionItemModel} from "./propertyConstructionTypeOptionItem.model";
import {PropertyTypeOptionItemModel} from "./propertyTypeOptionItem.model";

export interface FormInitDataModel {
  parkingTypes: Array<ParkingTypeOptionItemModel>;
  propertyConditionTypes: Array<PropertyConditionTypeOptionItemModel>;
  propertyConstructionTypes: Array<PropertyConstructionTypeOptionItemModel>;
  propertyTypes: Array<PropertyTypeOptionItemModel>;
}
