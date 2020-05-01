import DateTimeFormat = Intl.DateTimeFormat;

export interface BidFormDataModel {
  amountOfBid: number;
  dateTimeOfBid: DateTimeFormat;
  id?: number;
}
