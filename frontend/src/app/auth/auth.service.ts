import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpResponseBase} from "@angular/common/http";
import {DRIVERS, Locker} from "angular-safeguard";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public token: BehaviorSubject<string> = new BehaviorSubject(null);

  constructor(private httpClient: HttpClient, private locker: Locker) {
    this.token.next(this.readToken());
  }

  login(username: string, password: string): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.httpClient
        .post('/auth/', {username: username, password: password}, {observe: 'response'})
        .subscribe(resp => {
          let token = this.readTokenFromResponse(resp);
          this.token.next(token);
          this.storeToken(token);
          resolve(token);
        }, error => {
          reject(error);
        })
    });
  }

  logout() {
    this.token.next(null);
    this.storeToken(null);
  }

  checkLoggedIn(): Promise<boolean> {
    return new Promise<boolean>((resolve, reject) => {
      this.httpClient
        .get('/api/auth/check')
        .subscribe(() => {
          resolve(true);
        }, error => {
          if(error.status===401){
            resolve(false);
          }else {
            reject(error);
          }
        })
    });
  }

  private storeToken(token: string) {
    this.locker.set(DRIVERS.LOCAL, 'access-token', token);
  }

  private readToken(): string {
    return this.locker.get(DRIVERS.LOCAL, 'access-token');
  }

  readTokenFromResponse(response: HttpResponseBase) {
    let token = response.headers.get('authorization');
    if (token && token.startsWith('Bearer ')) {
      token = token.substr(7);
    }
    return token;
  }

  createAuthenticatedRequest(request: HttpRequest<any>): HttpRequest<any> {
    let token = this.readToken();

    if (token) {
      return request.clone({
        headers: request.headers.set('authorization', 'Bearer ' + this.readToken())
      });
    } else {
      return request;
    }
  }
}
