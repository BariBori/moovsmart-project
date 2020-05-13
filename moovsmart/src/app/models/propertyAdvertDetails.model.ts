import {PropertyTypeOptionItemModel} from './propertyTypeOptionItem.model';
import {PropertyConditionTypeOptionItemModel} from './propertyConditionTypeOptionItem.model';
import {ParkingTypeOptionItemModel} from './parkingTypeOptionItem.model';
import {AdvertStatusTypeOptionItemModel} from './advertStatusTypeOptionItem.model';
import {UserFormDataModel} from './userFormData.model';
import DateTimeFormat = Intl.DateTimeFormat;


export interface PropertyAdvertDetailsModel {
  id: number;
  price: number;
  listOfImages: Array<string>;
  advertId: number;

  propertyType: PropertyTypeOptionItemModel;
  propertyConditionType: PropertyConditionTypeOptionItemModel;
  parkingType: ParkingTypeOptionItemModel;

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

  startOfAuction: Date;
  endOfAuction: DateTimeFormat;




  actualPrice: number;

  userName: string;
}
