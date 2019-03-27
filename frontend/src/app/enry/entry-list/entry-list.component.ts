import {Component, OnInit} from '@angular/core';
import {EntryService} from "../entry.service";

@Component({
  selector: 'entry-list',
  templateUrl: './entry-list.component.html',
  styleUrls: ['./entry-list.component.scss']
})
export class EntryListComponent implements OnInit {

  constructor(private entryService: EntryService) {
  }

  ngOnInit() {

  }

  onTest() {
    this.entryService.findAll().then(x => {
      console.log("XXX")
    })
  }
}
