import {AdvertStatusTypeOptionItemModel} from './advertStatusTypeOptionItem.model';
import DateTimeFormat = Intl.DateTimeFormat;

export interface PropertyListItemModel {
  id: number;
  listOfImages: Array<string>;
  title: string;
  advertId: number;
  numberOfRooms: number;
  area: number;
  price: number;
  actualPrice: number;
  priceForSquareMeter: number;
  address: string;
  advertStatus: AdvertStatusTypeOptionItemModel;
  createdAt: DateTimeFormat;
  timeOfActivation: DateTimeFormat;

  startOfAuction: DateTimeFormat;
  endOfAuction: DateTimeFormat;
  today: DateTimeFormat;
}
