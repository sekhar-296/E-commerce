import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  formData: any = {};

  // findingRole=this.serve.getCurrentData(this.Email).subscribe((res)=>{
  //   this.role=res.role;
  // })

  redirectPath() {
    this.route.navigateByUrl('/register');
  }

  passData() {
    localStorage.setItem('userDetail', this.formData.email);
    localStorage.setItem('userCity', this.formData.city);
    localStorage.setItem('username', this.formData.name);
    console.log(this.formData);
    this.serve.authenticate(this.formData);
  }

  constructor(
    private serve: ServiceService,
    private route: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    // localStorage.clear()
  }
  loginwithgoogle() {
    this.serve.loginWithGoogle();
  }
}
