package employee.employeemanagement.dataaccess;

import employee.employeemanagement.models.domain.CheckEmployeeCode;
import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.domain.Position;
import employee.employeemanagement.models.domain.WorkTime;
import employee.employeemanagement.models.dto.EmployeeDevicesSummaryDto;
import employee.employeemanagement.models.dto.FullTimeEmployeesDto;
import employee.employeemanagement.models.dto.HiredEmployeeDto;
import employee.employeemanagement.models.maps.EmployeeDevicesSummaryDtoMapper;
import employee.employeemanagement.models.maps.FullTimeEmployeeDtoMapper;
import employee.employeemanagement.models.maps.HiredEmployeeDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class EmployeeRepository implements IEmployeeRepository{
    @Autowired
    private CheckEmployeeCode checkEmployeeCode;


    private HiredEmployeeDtoMapper hiredEmployeeDtoMapper;


    private final List<Employee> employees = seedEmployees();

//    @Autowired
//    public EmployeeRepository(CheckEmployeeCode checkEmployeeCode) {
//        this.checkEmployeeCode = checkEmployeeCode;
//    }

    @Autowired
    public EmployeeRepository(HiredEmployeeDtoMapper hiredEmployeeDtoMapper) {
        this.hiredEmployeeDtoMapper = hiredEmployeeDtoMapper;
    }

    private List<Employee> seedEmployees() {
        var initialEmployees = new ArrayList<Employee>();
        initialEmployees.add(new Employee(
                "John",
                "Doe",
                "2018269734",
                LocalDate.of(1980,02,14),
                WorkTime.PartTime,
                Position.Tester,
                LocalDate.of(2021,3,31),
                new ArrayList<>(){{add("Dell XPS 13");}}));
        initialEmployees.add(new Employee(
                "Jane",
                "Doe",
                "1634751505",
                LocalDate.of(1984,5,17),
                WorkTime.FullTime,
                Position.Manager,
                LocalDate.of(2018,12,1),
                new ArrayList<>(){{
                    add("Alienware X17");
                    add("Samsung30-inch LED Monitor");
                }}));
        initialEmployees.add(new Employee(
                "Sven",
                "Svensson",
                "1507119608",
                LocalDate.of(1987,3,22),
                WorkTime.FullTime,
                Position.Developer,
                LocalDate.of(2019,2,15),
                new ArrayList<>(){{
                    add("Dell XPS 15");
                    add("Samsung 27-inch LED Monitor");
                }}));
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
        employeeToModify.setWorkTime(employee.getWorkTime());
        employeeToModify.setPosition(employee.getPosition());
        employeeToModify.setDateHired(employee.getDateHired());
        employeeToModify.setDevices(employee.getDevices());
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
                employee.getEmployeeCode().length() == 10 &&
                checkEmployeeCode.codeExist(employee.getEmployeeCode(), employees) &&
                checkEmployeeCode.isLuhnNumber(employee.getEmployeeCode()) ;
    }

    @Override
    public void deleteEmployee(int id) {
        employees.remove(getEmployee(id));
    }

    @Override
    public Employee removeDevice(int id, String deviceName) {
        var removeDeviceFromEmployee = getEmployee(id);
        removeDeviceFromEmployee.getDevices().remove(deviceName);
        return getEmployee(id);
    }

    @Override
    public Employee addDevice(int id, String deviceName) {
        var addDeviceToEmployee = getEmployee(id);
        addDeviceToEmployee.getDevices().add(deviceName);
        return getEmployee(id);
    }

    @Override
    public EmployeeDevicesSummaryDto employeesNumberOfDevices() {
        var employeeDevicesMap = EmployeeDevicesSummaryDtoMapper.EmployeeDevices(employees);
        return employeeDevicesMap;
    }

    @Override
    public ArrayList<HiredEmployeeDto> employeesHired(){
        return HiredEmployeeDtoMapper.employeesHired(employees);
    }

    @Override
    public ArrayList<FullTimeEmployeesDto> fullTimeEmployees(){
        return FullTimeEmployeeDtoMapper.getFullTimeEmployees(employees);
    }


}
