import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHelperService} from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {
  
      helper=new JwtHelperService();
  userName!: string;
  cookieValue_jwt!: string;
  constructor(public router: Router, private cookieService: CookieService) { }
  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    const check: Boolean = false;
    this.cookieValue_jwt = this.cookieService.get('jwt');
    if (this.cookieValue_jwt || localStorage.getItem("token")) {
      return true;
    } else {
      this.router.navigateByUrl("/login");
      return false;
    }
  }
  }
  

