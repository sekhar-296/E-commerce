import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';
import { CookieService } from 'ngx-cookie-service';

import { environment } from 'src/environments/environment';
import { ThisReceiver } from '@angular/compiler';

@Injectable({
  providedIn: 'root',
})
export class ServiceService {
  private baseUrl = environment.baseUrl;
  private authorizeEndpoint = '/oauth2/authorization/google';
  private tokenEndpoint = '/login/oauth2/code/google';

  email: any;

  //url=environment.url
   url='http://localhost:8080'
  constructor(
    private http: HttpClient,
    private route: Router,
    private cookie: CookieService,
    private _snackBar: MatSnackBar
  ) {}

  array: any;
  profileResponse: any;

  authenticate(formData: any) {
    let role = null;
    this.http.post<any>(this.url+'/api/v1/login', formData).subscribe(
      (res) => {
        this.openSnackBar('Successfully Logged in', 'close');
        this.cookie.set('jwt', res.token);
        console.log(res.token);
        this.getCurrentData(formData.email).subscribe((res) => {
          if (formData.password === res.password) {
            console.log(res.role);
            const role1 = res.role;
            const string1 = 'buyer';
            const string2 = 'seller';
            if (role1.toUpperCase() === string1.toUpperCase()) {
              this.route.navigateByUrl('/navbar/productlist');
              localStorage.setItem('userRole', JSON.stringify(string1));
            } else if (role1.toUpperCase() === string2.toUpperCase()) {
              localStorage.setItem('userRole', JSON.stringify(string2));

              this.http
                .get(this.url+
                  '/api/v11/shopbyemail/' +
                    localStorage.getItem('userDetail')
                )
                .subscribe(
                  (a) => {
                    this.profileResponse = a;
                    this.route.navigateByUrl('/navbar/addproduct');
                  },
                  (error) => {
                    this.route.navigateByUrl('/navbar/shopregister');
                  }
                );
              localStorage.setItem('userRole', JSON.stringify(string2));
            }
          }
        });
      },
      (error) => {
        this.openSnackBar('Register If you are a new user', 'close');
      }
    );
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  postData(formData: any) {
    formData.email = localStorage.getItem('verifyEmail');
    this.http
      .post<any>(this.url+'/api/v1/register', formData, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .subscribe(
        (res) => {
          this.openSnackBar('Registration succesfull', 'close');
          this.route.navigateByUrl('/login');
        },
        (error) => {
          this.openSnackBar('user already exists', 'close');
        }
      );
  }

  private eventCallback = new Subject<string>(); // Source
  eventCallback$ = this.eventCallback.asObservable(); // Stream

  getRecommandation() {
    return this.http.get(this.url+
      '/api/v9/category/' +
        localStorage.getItem('productCategory')
    );
  }

  updateCard(formData: any) {
    this.http.patch<any>(this.url+'/api/v1/user1', formData).subscribe(
      (data) => {
        this.openSnackBar('updated successfully', 'close');
      },
      (error) => {
        this.openSnackBar('Error occured please try again', 'close');
      }
    );
  }
  getCurrentData(email: any) {
    // console.log(email);
    return this.http.get<any>(this.url+'/api/v1/user/' + email);
  }

  loginWithGoogle() {
    window.open(this.baseUrl + this.authorizeEndpoint, '_self');
  }

  fetchToken(code: any, state: any) {
    console.log(
      this.baseUrl + this.tokenEndpoint + '?code=' + code + '&state=' + state
    );

    return this.http.get(
      this.baseUrl + this.tokenEndpoint + '?code=' + code + '&state=' + state
    );
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    console.log('this is checking login token' + token);
    if (token == null || token == '') {
      console.log('user is not login');
      return false;
    } else {
      console.log('user is login');
      return true;
    }
  }
//////////////////////////////
  fetchdata(formData: any) {
    return this.http.post('http://localhost:8085/qrcode', formData);
  }

  getCartDetails() {
    return this.http.get(
      this.url+'/api/v5/products/' + localStorage.getItem('userDetail')
    );
  }
  saveToCart(productData: any) {
    return this.http.post(this.url+'/api/v5/product', productData);
  }

  deleteItems(productId: string) {
    return this.http.delete(this.url+
      '/api/v5/delete/' +
        productId +
        '/' +
        localStorage.getItem('userDetail')
    );
  }

  getRecommandationByCity() {
    return this.http.get(this.url+
      '/api/v9/city/' + localStorage.getItem('userCity')
    );
  }

  getRecommandationByBoth() {
    return this.http.get(this.url+
      '/api/v9/product/' +
        localStorage.getItem('productCategory') +
        '/' +
        localStorage.getItem('userCity')
    );
  }

  deleteAllProducts(email: any) {
    return this.http.delete(this.url+'/api/v5/deleteAll/' + email);
  }

  getDetail() {
    return this.http.get(this.url+
      '/api/v8/getProduct/' + localStorage.getItem('productId')
    );
  }

  fetchUserAddress() {
    return this.http.get(this.url+
      '/api/v1/user/' + localStorage.getItem('userDetail')
    );
  }

  fetchAuthenticatedEmailId(emailId: any) {
    return this.http.get(this.url+'/api/v1/user/' + emailId);
  }
}
