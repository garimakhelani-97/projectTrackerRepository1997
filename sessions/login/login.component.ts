import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MatCarouselSlideComponent } from '@ngmodule/material-carousel';
import { AuthService } from '@core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  items = [
    { title: 'General Program Overall Review', desc: 'GPOR Process is used to track financials and overall status of all the projects from the time of winning the project to post-launch stage which is 3-6 months after start of production.' },
    { title: 'Integrated Controlling File', desc: 'ICF Process is used to track Customer Contributions (Revenues) i.e. payment done by Customers for financing D&E, CAPEX, Launch Cost etc. for the Projects. This process is implemented only in French & German BUs.' },
    { title: 'Project Tooling Reporting', desc: 'PTR Process is used to track Tooling related Cashflows, Margins & POCM Margins of each project during the life cycle of Projects.' },
    { title: 'Research & Development Process', desc: 'R&D Process is used to track Direct & Indirect Revenues & Expenses of Technical Centers for all BUs and allocation of Costs to other operating Plants by CSA Mechanism.'}
  ];

  public parentHeight = '100vh';
  public timings = '250ms ease-in';
  public autoplay = true;
  public interval = 5000;
  public loop = true;
  public hideArrows = false;
  public hideIndicators = false;
  public maxWidth = 'auto';
  public maintainAspectRatio = true;
  public proportion = 25;
  public slideHeight = '100vh';
  public hideOverlay = true;
  public useKeyboard = true;
  public useMouseWheel = false;
  public log: string[] = [];
  public slides;
  reactiveForm: FormGroup;

  @ViewChildren(MatCarouselSlideComponent) public carouselSlides: QueryList<
    MatCarouselSlideComponent
  >;
  
  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {
    this.reactiveForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  ngOnInit() {}

  login() {
    //this.router.navigateByUrl('/');
    this.authService.attempLogin(this.reactiveForm.get('username').value, this.reactiveForm.get('password').value);
  }
}
