import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Charity } from '../models/charity'
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CharityService {
  charitysUrl: string;

  constructor(private http: HttpClient) { 
    this.charitysUrl = 'http://localhost:8080/charities';
  }

  public findAll() : Observable<Charity[]> {
    return this.http.get<Charity[]>(this.charitysUrl + '/all');
  }

  public save(charity: Charity): Observable<Charity> {
    return this.http.post<Charity>(this.charitysUrl, charity);
  }

  public delete(charity: Charity | number) {
    const id = typeof charity === 'number' ? charity : charity.id;
    const url = `${this.charitysUrl}/delete/${id}`;
   
    return this.http.delete(url).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  public updateCharity(charity: Charity) {
    const id = charity.id;
    const url = `${this.charitysUrl}/put/${id}`;
    return this.http.put(url, charity).subscribe(
      res => {
        console.log(res);
      }
    );
  }

  public getCharity(id: number): Observable<Charity> {
    const url = `${this.charitysUrl}/get/${id}`;
    return this.http.get<Charity>(url);
  }
}
