import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { from } from 'rxjs';
import { timestamp } from 'rxjs-compat/operator/timestamp';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  //url=environment.url
   url='http://localhost:8080'
  getUsers() {
    return this.http.get('http://localhost:9546/users');
    // return this.http.get(this.url+'/api/v2/users');
  }

  loginUser(userId: any, username: any) {
    return this.http.post('http://localhost:9546/messages', {
      userId: userId,
      username: username,
    });
  }
  getAllMessages(from:any,to:any){
    return this.http.get('http://localhost:9546/getusermessage/'+from+'/'+to);
  }
}
