import axios from 'axios'

const employee_service_baseurl = "http://localhost:9191/api/employees"

const employee_id = 2;

class EmployeeService{
    getEmployee(){
        return axios.get(employee_service_baseurl + '/' + employee_id);
    }
}
export default new EmployeeService