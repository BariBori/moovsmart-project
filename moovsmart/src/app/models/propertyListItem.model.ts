import {AdvertStatusTypeOptionItemModel} from "./advertStatusTypeOptionItem.model";
import DateTimeFormat = Intl.DateTimeFormat;

export interface PropertyListItemModel {
  id: number;
  listOfImages: Array<string>;
  title: string;
  advertId: number;
  numberOfRooms: number;
  area: number;
  price: number;
  priceForSquareMeter: number;
  address: string;
  advertStatus: AdvertStatusTypeOptionItemModel;
  createdAt: DateTimeFormat;
  timeOfActivation: DateTimeFormat;
}
