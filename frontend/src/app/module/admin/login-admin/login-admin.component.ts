import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import { MessageService } from 'primeng/api';
import {environment} from 'src/environments/environment';
import { AuthService } from 'src/app/zynerator/security/Auth.service';


@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.scss'],
  providers: [MessageService]
})
export class LoginAdminComponent implements OnInit {
  loginForm = new FormGroup({
    username:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required)
  })
  constructor(private authService:AuthService,private router: Router, private messageService: MessageService) { }

  ngOnInit(): void {
  }
  submit() {
    const formValues = this.loginForm.value;
    const username = formValues.username;
    const password = formValues.password;
    this.authService.loginAdmin(username, password).subscribe(
      () => {
        // Show success toast
        this.messageService.add({severity:'success', summary:'Success', detail:'You are logged in successfully.'});
        this.router.navigate(['/' + environment.rootAppUrl + '/admin']);
      },
      (error) => {
        // Show warning toast
        this.messageService.add({severity:'error', summary:'Error', detail:'Invalid username or password.'});
        console.log(error);
      }
    );
  }
  
    register(){
    this.router.navigate(['/admin/register']);
  }
}
