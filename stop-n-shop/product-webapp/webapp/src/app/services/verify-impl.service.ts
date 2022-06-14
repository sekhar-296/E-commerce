import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VerifyImplService {
   otp=new BehaviorSubject(0);
   otp2:any;
   email=new BehaviorSubject('');
   //url=environment.url
    url='http://localhost:8'
  
  verify(formData:any){
    this.http.post<any>(this.url+'/api/v4/email', formData)
    .subscribe(
     (res) => {
       console.log(formData);
   
       this.openSnackBar("OTP sent successfully","close")
     },
     (error) => {
   
      this.openSnackBar("Error occured","close")
     }
   
   );
  }
  
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }


  constructor(private http:HttpClient,
    private route:Router,
    private _snackBar: MatSnackBar) { }
}
