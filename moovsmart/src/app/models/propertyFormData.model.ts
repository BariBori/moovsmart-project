import {PropertyTypeOptionItemModel} from "./propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "./propertyConditionTypeOptionItem.model";
import {PropertyConstructionTypeOptionItemModel} from "./propertyConstructionTypeOptionItem.model";
import {ParkingTypeOptionItemModel} from "./parkingTypeOptionItem.model";

export interface PropertyFormDataModel {

  price: number;
  listOfImages: Array<string>;
  advertId: number;
  propertyType: string;
  propertyConditionType: string;
  propertyConstructionType: string;
  parkingType: string;
  title: string;

  address: string;
  city: string;
  district: string;
  street: string;

  area: number;
  numberOfRooms: number;

  elevator: boolean;
  balcony: boolean;

  description: string;

  advertStatus: string;

}
