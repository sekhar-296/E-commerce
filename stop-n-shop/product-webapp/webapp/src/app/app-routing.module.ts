import { NgModule } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { ProductlistComponent } from './productlist/productlist.component';
import { RegisterComponent } from './register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { RecommandationComponent } from './recommandation/recommandation.component';
import { OrderhistoryComponent } from './orderhistory/orderhistory.component';
import { ShopsListComponent } from './shops-list/shops-list.component';
import { AddProductsComponent } from './add-products/add-products.component';
import { MycartComponent } from './mycart/mycart.component';
import { ModalFormComponent } from './modal-form/modal-form.component';
import { DetailFormComponent } from './detail-form/detail-form.component';
import { OrderSummaryComponent } from './order-summary/order-summary.component';
import { HomepageComponent } from './homepage/homepage.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';

import { LocationAndDirectionsComponent } from './location-and-directions/location-and-directions.component';
import { AuthGuardGuard } from './auth-guard.guard';
import { FullscreenOverlayContainer } from '@angular/cdk/overlay';
import { DealOfTheDayComponent } from './deal-of-the-day/deal-of-the-day.component';
import { RegisterShopComponent } from './register-shop/register-shop.component';
import { ChatComponent } from './chat/chat.component';
import { ChatloginComponent } from './chatlogin/chatlogin.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ShortestRouteComponent } from './shortest-route/shortest-route.component';

const routes: Routes=[
  {
    path: 'home',
    component: HomepageComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'verify',
    component: VerifyEmailComponent
  },
  {
    path: 'navbar',
    canActivate: [AuthGuardGuard],
    component: NavBarComponent,
    children: [
      {
        path: 'productlist',
        component: ProductlistComponent
      },
      {
        path: 'productDetails',
        component: ProductDetailsComponent
      },
      {
        path: 'recommendation',
        component: RecommandationComponent
      },
      {
        path: 'orderhistory',
        component: OrderhistoryComponent
      },
      {
        path: 'shopslist',
        component: ShopsListComponent
      },
      {
        path: 'addproduct',
        component: AddProductsComponent
      },
      {
        path: 'mycart',
        component: MycartComponent
      },
      {
        path: 'ordersummary',
        component: OrderSummaryComponent
      },
      {
        path: 'profile',
        component: DetailFormComponent
      },
      {
        path: 'location',
        component: LocationAndDirectionsComponent
      },
      {
        path: 'dealsoftheday',
        component: DealOfTheDayComponent
      },
      {
        path: 'shopregister',
        component: RegisterShopComponent
      },
      {
        path: 'message',
        component: ChatloginComponent
      },
      {
        path: 'chat/:userId',
        component: ChatComponent
      },
      {
        path: 'routes',
        component: ShortestRouteComponent
      }
    ]
  },
    {
      path: '',
      component: HomepageComponent,
      pathMatch: 'full',
    }
]
// const routes: Routes = [
//   {
//     path: 'home',
//     component: HomepageComponent,
//   },
//   {
//     path: 'navbar',
//     component: NavBarComponent,
//   },
//   {
//     path: 'register',
//     // canActivate:[AuthGuardGuard],
//     // redirectTo:"/login",
//     // pathMatch:"full",
//     // canActivate:[AuthGuardGuard],
//     component: RegisterComponent,
//   },
//   {
//     path: 'productlist',
//     component: NavBarComponent,
//     canActivate: [AuthGuardGuard],
//     children: [
//       {
//         path: '',
//         component: ProductlistComponent,
//       },
//     ],
//   },
//   {
//     path: 'productDetails',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: ProductDetailsComponent,
//       },
//     ],
//   },
//   {
//     path: 'recommendation',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: RecommandationComponent,
//       },
//     ],
//   },
//   {
//     path: 'orderhistory',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: OrderhistoryComponent,
//       },
//     ],
//   },
//   {
//     path: 'shopslist',
//     // canActivate:[AuthGuardGuard],
//     // component:NavBarComponent,

//     // children:[
//     //   {
//     //     path:'',
//     component: ShopsListComponent,
//     //   }
//     // ]
//   },

//   {
//     path: 'addproduct',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: AddProductsComponent,
//       },
//     ],
//   },

//   {
//     path: 'mycart',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: MycartComponent,
//       },
//     ],
//   },
//   {
//     path: 'ordersummary',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: OrderSummaryComponent,
//       },
//     ],
//   },
//   {
//     path: 'profile',
//     // canActivate:[AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: DetailFormComponent,
//       },
//     ],
//   },
//   {
//     path: 'verify',
//     component: VerifyEmailComponent,
//   },
//   {
//     path: 'location',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: LocationAndDirectionsComponent,
//       },
//     ],
//   },
//   {
//     path: 'dealsoftheday',
//     canActivate: [AuthGuardGuard],
//     component: NavBarComponent,

//     children: [
//       {
//         path: '',
//         component: DealOfTheDayComponent,
//       },
//     ],
//   },
//   {
//     path: 'login',
//     component: LoginComponent,
//   },
//   {
//     path: 'shopregister',
//     component: RegisterShopComponent,
//   },
//   // {
//   //   path: 'message',
//   //   component: ChatloginComponent,
//   // },
//   {
//     path: 'chat/:userId',
//     component: ChatComponent,
//   },
//   {
//     path: 'shopregister',
//     canActivate: [AuthGuardGuard],
//     component: RegisterShopComponent,
//   },
//   {
//     path: 'routes',
//     component: ShortestRouteComponent,
//   },
//   {
//     path: '',
//     component: HomepageComponent,
//     pathMatch: 'full',
//   },
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
