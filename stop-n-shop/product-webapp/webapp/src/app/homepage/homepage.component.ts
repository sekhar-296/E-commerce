import { Component, OnInit, HostListener } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { MenuItem } from './menu-items';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  email: any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: ServiceService
  ) {}
  public getScreenWidth: any;
  public getScreenHeight: any;

  ngOnInit(): void {
    this.route.queryParams.subscribe((p) => {
      this.service.fetchToken(p['code'], p['state']).subscribe((a) => {
        this.email = a;
        localStorage.setItem('token', this.email.map.accessToken);
        localStorage.setItem('userDetail', this.email.map.email);
        localStorage.setItem('userRole', 'buyer');
        this.router.navigateByUrl('/navbar/productlist');
      });
    });
  }

  @HostListener('window:resize', ['$event']) onWindowResize() {
    this.getScreenWidth = window.innerWidth;
    this.getScreenHeight = window.innerHeight;
  }
  menuItems: MenuItem[] = [
    {
      label: 'Register',
      icon: 'how_to_reg',
      link: '/verify',
      showOnMobile: false,
      showOnTablet: true,
      showOnDesktop: true,
    },
    {
      label: 'Login',
      icon: 'login',
      link: '/login',
      showOnMobile: false,
      showOnTablet: true,
      showOnDesktop: true,
    },
  ];
  customOptions: any = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: true,
    navSpeed: 500,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1,
      },
      400: {
        items: 2,
      },
      740: {
        items: 3,
      },
      940: {
        items: 1,
      },
    },
    nav: true,
  };
}
