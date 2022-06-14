import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-modal-form',
  templateUrl: './modal-form.component.html',
  styleUrls: ['./modal-form.component.css']
})
export class ModalFormComponent implements OnInit {
 formData:any={};


  Email=localStorage.getItem('userDetail');
  
  

  myform= new FormGroup({
    userName: new FormControl(''),
    dob: new FormControl('')
  })
  myform1=new FormGroup({
    streetName: new FormControl('',Validators.required),
    city: new FormControl('',Validators.required),
    landMark: new FormControl('',Validators.required),
    state:new FormControl('',Validators.required),
    pin:new FormControl('',Validators.required),
    nationality:new FormControl('',Validators.required)
  })
  constructor(private serve:ServiceService,
    private route:Router,
    private router:ActivatedRoute) { }
  

  ngOnInit(): void {  

    
  
      const obj= this.serve.getCurrentData(this.Email);
      console.log(this.Email)
      console.log(obj);
      obj.subscribe((res:any)=>{
        console.log(res);
        if(res.dob!=undefined || res.dob!=null )
        {
          this.myform.setValue({
            userName: res['userName'],
            dob: res['dob'],
          
          })
          this.myform1.setValue({
            streetName:res.address['streetName'],
             city: res.address['city'],
             landMark:res.address['landMark'],
             state: res.address['state'],
             pin: res.address['pin'],
             nationality:res.address['nationality']
   
          })
        }
        else{
          this.myform.setValue({
            userName: res['userName'],
            dob:"",
            mobileNo:""
          })
          this.myform1.setValue({
            streetName:"",
             city:"",
             landMark:"",
             state:"",
             pin: "",
             nationality:""
   
          })

        }
        
      
      })
    
 
  }
  updateFunction(){
    let finalForm = {
      ...this.myform.value,
      "email":this.Email,
      "address":this.myform1.value
  };
      this.serve.updateCard(finalForm)
      console.log(this.myform.value)

      console.log(this.myform1.value) 
      console.log(finalForm)
  }

}
