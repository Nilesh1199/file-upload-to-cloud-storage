import { Component, OnInit } from '@angular/core';
import { FileUploadService } from '../service/file-upload.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  fileToUpload: File;
  isValidPDF: boolean;
  fileName: string ;
  isFormDisabled= true;
  pdfUpload: string;
  constructor(
    private fileUploadService: FileUploadService
  ) { }

  ngOnInit() {
    this.fileName= '';
  }

  addFileToJson(files: FileList) {
    this.fileToUpload = files.item(0);
    if(this.fileToUpload.type === "application/pdf" && this.fileToUpload.size < 1048576){
      this.isValidPDF = true;
      this.isFormDisabled = false;
    }else{
      this.isValidPDF= false;
      this.isFormDisabled = true;
    }
    // this.fileToUpload.size
  }

  uploadPdf(){
    const formData = new FormData();
    formData.append('file', this.fileToUpload);
    formData.append('fileName', this.fileName);

    this.fileUploadService.uploadFile(formData).subscribe(
      (res=>{this.onSuccess(res.body, res.headers)}), 
      (err=>{this.onError(err.body)})
    )
  }
  onError(body: any) {
    this.pdfUpload= "failed";
  }
  onSuccess(body: any, headers: any) {
    this.pdfUpload= "success";
  }



}
