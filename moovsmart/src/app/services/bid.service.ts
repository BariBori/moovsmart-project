import {environment} from "../../environments/environment";
import {Injectable} from "@angular/core";
import {BidFormDataModel} from "../models/bids/bidFormData.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BidListItemModel} from "../models/bids/bidListItem.model";

const BASE_URL = environment.BASE_URL + "/api/properties/property-details";

@Injectable({
  providedIn: 'root'
})
export class BidService{

  constructor(private httpClient: HttpClient) {
  }

  createBid(bidFormDataModel: BidFormDataModel, advertId: number): Observable<any> {
    return this.httpClient.post(BASE_URL + '/' + advertId, bidFormDataModel);
  }


  getBidList(advertId: number): Observable<Array<BidListItemModel>> {
    return this.httpClient.get<Array<BidListItemModel>>(BASE_URL + '/bids/' + advertId);
  }

  getLastBid(advertId: number): Observable<number> {
    return this.httpClient.get<number>(BASE_URL + "/lastBid/" + advertId);
  }

  getNumberOfBidUsers(advertId: number): Observable<number> {
    return this.httpClient.get<number>(BASE_URL + '/bidder/' + advertId);
  }

}

