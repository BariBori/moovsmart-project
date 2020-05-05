import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable, Subject, ReplaySubject } from "rxjs";
import { PropertyListItemModel } from "../models/propertyListItem.model";
import { PropertyFormDataModel } from "../models/propertyFormData.model";
import { FormInitDataModel } from "../models/formInitDataModel";
import { PropertyAdvertDetailsModel } from "../models/propertyAdvertDetails.model";
import { environment } from "../../environments/environment";
import { PropertyCityModel } from "../models/propertyCity.model";
import { PropertyEditModel } from "../models/propertyEdit.model";
import { FilterPropertyAdvertModel } from "../models/filterPropertyAdvert.model";
import { UserService } from './user.service';
import { tap, filter, flatMap } from 'rxjs/operators';
import {PageModel} from "../models/page.model";

const BASE_URL = environment.BASE_URL + "/api/properties";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  setGroupFilter$ = new Subject<any>();
  getGroupFilter = this.setGroupFilter$.asObservable();
  savedAdverts = new ReplaySubject<PropertyListItemModel[]>(1);

  constructor(private httpClient: HttpClient, private userService: UserService) {
    userService.loggedIn.pipe(
      filter(loggedin => loggedin === true),
      flatMap(login =>
        this.httpClient.get<PropertyListItemModel[]>(BASE_URL + "/fav")),
      tap(faves => this.savedAdverts.next(faves))
    )
      .subscribe(console.log);
  }

  createProperty(propertyFormDataModel: PropertyFormDataModel): Observable<any> {

    return this.httpClient.post(BASE_URL, propertyFormDataModel);
  }

  getPropertyList(): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(BASE_URL);
  }

  getPropertiesByPage(data: PageModel): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.post<Array<PropertyListItemModel>>(BASE_URL + "/page", data);
  }

  //user's property list in profil
  getMyProperties(userName: String): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.get<Array<PropertyListItemModel>>(BASE_URL + "/myProperties/" + userName);
  }

  getCityList(): Observable<Array<PropertyCityModel>> {
    return this.httpClient.get<Array<PropertyCityModel>>(BASE_URL + "/cities")
  }

  fetchFormInitData(): Observable<FormInitDataModel> {
    return this.httpClient.get<FormInitDataModel>(`${BASE_URL}/formData`);
  }

  archivePropertyAdvert(id: number): Observable<Array<PropertyListItemModel>> {
    return this.httpClient.delete<Array<PropertyListItemModel>>(BASE_URL + '/' + id);
  }

  fetchAdvertDetails(id: string): Observable<PropertyAdvertDetailsModel> {
    return this.httpClient.get<PropertyAdvertDetailsModel>(`${BASE_URL}/${id}`);
  }

  updateProperty(data: PropertyEditModel, propertyId: number): Observable<any> {
    data.id = propertyId;
    return this.httpClient.put(BASE_URL + '/' + propertyId, data);
  }

  postFilteredPropertyAdverts(filterPropertyAdvertModel: FilterPropertyAdvertModel): Observable<any> {
    return this.httpClient.post(BASE_URL + "/search", filterPropertyAdvertModel)
  }

  saveFavouriteAdvert(advertId: number): Observable<PropertyListItemModel[]> {
    return this.httpClient.post<PropertyListItemModel[]>(BASE_URL + "/fav/" + advertId, "").pipe(
      tap(list => this.savedAdverts.next(list))
    );
  }

  removeFavouriteAdvert(propertyAdvertID: number) {
    return this.httpClient.delete<PropertyListItemModel[]>(BASE_URL + "/fav/" + propertyAdvertID).pipe(
      tap(list => this.savedAdverts.next(list))
    );
  }
}
