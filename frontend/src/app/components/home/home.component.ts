import { Component, OnInit } from '@angular/core';
import { Charity } from 'src/app/models/charity';
import { CharityService } from 'src/app/services/charity.service';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  hasSearched: boolean;
  searchTerm: string;
  charities: Charity[];
  filteredCharities: Charity[];

  constructor(private charityService: CharityService,private uploadFileService: UploadFileService) {
      this.hasSearched = false;
  }

  ngOnInit() {
    this.charityService.findAll().subscribe(data => {
      this.charities = data;
      this.filteredCharities = data;
    });
  }

  handleTermChange(event){
    console.log('>>>>>>>', event);
    this.searchTerm = event.trim();
    if(this.searchTerm === ''){
      this.hasSearched = false;
      this.filteredCharities = this.charities;
    }
  }

  getCharities(): void {
    this.charityService.findAll();
  }

  getImage(image: string) : string{
    return this.uploadFileService.getImage(image);
  }

  add(title: string, description: string): void {
    title = title.trim();
    if (!title) { return; }
    this.charityService.save({title,description} as Charity)
      .subscribe(charity => {
        this.charities.push(charity);
      });
  }

  searchBy(searchTerm: string){
    this.filteredCharities = this.filteredCharities.filter(charity => charity.title.toLowerCase().includes(searchTerm.toLowerCase()));
  }
  clear() {
    this.filteredCharities = this.charities;
  }

  delete(charity: Charity): void {
    this.charities = this.charities.filter(ch => ch !== charity);
    this.charityService.delete(charity);
  }
 
}
