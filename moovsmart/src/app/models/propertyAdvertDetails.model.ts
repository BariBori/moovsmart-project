
export interface PropertyAdvertDetailsModel {
  price: number,
  listOfImages: Array<string>,
  advertId: number,
  propertyType: string;
  propertyConditionType: string;
  parkingType: string;
  title: string;
  priceForSquareMeter: number;
  placeId: string;

  latitude: number;
  longitude: number;

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
