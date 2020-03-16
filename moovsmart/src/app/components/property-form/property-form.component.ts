import {Component, ElementRef, Input, NgZone, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import { PropertyService } from '../../services/property.service';
import { Router } from '@angular/router';
import { validationHandler } from '../../utils/validationHandler';
import {PropertyTypeOptionItemModel} from "../../models/propertyTypeOptionItem.model";
import {PropertyConditionTypeOptionItemModel} from "../../models/propertyConditionTypeOptionItem.model";
import {ParkingTypeOptionItemModel} from "../../models/parkingTypeOptionItem.model";
import {FormInitDataModel} from "../../models/formInitDataModel";
import {MapsAPILoader} from "@agm/core";
import {HttpClient} from "@angular/common/http";
import {FileUploader, FileUploaderOptions, ParsedResponseHeaders} from "ng2-file-upload";
import {Cloudinary} from "@cloudinary/angular-5.x";


@Component({
  selector: 'app-property-form',
  templateUrl: './property-form.component.html',
  styleUrls: ['./property-form.component.css']
})
export class PropertyFormComponent implements OnInit {
  //------Cloudinary file upload------------
  @Input()
  responses: Array<any>;
  listOfImages: Array<string> = [];
  private hasBaseDropZoneOver: boolean = false;
  uploader: FileUploader;
  private imgTitle: string;
  //---------------------------------------


  //------Google Maps------------
  private geoCoder;
    zoom: number;
    address: string;
    city: string;
    district: string;
    street: string;
    postalCode: string;
    placeId: string;
    latitude: number;
    longitude: number;
    addressComponent: any;

    @ViewChild('search')
    public searchElementRef: ElementRef;

    //---------------------------------

  propertyType: Array<PropertyTypeOptionItemModel>;
  propertyConditionType: Array<PropertyConditionTypeOptionItemModel>;
  parkingType: Array<ParkingTypeOptionItemModel>;

    propertyForm = this.formBuilder.group({
      advertStatus: ['FORAPPROVAL'],

      address: [''],
      city: [''],
      street: [''],
      district: [''],
      postalCode: [''],

      title: ['',Validators.required],
      price: [0 ,Validators.required],

      area: [0,Validators.required],
      numberOfRooms: [0,Validators.required],

      propertyType: ['',Validators.required],
      propertyConditionType: ['',Validators.required],
      parkingType: ['',Validators.required],

      elevator: [false],
      balcony: [false],

      description: ['',Validators.required],

      listOfImages: [null],
  });


  constructor(
    private formBuilder: FormBuilder,
    private propertyService: PropertyService,
    private router: Router,
    private httpClient: HttpClient,
    //-----Google Maps----
    private mapsAPILoader: MapsAPILoader,
    private zone: NgZone,
    //----Cloudinary-----
    private cloudinary: Cloudinary,
  ) {this.responses = [];
    this.imgTitle = ''; }

  ngOnInit() {
    this.propertyService.fetchFormInitData().subscribe(
      (initData: FormInitDataModel) =>{
        this.propertyType = initData.propertyType;
        this.propertyConditionType = initData.propertyConditionType;
        this.parkingType = initData.parkingType;
      },
      error => console.warn(error)
    );



     //----------CLOUDINARY----------------------
    // Create the file uploader, wire it to upload to your account
    const config = this.cloudinary.config();
    const cloud_name = "dqmt1lieq";
    const uploaderOptions: FileUploaderOptions = {
      url: "https://api.cloudinary.com/v1_1/"+  this.cloudinary.config().cloud_name+"/image/upload",

      // Upload files automatically upon addition to upload queue
      autoUpload: true,
      // Use xhrTransport in favor of iframeTransport
      isHTML5: true,
      // Calculate progress independently for each uploaded file
      removeAfterUpload: true,
      // XHR request headers
      headers: [
        {
          name: 'X-Requested-With',
          value: 'XMLHttpRequest'
        }
      ]
    };

    this.uploader = new FileUploader(uploaderOptions);

    this.uploader.onBuildItemForm = (fileItem: any, form: FormData): any => {
      // Add Cloudinary unsigned upload preset to the upload form
      const upload_preset = "s1jujbuu";
      form.append('upload_preset', this.cloudinary.config().upload_preset);

      let tags = 'mypropertyalbum';
      if(this.imgTitle){
        form.append('context', `photo=${this.imgTitle}`);
        tags = `myphotoalbum,${this.imgTitle}`;
      }
      // Add  folder name
      form.append('folder', 'property_album');

      // Add custom tags
      form.append('tags', tags);


      // Add file to upload
      form.append('file', fileItem);

      // Use default "withCredentials" value for CORS requests
      fileItem.withCredentials = false;
      return { fileItem, form };
    };

    // Insert or update an entry in the responses array
    const upsertResponse = fileItem => {

      // Run the update in a custom zone since for some reason change detection isn't performed
      // as part of the XHR request to upload the files.
      // Running in a custom zone forces change detection
      this.zone.run(() => {
        // Update an existing entry if it's upload hasn't completed yet

        // Find the id of an existing item

        const existingId = this.responses.reduce((prev, current, index) => {
          if (current.file.name === fileItem.file.name && !current.status) {
            return index;
          }
          return prev;
        }, -1);
        if (existingId > -1) {
          // Update existing item with new data
          this.responses[existingId] = Object.assign(this.responses[existingId], fileItem);
        } else {
          // Create new response

          this.responses.push(fileItem);
        }
        console.log(this.responses);

        //fill listOfImages array
        this.listOfImages.push(fileItem.data.url);
        this.listOfImages= this.listOfImages.filter(function (el) {
          return el != null;
        });
        console.log(this.listOfImages);
      });

    };

    // Update model on completion of uploading a file
    this.uploader.onCompleteItem = (item: any, response: string, status: number, headers: ParsedResponseHeaders) =>
      upsertResponse(
        {
          file: item.file,
          status,
          data: JSON.parse(response)
        }
      );

    // Update model on upload progress event
    this.uploader.onProgressItem = (fileItem: any, progress: any) =>
      upsertResponse(
        {
          file: fileItem.file,
          progress,
          data: {}
        }
      );

    //---------CLOUDINARY END--------------------


    //---------GOOGLE MAPS----------------------
    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {

      this.geoCoder = new google.maps.Geocoder;

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.zone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          this.address = place.formatted_address;
          this.placeId = place.place_id;
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.addressComponent = place.address_components;
          console.log(this.addressComponent.length);
          console.log(this.addressComponent);


          for(let i = 0; i < this.addressComponent.length; i++){
            switch (this.addressComponent[i].types[0]) {
              case "route": {
                this.street = place.address_components[i].long_name;
                break;
              }
              case "sublocality_level_1":{
                this.district = place.address_components[i].long_name;
                break;
              }
              case "locality": {
                this.city = place.address_components[i].long_name;
                break;
              }
              case "postal_code": {
                this.postalCode = place.address_components[i].long_name;
                break;
              }
            }
          }

          this.zoom = 12;

        });
      });
    });
  } //NGONINIT END

  //-----------GOOGLE MAPS------------
  clearAddressDetails() {
    this.street='';
    this.postalCode='';
    this.address='';
    this.city='';
    this.district='';
  }

  //---------GOOGLE MAPS END-----------

  //----------CLOUDINARY----------------

  updateTitle(value: string) {
    this.imgTitle = value;
  }

  // Delete an uploaded image
  // Requires setting "Return delete token" to "Yes" in your upload preset configuration
  // See also https://support.cloudinary.com/hc/en-us/articles/202521132-How-to-delete-an-image-from-the-client-side-
  deleteImage = function (data: any, index: number) {
    const url = `https://api.cloudinary.com/v1_1/${this.cloudinary.config().cloud_name}/delete_by_token`;
    const headers = new Headers({ 'Content-Type': 'application/json', 'X-Requested-With': 'XMLHttpRequest' });
    const options = { headers: headers };
    const body = {
      token: data.delete_token
    };
    console.log(data);
    console.log(data.delete_token);
    this.http.post(url, body, options).subscribe(response => {
      console.log(`Deleted image - ${data.public_id} ${response.result}`);
      // Remove deleted item for responses
      this.responses.splice(index, 1);
    });
  };

  fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  getFileProperties(fileProperties: any) {
    // Transforms Javascript Object to an iterable to be used by *ngFor
    if (!fileProperties) {
      return null;
    }
    let fileObject = Object.keys(fileProperties)
      .map((key) => ({ 'key': key, 'value': fileProperties[key] }));
    return fileObject;

  }


  deleteImgFromArray(array: any, i: number) {
    delete array[i];
  }


  //--------CLOUDINARY END-------------


  submit = () => {
    let propertyFormDataModel = this.propertyForm.value;
    propertyFormDataModel.address = this.address;
    propertyFormDataModel.city = this.city;
    propertyFormDataModel.district = this.district;
    propertyFormDataModel.street = this.street;
    propertyFormDataModel.postalCode = this.postalCode;
    propertyFormDataModel.placeId = this.placeId;
    propertyFormDataModel.latitude = this.latitude;
    propertyFormDataModel.longitude = this.longitude;
    propertyFormDataModel.listOfImages = this.listOfImages;

    this.propertyService.createProperty(propertyFormDataModel).subscribe(
      () => this.router.navigate(['property-list']),
      error => validationHandler(error, this.propertyForm)
    )
  };


}
