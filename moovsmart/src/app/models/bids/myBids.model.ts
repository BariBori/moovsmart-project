import DateTimeFormat = Intl.DateTimeFormat;

export interface MyBidsModel {
  dateTimeOfBid: DateTimeFormat;
  amountOfBid: number;
  title: string;
  advertId: number;
  startOfAuction: DateTimeFormat;
  endOfAuction: DateTimeFormat;
}
