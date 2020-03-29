import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {PropertyListItemModel} from "../models/propertyListItem.model";
import {PropertyFormDataModel} from "../models/propertyFormData.model";
import {FormInitDataModel} from "../models/formInitDataModel";
import {PropertyAdvertDetailsModel} from "../models/propertyAdvertDetails.model";
import {environment} from "../../environments/environment";
import {PropertyCityModel} from "../models/propertyCity.model";
import {PropertyEditModel} from "../models/propertyEdit.model";

const BASE_URL = environment.BASE_URL + "/api/properties";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  setGroupFilter$ = new Subject<any>();
  getGroupFilter = this.setGroupFilter$.asObservable();

  constructor(private httpClient: HttpClient) {
  }


  createProperty(propertyFormDataModel: PropertyFormDataModel): Observable<any> {
    return this.httpClient.post(BASE_URL, propertyFormDataModel);
  }

  getPropertyList(): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(BASE_URL);
  }

  getCityList(): Observable<Array<PropertyCityModel>>{
    return this.httpClient.get<Array<PropertyCityModel>>(BASE_URL+"/cities")
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

  updateProperty(data: PropertyEditModel, propertyId: number): Observable<any>{
    data.id = propertyId;
    return this.httpClient.put(BASE_URL +'/' + propertyId, data);
  }

  getSearchResult(search: string): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(BASE_URL + "/propertySearch/?search=" + search);
  }

}
