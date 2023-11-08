export class Employee {
    id: number = 0;
    employeeName: string = '';
    mobile: string = '';
    gender: string = '';
    // technicalSkills: string[] = [];
    technicalSkills: { id: number, skill: string }[] = [];
    address: string = '';
    employeeCode:string='';
    department: string = '';
    designation: string = '';
    email: string = '';
}
