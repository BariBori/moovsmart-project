import DateTimeFormat = Intl.DateTimeFormat;

export interface BidListItemModel {
  dateTimeOfBid: DateTimeFormat;
  user: string;
  amountOfBid: number;
}
