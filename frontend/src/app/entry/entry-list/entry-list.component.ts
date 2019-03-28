import {Component, OnInit} from '@angular/core';
import {EntryService} from "../entry.service";
import {Entry} from "../entry";
import {PageEvent} from "@angular/material";

@Component({
  selector: 'entry-list',
  templateUrl: './entry-list.component.html',
  styleUrls: ['./entry-list.component.scss']
})
export class EntryListComponent implements OnInit {

  entries: Entry[] = [];
  displayedColumns: string[] = ['id','created','time'];

  totalElements: number = 0;
  pageSize: number = 20;
  pageSizeOptions: number[] = [10, 20, 50, 100];
  currentPage: number = 0;

  constructor(private entryService: EntryService) {
  }

  ngOnInit() {
  }

  onPagination(e: PageEvent) {
    this.entryService
      .page({page: e.pageIndex, size: e.pageSize})
      .then(entryResponse => {
        this.entries = entryResponse.content;
        this.totalElements = entryResponse.totalElements;
        this.currentPage =  entryResponse.number;
        this.pageSize = entryResponse.size;
      })
  }
}
