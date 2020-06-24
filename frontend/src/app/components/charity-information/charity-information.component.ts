import { Component, OnInit } from '@angular/core';
import { Charity } from 'src/app/models/charity';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CharityService } from 'src/app/services/charity.service';
import { UploadFileService } from 'src/app/services/upload-file.service';

@Component({
  selector: 'app-charity-information',
  templateUrl: './charity-information.component.html',
  styleUrls: ['./charity-information.component.css']
})
export class CharityInformationComponent implements OnInit {
  charity: Charity;
  private routeSub: Subscription;
  amountToDonate: number;

  constructor(private charityService: CharityService,private routeOther: Router, private uploadFileService: UploadFileService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.amountToDonate = 0;
    this.routeSub = this.route.params.subscribe(params => {
      this.charityService.getCharity(params['id']).subscribe(data => {
        this.charity = data;
      })
    });
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

  getImage(image: string) : string{
    return this.uploadFileService.getImage(image);
  }
  btnEdit():void{
    this.routeOther.navigate(['/edit/' + this.charity.id]);
  }

  delete(){
    this.charityService.delete(this.charity.id);   
    this.routeOther.navigate(['/home']);
  }

  donate(){
    this.charity.amountCollected += this.amountToDonate;
    if(this.charity.amountCollected > this.charity.amountRequired)
      this.charity.amountCollected = this.charity.amountRequired;
    this.amountToDonate = 0;
    this.charityService.updateCharity(this.charity);
  }
}
