export interface PropertyEditModel {
  id?: number;

  price: number;
  // listOfImages: Array<string>;
  advertId: number;

  propertyType: string;
  propertyConditionType: string;
  parkingType: string;
  //propertyStatusType: string;

  title: string;

  address: string;

  // latitude: number;
  // longitude: number;
  // city: string;
  // district: string;
  // street: string;
  // placeId: string;

  area: number;
  numberOfRooms: number;

  elevator: boolean;
  balcony: boolean;

  description: string;
}
