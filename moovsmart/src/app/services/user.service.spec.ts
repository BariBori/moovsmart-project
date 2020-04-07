import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { UserService } from './user.service';
import { environment } from 'src/environments/environment';
import { User } from '../models/error/User';

describe('UserService', () => {
    let service: UserService;
    let controller: HttpTestingController;
    const BASE_URL = environment.BASE_URL;
    const AUTH_URL = BASE_URL + '/api/users/authenticate';
    const LOGOUT_URL = BASE_URL + '/api/users/logout';
    const REG_URL = BASE_URL + '/api/users/register';
    const ME_URL = BASE_URL + '/api/users/me';


    beforeEach(() => {
        TestBed.configureTestingModule({ imports: [HttpClientTestingModule] });
        controller = TestBed.inject(HttpTestingController);
        service = TestBed.inject(UserService);
    });

    it('should emit login event on creation', () => {
        service.loggedIn.subscribe(login => expect(login).toBe(
            service.isLoggedIn()
                ? true : false
        ));
    });

    it('should log users in', () => {
        service.getCurrentUser.subscribe(user => expect(user.userName).toBe('user'));
        service.authenticate({ email: 'user', password: '1234' })
            .subscribe();
        controller.expectOne(AUTH_URL)
            .flush({ id: 1, email: 'user', userName: 'user' });
        service.loggedIn.subscribe(loggedin => expect(loggedin).toBe(true));
    });

    it('should log users out', () => {
        service.logOut.subscribe();
        controller.expectOne(LOGOUT_URL)
            .flush('');
        service.loggedIn.subscribe(login => expect(login).toBe(false));
    });

    afterAll(() => {
        if (service.isLoggedIn()) {
            controller.expectOne(ME_URL)
                .flush({ id: 1, email: 'user', userName: 'user' });
        }
        TestBed.resetTestingModule();
        controller.verify();
    });
});
