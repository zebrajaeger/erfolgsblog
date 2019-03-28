import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Entry} from "./entry";
import {PageRequest, PageResponse} from "../util/pageable";

export interface EntryResponse extends PageResponse<Entry>{};

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  pageSize: number = 20;

  constructor(private httpClient: HttpClient) {
  }

  page(options: PageRequest) {
    return new Promise<EntryResponse>((resolve, reject) => {
      this.httpClient
        .get<EntryResponse>('/api/entry/' + '?page:' + options.page + '&size:' + options.size)
        .subscribe(resp => {
          resolve(resp);
        }, (err => {
          console.log("ENTRY/ALL/ERR", err);
          reject(err);
        }))
    })
  }
}
