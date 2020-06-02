import {PropertyTypeOptionItemModel} from './propertyTypeOptionItem.model';
import {PropertyConditionTypeOptionItemModel} from './propertyConditionTypeOptionItem.model';
import {ParkingTypeOptionItemModel} from './parkingTypeOptionItem.model';
import {AdvertStatusTypeOptionItemModel} from './advertStatusTypeOptionItem.model';
import DateTimeFormat = Intl.DateTimeFormat;
import {AuctionStatusTypeModel} from "./auctionStatusType.model";


export interface PropertyAdvertDetailsModel {
  id: number;
  price: number;
  listOfImages: Array<string>;
  advertId: number;

  propertyType: PropertyTypeOptionItemModel;
  propertyConditionType: PropertyConditionTypeOptionItemModel;
  parkingType: ParkingTypeOptionItemModel;

  auctionStatus: AuctionStatusTypeModel;

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
  advertStatus: AdvertStatusTypeOptionItemModel;

  startOfAuction: DateTimeFormat;
  endOfAuction: DateTimeFormat;

  actualPrice: number;

  userName: string;
}
