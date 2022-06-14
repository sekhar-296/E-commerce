import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductlistComponent } from './productlist/productlist.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShopsListComponent } from './shops-list/shops-list.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { OrderhistoryComponent } from './orderhistory/orderhistory.component';
import { AddProductsComponent } from './add-products/add-products.component';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule, MatRippleModule } from '@angular/material/core';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { RecommandationComponent } from './recommandation/recommandation.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { ModalFormComponent } from './modal-form/modal-form.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { MatTabsModule } from '@angular/material/tabs';
import { OAuthModule } from 'angular-oauth2-oidc';
import { MycartComponent } from './mycart/mycart.component';
import { OrderSummaryComponent } from './order-summary/order-summary.component';
import { HomepageComponent } from './homepage/homepage.component';
import { DetailFormComponent } from './detail-form/detail-form.component';
import { LocationAndDirectionsComponent } from './location-and-directions/location-and-directions.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
// import { CountdownModule } from 'ngx-countdown';
import { MatTooltipModule } from '@angular/material/tooltip';
import { AgmCoreModule } from '@agm/core';
// import { AuthHeaderInterceptor } from './services/auth-header.interceptor';
import { MatChipsModule } from '@angular/material/chips';
import { AvatarModule } from 'ngx-avatar';
import { DealOfTheDayComponent } from './deal-of-the-day/deal-of-the-day.component';
import { RegisterShopComponent } from './register-shop/register-shop.component';
import { ChatComponent } from './chat/chat.component';
import { ChatloginComponent } from './chatlogin/chatlogin.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LocationModelComponent } from './location-model/location-model.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { InvoiceFormComponent } from './invoice-form/invoice-form.component';
import { ShortestRouteComponent } from './shortest-route/shortest-route.component';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    AppComponent,
    ProductlistComponent,
    ShopsListComponent,
    LoginComponent,
    RegisterComponent,
    AddProductsComponent,
    ProductDetailsComponent,
    RecommandationComponent,
    OrderhistoryComponent,
    AddProductsComponent,
    ModalFormComponent,
    MycartComponent,
    OrderSummaryComponent,
    HomepageComponent,
    DetailFormComponent,
    VerifyEmailComponent,
    LocationAndDirectionsComponent,
    DealOfTheDayComponent,
    RegisterShopComponent,
    ChatComponent,
    ChatloginComponent,
    NavBarComponent,
    LocationModelComponent,
    InvoiceFormComponent,
    ShortestRouteComponent,
  ],
  entryComponents: [ModalFormComponent],
  imports: [
    MatGridListModule,
    NgbModule,
    CarouselModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatCheckboxModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatDialogModule,
    MatTabsModule,
    MatCardModule,
    ScrollingModule,
    OAuthModule.forRoot(),
    MatCardModule,
    ReactiveFormsModule,
    MatTooltipModule,
    MatChipsModule,
    MatRippleModule,
    MatExpansionModule,
    FlexLayoutModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCLXtO-nKhBD_BGmoqS1axBdFT2Zmj2r8A',
      libraries: ['places', 'drawing', 'geometry'],
    }),
    AvatarModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
