import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Entry} from "./entry";

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor(private httpClient: HttpClient) {
  }

  findAll() {
    return new Promise<Entry[]>((resolve, reject) => {
      this.httpClient
        .get('api/entry/')
        .subscribe(resp => {
          console.log("ENTRY/ALL", resp)
          resolve();
        }, (err => {
          console.log("ENTRY/ALL/ERR", err)
          reject(err);
        }))
    })
  }
}
