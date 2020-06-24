import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient, HttpRequest, HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Charity } from 'src/app/models/charity';
import { Router } from '@angular/router';
import { logging } from 'protractor';



@Component({
  selector: 'app-create-charity',
  templateUrl: './create-charity.component.html',
  styleUrls: ['./create-charity.component.scss']
})
export class CreateCharityComponent implements OnInit {
  title: string;
  description: string;
  donation_requered: number;
  path: string;
  fileUpload: File
  progress: { percentage: number } = { percentage: 0 }
  httpClient: HttpClient;
  selectedFile: File;
  
  constructor(private uploadService: UploadFileService, http: HttpClient,private route: Router) { 
    this.httpClient = http;
  }

  ngOnInit() {
  }


  create() {
    let charity: Charity = new Charity();
    charity.title = this.title;
    charity.description = this.description;
    charity.amountRequired = this.donation_requered;
    charity.amountCollected = 0;
    charity.image = this.path;
    console.log(charity);
    const req = new HttpRequest('POST', 'http://localhost:8080/charities', charity); 
    
    this.httpClient.request(req).subscribe(
      res => {
        console.log(res);
      },
      err => {
          console.log("Error occured");
      }
    );

    this.btnClickCancel();
  }


  selectFile(event) {
    const file = event.target.files.item(0);
 
    if (file.type.match('image.*')) {
      this.selectedFile = event.target.files.item(0);
    } else {
      alert('invalid format!');
    }
  }
 
  btnClickCancel() {
    this.route.navigate(['/home']);
};

  upload() {
    this.progress.percentage = 0;
    this.fileUpload = this.selectedFile;
    this.uploadService.pushFile(this.fileUpload).subscribe(event => {
      console.log(event);
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.path = new String(event.body).valueOf();
        console.log(this.path);
      }
    });
   
    this.selectedFile = undefined;
  }
 

}
