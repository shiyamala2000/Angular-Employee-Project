import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Employee } from '../employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {

  employees: Employee[] = [];
  private apiUrl = 'http://localhost:8080/employee/';
  
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllEmployees();
  }

  getAllEmployees() {
    this.http.get<Employee[]>(`${this.apiUrl}`).subscribe(
      (response) => {
        this.employees = response;
        console.log('All employees:', response);
      },
    )
  }
}
