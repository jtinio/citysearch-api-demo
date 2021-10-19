import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { debounceTime, tap, switchMap, finalize, distinctUntilChanged, filter } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

const API_KEY = "e8067b53"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  searchCitiesCtrl = new FormControl();
  filteredCities: any;
  isLoading = false;
  errorMsg!: string;
  minLengthTerm = 3;
  selectedCity: any = "";

  constructor(
    private http: HttpClient
  ) { }

  onSelected() {
    console.log(this.selectedCity);
    this.selectedCity = this.selectedCity;
  }

  displayWith(value: any) {
    return value?.Title;
  }

  clearSelection() {
    this.selectedCity = "";
    this.filteredCities = [];
  }

  ngOnInit() {
    this.searchCitiesCtrl.valueChanges
      .pipe(
        filter(res => {
          return res !== null && res.length >= this.minLengthTerm
        }),
        distinctUntilChanged(),
        debounceTime(1000),
        tap(() => {
          this.errorMsg = "";
          this.filteredCities = [];
          this.isLoading = true;
        }),
        switchMap(value => this.http.get(environment.apiUrl + '/city/search?name=' + value)
          .pipe(
            finalize(() => {
              this.isLoading = false
            }),
          )
        )
      )
      .subscribe((data: any) => {
        this.errorMsg = "";
        this.filteredCities = data;
        console.log(this.filteredCities);
      });
  }
}
