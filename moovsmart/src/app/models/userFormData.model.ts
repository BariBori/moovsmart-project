import {PersonalDetailsFormDataModel} from "./personalDetailsFormData.model";

export interface UserFormDataModel {
  email: string,
  password: string,
  personalDetails: PersonalDetailsFormDataModel;
}
