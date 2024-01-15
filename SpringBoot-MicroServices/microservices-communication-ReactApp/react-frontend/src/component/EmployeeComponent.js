import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {
    constructor(props) {
        super(props);
        this.state ={
            employee: {},
            department: {},
            organisation: {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((response) => {
            this.setState({employee : response.data.employeeDto})
            this.setState({department : response.data.departmentDto})
            this.setState({organisation : response.data.organisationDto})

            console.log(this.state.employee)
            console.log(this.state.department)
            console.log(this.state.organisation)
        })
    }
    
    render() {
        return (
            <div> <br/> <br/>
                <div className='card col-md-6 offset-md-3'>
                    <h3 className='text-center card-header'>View Employee Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Employee First Name: </strong>{this.state.employee.firstName}</p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Last Name: </strong>{this.state.employee.lastName}</p>
                        </div>
                    </div>
                    <h3 className='text-center card-header'>View Department Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Department Name: </strong>{this.state.department.departmentName}</p>
                        </div>
                        <div className='row'>
                            <p><strong>Department Description: </strong>{this.state.department.departmentDescription}</p>
                        </div>
                    </div>
                    <h3 className='text-center card-header'>View Organisation Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Organisation Name: </strong>{this.state.organisation.organisationName}</p>
                        </div>
                        <div className='row'>
                            <p><strong>Organisation Description: </strong>{this.state.organisation.organisationDescription}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default EmployeeComponent;