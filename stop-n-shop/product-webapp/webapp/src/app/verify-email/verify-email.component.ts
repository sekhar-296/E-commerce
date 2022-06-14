import { Component, Input, OnInit, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { VerifyImplService } from '../services/verify-impl.service';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit {

  formData:any={}; 
  show:boolean=false;
  otp:any;
 
  email:any;
  
  toggle(){
    this.show=!this.show;
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
  
   passData(){
    localStorage.setItem('verifyEmail',this.formData.toEmail);
    console.log(localStorage.getItem('verifyEmail'));
    this.toggle();
     this.otp= Math.floor(100000 + Math.random() * 900000);
     this.formData.body=this.otp;
     this.formData.subject="OTP-STOP-N-SHOP ";
     this.serve.otp.next(this.otp);
     this.serve.verify(this.formData);
    
  } 
  
  redirectPath1(){
  console.log(this.otp);
  console.log(this.formData.otp);
  if(this.otp==this.formData.otp){
    console.log("hi")
    this.route.navigateByUrl('/register');
  }
  else{
    this.openSnackBar("Please  enter correct otp","close") 
  }
  }
  

   
  
  
    constructor(private serve:VerifyImplService,
      private route:Router,
      private serve1:ServiceService,
      private _snackBar: MatSnackBar
      ) { }
  
    ngOnInit(): void {
    }
  }


