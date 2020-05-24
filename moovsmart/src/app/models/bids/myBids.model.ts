import DateTimeFormat = Intl.DateTimeFormat;

export interface MyBidsModel {
  dateTimeOfBid: DateTimeFormat;
  amountOfBid: number;
  title: string;
  advertId: number;
  address: string;
  startOfAuction: DateTimeFormat;
  endOfAuction: DateTimeFormat;
  listOfImages: Array<string>;
  actualPrice: number;
  numberOfBids: number;
  propertyId: number;
  today: DateTimeFormat;
}
