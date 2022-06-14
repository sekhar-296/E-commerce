import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-chatlogin',
  templateUrl: './chatlogin.component.html',
  styleUrls: ['./chatlogin.component.css'],
})
export class ChatloginComponent implements OnInit {
  public username = '';
  public userId = '';
  constructor(private router: Router, private userService: UserService) {}

  ngOnInit() {}

  login() {
    this.userService
      .loginUser(this.userId, this.username)
      .subscribe((data: any) => {
        this.router.navigate(['/navbar/chat', data.userId]);
      });
  }
}
