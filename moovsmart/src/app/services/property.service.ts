import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {PropertyListItemModel} from "../models/propertyListItem.model";
import {PropertyFormDataModel} from "../models/propertyFormData.model";
import {FormInitDataModel} from "../models/formInitDataModel";
import {PropertyAdvertDetailsModel} from "../models/propertyAdvertDetails.model";

const BASE_URL = "http://localhost:8080/api/properties";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  constructor(private httpClient: HttpClient) {
  }


  createProperty(propertyFormDataModel: PropertyFormDataModel): Observable<any> {
    return this.httpClient.post(BASE_URL, propertyFormDataModel);
  }

  getPropertyList(): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(BASE_URL);
  }

  fetchFormInitData(): Observable<FormInitDataModel>{
    return  this.httpClient.get<FormInitDataModel>(`${BASE_URL}/formData`)
  }

  archivePropertyAdvert(id: number): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.delete<Array<PropertyListItemModel>>(BASE_URL + '/' + id);
  }

  fetchAdvertDetails(id: string): Observable<PropertyAdvertDetailsModel> {
    return this.httpClient.get<PropertyAdvertDetailsModel>(`${BASE_URL}/${id}`);
  }

  updateProperty(data: PropertyFormDataModel, propertyId: number): Observable<any>{
    data.id = propertyId
    return this.httpClient.put(BASE_URL +'/' + propertyId, data);
  }

}
