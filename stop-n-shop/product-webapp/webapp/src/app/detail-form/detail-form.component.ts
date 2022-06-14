import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { ModalFormComponent } from '../modal-form/modal-form.component';
import { ServiceService } from '../service.service';
import { AvatarModule, AvatarConfig } from "ngx-avatar";
@Component({
  selector: 'app-detail-form',
  templateUrl: './detail-form.component.html',
  styleUrls: ['./detail-form.component.css']
})
export class DetailFormComponent implements OnInit {

  formData: any = {};
  res: any = {};
  role: boolean = false;


  @Input()
  Email: any;

  myform = new FormGroup({
    userName: new FormControl(''),
    dob: new FormControl('')
  })
  myform1 = new FormGroup({
    streetName: new FormControl(''),
    city: new FormControl(''),
    landMark: new FormControl(''),
    state: new FormControl(''),
    pin: new FormControl(''),
    nationality: new FormControl('')
  })
  constructor(private serve: ServiceService,
    private route: Router,
    private router: ActivatedRoute,
    public dialog: MatDialog) { }


  ngOnInit(): void {

    this.Email = localStorage.getItem('userDetail');
    const obj = this.serve.getCurrentData(this.Email);
    console.log(obj);
    obj.subscribe({
      next: (data: any) => {
        this.res = data;
        console.log(this.res);
        console.log(data.dob);

        if (data.dob != null) {
          this.myform.setValue({
            userName: this.res['userName'],
            dob: this.res['dob']
          })
          this.myform1.setValue({
            streetName: this.res.address['streetName'],
            city: this.res.address['city'],
            landMark: this.res.address['landMark'],
            state: this.res.address['state'],
            pin: this.res.address['pin'],
            nationality: this.res.address['nationality']

          })
        }
        else {
          console.log("hi");
          this.myform.setValue({
            userName: this.res['userName'],
            dob: ""
          })
          this.myform1.setValue({
            streetName: "",
            city: "",
            landMark: "",
            state: "",
            pin: "",
            nationality: ""

          })
        }
      }
    })
    this.setRole();
  }
  setRole() {
    const role1 = '"' + "seller" + '"';
    const role2 = localStorage.getItem('userRole');
    console.log(role1);
    console.log(role2);
    if (role1 == role2) {
      this.role = !this.role;
      console.log(this.role);
    }
  }

  updateFunction() {
    let finalForm = {
      ...this.myform.value,
      "email": this.Email,
      "address": this.myform1.value
    };
    this.serve.updateCard(finalForm)
    console.log(this.myform.value)

    console.log(this.myform1.value)
    console.log(finalForm)
  }
  openDialogue() {
    this.dialog.open(ModalFormComponent);
  }


}
