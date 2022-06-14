import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { ServiceService } from '../service.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  show: boolean = false;
  name = localStorage.getItem('userDetail');

  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private route: Router,
    private location: Location,
    private userService: UserService,
    private service: ServiceService
  ) {}

  ngOnInit(): void {
    console.log(this.name);

    const role = localStorage.getItem('userRole');
    const defaultRole = '"' + 'seller' + '"';
    if (role == defaultRole) {
      this.show = !this.show;
    }
  }
  logout() {
    localStorage.clear();
    this.route.navigateByUrl('/home');
  }
  back() {
    this.location.back();
  }

  userRes!: any;
  username: any;
  login() {
    this.service.fetchUserAddress().subscribe((res) => {
      this.userRes = res;
      this.username = this.userRes.userName;
      this.userService
        .loginUser(localStorage.getItem('userDetail'), this.username)
        .subscribe((data: any) => {
          this.route.navigate(['/navbar/chat', data.userId]);
        });
      console.log(this.username);
    });
  }
}
