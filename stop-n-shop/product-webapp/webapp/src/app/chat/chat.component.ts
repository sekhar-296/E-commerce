import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit {
  public userId!: string;
  public selectedUser!: any;
  public message: any = '';
  public messageHistory: any = [];
  public topic!: string;
  public buyersList: any = [];
  public sellersList: any = [];
  public users: any = [];
  public check: any = [];
  messages:any=[];
  public stompClient!: Stomp.Client;
  public flag: boolean = false;

  public webSocketEndPoint = 'http://localhost:9546/'+'/chat';

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private service: ServiceService
  ) {}

  ngOnInit() {
    this.messageHistory=[];
    this.userId = this.route.snapshot.params['userId'];
    this.connect();
    this.userService.getUsers().subscribe((users: any) => {
      this.users = users.filter((user: any) => user.userId !== this.userId);
      // this.users.forEach((element: any) => {
      //   this.service
      //     .fetchAuthenticatedEmailId(element.userId)
      //     .subscribe((data: any) => {
      //       console.log(data);
      //       if (data.role == 'SELLER') this.sellersList.push(data);
      //       else this.buyersList.push(data);
      //     });
      // });
      this.topic = '/topic/' + this.userId;
      if (users.length === 0) {
        this.router.navigate(['/']);
      }
    });
  }
  connect() {
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect(
      {},
      (frame) => {
        // After connection subscribe to the topic
        this.stompClient.subscribe(this.topic, (event) => {
          this.onMessageReceived(event.body);
        });
      },
      this.onError
    );
  }
  onError(error: any) {
    // Do something on error
  }
  onMessageReceived(payload: any) {
    let obj = JSON.parse(payload);
    this.messageHistory.push(obj);
    // console.log(this.messageHistory);
  }
  onUserSelect(user: any) {
    this.messageHistory=[];
    this.selectedUser = user;
    console.log(this.selectedUser);
    this.userService.getAllMessages(localStorage.getItem('userDetail'),this.selectedUser.userId).subscribe((res)=>{
      console.log(res);
      this.messages=res;
    })
  }
  sendMessage() {
    // Construct the payload
    const payload = {
      message: this.message,
      from: this.userId,
      to: this.selectedUser.userId,
      timestamp: new Date(),
    };
    // this.userService.saveMessage(payload);
    // Send the message to the web socket
    this.stompClient.send('/app/message', {}, JSON.stringify(payload));
    // push message to the current messages
    this.messageHistory.push(payload);
    // clear message
    this.message = '';
  }
}
//     if (localStorage.getItem('userRole') == '"seller"') {
//       this.flag = true;
//     } else {
//       this.flag = false;
//     }
//     this.userId = this.route.snapshot.params['userId'];
    
//     this.connect();
//     this.userService.getUsers().subscribe((users: any) => {
//       this.users = users.filter((user: any) => user.userId !== this.userId);
//       this.users.forEach((element: any) => {
//         this.service
//           .fetchAuthenticatedEmailId(element.userId)
//           .subscribe((data: any) => {
//             console.log(data);
//             if (data.role == 'SELLER') this.sellersList.push(data);
//             else this.buyersList.push(data);
//           });
//       });
//       console.log(this.sellersList);
//       console.log(this.buyersList);

//       this.topic = '/topic/' + this.userId;

//       if (users.length === 0) {
//         this.router.navigate(['/']);
//       }
//     });
//   }

//   connect() {
//     console.log("connect method called");
    
//     const ws = new SockJS(this.webSocketEndPoint);
//     this.stompClient = Stomp.over(ws);
//     this.stompClient.connect(
//       {},
//       (frame) => {
//         // After connection subscribe to the topic
//         this.stompClient.subscribe(this.topic, (event) => {
//           console.log("this is event body");
//           console.log(event.body);
//           this.onMessageReceived(event.body);
//         });
//       },
//       this.onError
//     );
//   }

//   onError(error: any) {
//     // Do something on error
//   }

//   onMessageReceived(payload: any) {
//     let obj = JSON.parse(payload);
//     this.messageHistory.push(obj);
//     console.log("this is messages history");
//     console.log(this.messageHistory);
    
//     // console.log(this.messageHistory);
//   }

//   onUserSelect(user: any) {

//     this.selectedUser = user;
//     console.log("this is selelcted user");
    
//     console.log(this.selectedUser);
//   }

//   sendMessage() {
//     // Construct the payload
//     const payload = {
//       message: this.message,
//       from: this.userId,
//       to: this.selectedUser.email,
//       timestamp: new Date(),
//     };
//     // this.userService.saveMessage(payload);
//     // Send the message to the web socket
//     this.stompClient.send('/app/message', {}, JSON.stringify(payload));
//     // push message to the current messages
//     this.messageHistory.push(payload);
//     console.log(this.messageHistory);
    
//     // clear message
//     this.message = '';
//   }
// }
