import {environment} from "../../environments/environment";
import {Injectable} from "@angular/core";
import {BidFormDataModel} from "../models/bids/bidFormData.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BidListItemModel} from "../models/bids/bidListItem.model";

const BASE_URL = environment.BASE_URL + "/api/bids";

@Injectable({
  providedIn: 'root'
})
export class BidService{

  constructor(private httpClient: HttpClient) {
  }

  createBid(bidFormDataModel: BidFormDataModel): Observable<any> {
    return this.httpClient.post(BASE_URL, bidFormDataModel);
  }


  getBidList(): Observable<Array<BidListItemModel>> {
    return this.httpClient.get<Array<BidListItemModel>>(BASE_URL);
  }

  getMyBidList(userName: String): Observable<Array<BidListItemModel>> {
    return this.httpClient.get<Array<BidListItemModel>>(BASE_URL + "myBids" + userName);
  }

}

