import {ImageModel} from "../../../../src/main/java/com/progmasters/moovsmart/dto/image.model";

export interface PropertyAdvertDetailsModel {
  price: number,
  listOfImages: Array<ImageModel>,
  advertId: number,
  propertyType: string;
  propertyConditionType: string;
  parkingType: string;
  title: string;
  placeId: number;

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
