import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse, HttpClient, HttpRequest } from '@angular/common/http';
import { Charity } from 'src/app/models/charity';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { CharityService } from 'src/app/services/charity.service';

@Component({
  selector: 'app-edit-charity',
  templateUrl: './edit-charity.component.html',
  styleUrls: ['./edit-charity.component.css']
})
export class EditCharityComponent implements OnInit {
  fileUpload: File
  progress: { percentage: number } = { percentage: 0 }
  httpClient: HttpClient;
  selectedFile: File;
  charityToEditId: number;
  charity: Charity;
  private routeSub: Subscription;
  
  constructor(private uploadService: UploadFileService,
              private charityService: CharityService,
              http: HttpClient,
              private router: Router,
              private route: ActivatedRoute
              ) { 
    this.httpClient = http;
  }

  ngOnInit() {   
    this.routeSub = this.route.params.subscribe(params => {
      this.charityToEditId = params['id'];
      this.charityService.getCharity(params['id']).subscribe(data => {
        this.charity = data;
      })
    });
  }


  edit() {
    let charityEdit: Charity = new Charity();
  
    const req = new HttpRequest('PUT', 'http://localhost:8080/charities/put/' + this.charityToEditId, this.charity); 
    
    this.httpClient.request(req).subscribe(
      res => {
        console.log(res);
      },
      err => {
          console.log("Error occured");
      }
    );

    this.router.navigate(['/home']);
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
    this.router.navigate(['/home']);
  }

  upload() {
    this.progress.percentage = 0;
    this.fileUpload = this.selectedFile;
    this.uploadService.pushFile(this.fileUpload).subscribe(event => {
      console.log(event);
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
        this.charity.image = new String(event.body).valueOf();
        console.log(this.charity.image);
      }
    });
   
    this.selectedFile = undefined;
  }

}
