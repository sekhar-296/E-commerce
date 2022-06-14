import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formData:any={}; 

  redirectPath(){
     this.route.navigateByUrl('/register');
  }
  
   passData(){
     console.log(this.formData);
     this.serve.postData(this.formData)
  } 
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
  private eventCallback = new Subject<string>(); // Source
  eventCallback$ = this.eventCallback.asObservable(); // Stream

    constructor(private serve:ServiceService,
      private _snackBar: MatSnackBar,
      private route:Router
      ) { }
  
    ngOnInit(): void {
      // localStorage.clear()
    }

}
