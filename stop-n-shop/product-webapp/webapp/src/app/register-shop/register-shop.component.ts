import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RegisterShopService } from './register-shop.service';

@Component({
  selector: 'app-register-shop',
  templateUrl: './register-shop.component.html',
  styleUrls: ['./register-shop.component.css']
})
export class RegisterShopComponent implements OnInit {

  constructor(private registrationService:RegisterShopService) { }

  emailId:any;
  ownerNamee:any;
  number:any;

  ngOnInit(): void {
    this.emailId=localStorage.getItem("userDetail");
    this.getUserDetails(this.emailId);
  }

  getUserDetails(email:any){
   this.registrationService.getUserDetails(email).subscribe( response=>{
      // console.log(response);
      this.ownerNamee=response.userName;
      this.number=response.mobileNo;
      });
  }

  isLinear = true;
 
  formDetailsGroup=new FormGroup({
    shopName:new FormControl('',[Validators.required]),
    ownerName:new FormControl('',[Validators.required]),
    email:new FormControl({value:'', disabled: true}, Validators.required),
    contactNo:new FormControl('',[Validators.required]),
    description:new FormControl(''),
    file:new FormControl('',[Validators.required])
  });

  get shopName(){
    return this.formDetailsGroup.controls['shopName']
  }
  get ownerName(){
    return this.formDetailsGroup.controls['ownerName']
  }
  get email(){
    return this.formDetailsGroup.controls['email']
  }
  get contactNo(){
    return this.formDetailsGroup.controls['contactNo']
  }
  get description(){
    return this.formDetailsGroup.controls['description']
  }
  get file(){
    return this.formDetailsGroup.controls['shopName']
  }

  formAddressGroup=new FormGroup({
    address1:new FormControl('',[Validators.required]),
    address2:new FormControl(''),
    city:new FormControl('',[Validators.required]),
    state:new FormControl('',[Validators.required]),
    pincode:new FormControl('',[Validators.required]),
    landmark:new FormControl('')
  });
  get address1(){
    return this.formAddressGroup.controls['address1']
  }
  get address2(){
    return this.formAddressGroup.controls['address2']
  }
  get city(){
    return this.formAddressGroup.controls['city']
  }
  get state(){
    return this.formAddressGroup.controls['state']
  }
  get pincode(){
    return this.formAddressGroup.controls['pincode']
  }
  get landmark(){
    return this.formAddressGroup.controls['landmark']
  }
  
  latitude: number= 11.7873;
  longitude: number=77.5947;
  record:any;
  address:any;
  getShopLocation(){
    this.address=this.formAddressGroup.value.address1+","+this.formAddressGroup.value.address2+""+this.formAddressGroup.value.city+","+this.formAddressGroup.value.state+","+this.formAddressGroup.value.pincode;
    this.registrationService.getCoordinates(this.address).subscribe( response=>{
      this.record=response;
      
      this.latitude=this.record.features[0].properties.lat;
      this.longitude=this.record.features[0].properties.lon;
      // console.log( this.latitude+""+ this.longitude)
      });
  }

onMapClicked(event: any){
    // console.table(event.coords);
    this.latitude = event.coords.lat;
   this.longitude = event.coords.lng;
}
  
  formLocationGroup=new FormGroup({
  });

  selectedFile: any;
  url:any;
  getFileData(event: any) {
    this.selectedFile=event.target.files[0];
    var reader=new FileReader();
    reader.readAsDataURL(this.selectedFile)
    reader.onload=(data:any)=>{
      this.url=data.target.result
  }
  }

  
  shopDetailsTosend:any;
   shopDetailsToBeSent(){
      
    this.shopDetailsTosend={
        email:localStorage.getItem("userDetail"),
        shopName:this.formDetailsGroup.value.shopName,
        location:{
          latitude:this.latitude,
          longitude:this.longitude
                },

        ownerName:this.formDetailsGroup.value.ownerName,
        contactNo:this.formDetailsGroup.value.contactNo,
        description:this.formDetailsGroup.value.description,
        
        address:{
        addressLine1:this.formAddressGroup.value.address1,
        addressLine2:this.formAddressGroup.value.address2,
        city:this.formAddressGroup.value.city,
        state:this.formAddressGroup.value.state,
        pincode:this.formAddressGroup.value.pincode,
        landmark:this.formAddressGroup.value.landmark
      }
       
       }
      //  console.log(this.shopDetailsTosend);
       this.AddShop();
     }


     AddShop(){
        var data=new FormData();
        // console.log(this.selectedFile.file);
        data.append('file',this.selectedFile,this.selectedFile.file);
        data.append('Shop',JSON.stringify(this.shopDetailsTosend));
        delete this.shopDetailsTosend['file'];
        this.registrationService.addShop(data).subscribe(a=>{
          // console.log(a);
        })
       }


  }
