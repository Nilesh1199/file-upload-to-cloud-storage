import { Component, OnInit } from '@angular/core';
import { FileUploadService } from '../service/file-upload.service';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {
  fileList: any[];
  displayedColumns: string[] = ['fileUrl', 'date']
  constructor(
    private fileUploadService: FileUploadService
  ) { }

  ngOnInit() {
    this.getFileList();
  }
  getFileList() {
    this.fileUploadService.getFileList().subscribe(res=>{
    this.fileList= res;
    }, err=>{
      console.error(err);
    })
  }

}
