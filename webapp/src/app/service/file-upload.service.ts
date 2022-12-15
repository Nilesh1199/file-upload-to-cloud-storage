import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FileUploadService {
  constructor(private http: HttpClient) {}
  SERVER_API_URL= 'http://54.144.107.137/srv/'
  
  uploadFile(fileDetails): Observable<any> {
    return this.http.post<any>(this.SERVER_API_URL + 'api/upload-file', fileDetails);
  }

  getFileList(){
    return this.http.get<any>(this.SERVER_API_URL + 'api/get-files');
  }

}
