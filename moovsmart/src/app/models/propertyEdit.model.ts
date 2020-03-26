export interface PropertyEditModel {
  id?: number;

  price: number;
  advertId: number;

  propertyType: string;
  propertyConditionType: string;
  parkingType: string;
  title: string;

  address: string;
  area: number;
  numberOfRooms: number;

  elevator: boolean;
  balcony: boolean;

  description: string;
}