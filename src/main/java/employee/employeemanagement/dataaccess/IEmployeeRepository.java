package employee.employeemanagement.dataaccess;

import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.dto.EmployeeDevicesSummaryDto;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee getEmployee(int id);
    Employee replaceEmployee(int id, Employee employee);
    Employee modifyEmployee(int id, Employee employee);
    boolean isEmployeesEmpty();
    boolean employeeExists(int id);
    boolean isEmployeeValid(Employee employee);
    void deleteEmployee (int id);
    Employee removeDevice(int id, String deviceName);
    Employee addDevice(int id, String deviceName);
    EmployeeDevicesSummaryDto employeesNumberOfDevices();
//    boolean isEmployeeCodeValid(String number);
}
