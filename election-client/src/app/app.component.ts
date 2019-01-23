import {Component, OnInit} from '@angular/core';
import {HttpService} from './HttpService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  name:string = 'N/A';

  constructor(private httpService: HttpService) {
  }

  ngOnInit(): void {
    this.httpService.getCampaigns().subscribe((response)=>{
      if (response!=null) {
        this.name = response["name"];
      }
    },(error)=>{
      console.log(error)
    })
  }
}
