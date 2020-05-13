import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class SharingSearchService {

  filteredProperties = new Subject<any>();

}
