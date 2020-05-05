import DateTimeFormat = Intl.DateTimeFormat;
import {JsonFormatter} from "tslint/lib/formatters";

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

  listOfImages: Array<string>;

  startOfAuction: DateTimeFormat;
  endOfAuction: DateTimeFormat;
  actualPrice: number;
}
