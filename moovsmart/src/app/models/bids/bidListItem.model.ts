import DateTimeFormat = Intl.DateTimeFormat;

export interface BidListItemModel {
  dateTimeOfBid: DateTimeFormat;
  userName: string;
  amountOfBid: number;
}
