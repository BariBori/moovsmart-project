import DateTimeFormat = Intl.DateTimeFormat;

export interface BidFormDataModel {
  amountOfBid: number;
  dateTimeOfBid: DateTimeFormat;
  advertId: number;
  userId: number;
  id?: number;
}
