import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PropertyListItemModel} from "../models/propertyListItem.model";
import {PropertyFormDataModel} from "../models/propertyFormData.model";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  baseUrl = "http://localhost:8080/api/properties";

  constructor(private httpClient: HttpClient) {
  }

  createProperty(roomFormData: PropertyFormDataModel): Observable<any> {
    return this.httpClient.post(this.baseUrl, roomFormData);
  }

  getPropertyList(): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(this.baseUrl);
  }

}
