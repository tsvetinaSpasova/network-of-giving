import { Injectable } from '@angular/core';
import { HttpEvent, HttpRequest, HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UploadFileService {

  constructor(private http: HttpClient) {}
 
  pushFile(file: File): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('file', file);
 
    const req = new HttpRequest('POST', 'http://localhost:8080/image', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }


  
  getImage(filename: string) : string{
    console.log('http://localhost:8080/image/' + filename);
    //return this.http.get('http://localhost:8080/image/' + filename, { responseType: 'blob' });
    //return this.http.get('http://localhost:8080/image/' + filename); 
    return 'http://localhost:8080/image/' + filename
  }
  
}
