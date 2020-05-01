import {environment} from "../../environments/environment";
import {Injectable} from "@angular/core";
import {BidFormDataModel} from "../models/bids/bidFormData.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BidListItemModel} from "../models/bids/bidListItem.model";
import {FormGroup} from "@angular/forms";

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


  getBidList(): Observable<Array<BidListItemModel>> {
    return this.httpClient.get<Array<BidListItemModel>>(BASE_URL);
  }

  getMyBidList(userName: String): Observable<Array<BidListItemModel>> {
    return this.httpClient.get<Array<BidListItemModel>>(BASE_URL + "myBids" + userName);
  }

}

