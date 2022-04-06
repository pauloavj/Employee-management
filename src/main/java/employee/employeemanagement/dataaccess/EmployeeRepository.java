package employee.employeemanagement.dataaccess;

import employee.employeemanagement.models.domain.CheckEmployeeCode;
import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository implements IEmployeeRepository{
    @Autowired
    private CheckEmployeeCode checkEmployeeCode;

    private final List<Employee> employees = seedEmployees();

    private List<Employee> seedEmployees() {
        var initialEmployees = new ArrayList<Employee>();
        initialEmployees.add(new Employee(
                "James",
                "Bond",
                "8072635082",
                LocalDate.of(1998,9,25),
                true,
                Position.Developer,
                LocalDate.of(2016,10,6)
                ));
        initialEmployees.add(new Employee(
                "Rafael",
                "Nadal",
                "9863003985",
                LocalDate.of(1988,5,21),
                false,
                Position.Tester,
                LocalDate.of(2011,1,25)
        ));
        return initialEmployees;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(int id){
        return employees.
                stream()
                .filter(specificEmployee -> specificEmployee.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return getEmployee(employee.getId());
    }

    @Override
    public Employee replaceEmployee(int id, Employee employee){
        employees.set(employees.indexOf(getEmployee(id)),employee);
        return getEmployee(employee.getId());
    }

    @Override
    public Employee modifyEmployee(int id, Employee employee) {
        var employeeToModify = getEmployee(id);
        employeeToModify.setFirstName(employee.getFirstName());
        employeeToModify.setLastName(employee.getLastName());
        employeeToModify.setEmployeeCode(employee.getEmployeeCode());
        employeeToModify.setDateOfBirth(employee.getDateOfBirth());
        employeeToModify.setFullTime(employee.isFullTime());
        employeeToModify.setPosition(employee.getPosition());
        employeeToModify.setDateHired(employee.getDateHired());
        return getEmployee(id);
    }

    @Override
    public boolean isEmployeesEmpty() {
        return employees.isEmpty();
    }

    @Override
    public boolean employeeExists(int id) {
        var employeeExists = employees
                .stream()
                .filter(match -> match.getId() == id)
                .count();
        return employeeExists > 0;
    }

    @Override
    public boolean isEmployeeValid(Employee employee) {
        return employee.getFirstName() != null &&
                employee.getLastName() != null &&
                employee.getEmployeeCode() != null &&
                employee.getDateOfBirth() != null &&
                employee.getPosition() != null &&
                employee.getDateHired() != null &&
                checkEmployeeCode.isLuhnNumber(employee.getEmployeeCode());
    }

    @Override
    public void deleteEmployee(int id) {
        employees.remove(getEmployee(id));
    }
}
