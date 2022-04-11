package employee.employeemanagement.dataaccess;

import employee.employeemanagement.models.domain.CheckEmployeeCode;
import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.domain.Position;
import employee.employeemanagement.models.domain.WorkTime;
import employee.employeemanagement.models.maps.HiredEmployeeDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    EmployeeRepository employeeRepository;
    Employee testEmployee;

    @BeforeEach
    void setUp(){
        employeeRepository = new EmployeeRepository(
                new HiredEmployeeDtoMapper(),
                new CheckEmployeeCode());

        testEmployee = new Employee(
                "John",
                "Doe",
                "9863003985",
                LocalDate.of(1980,02,14),
                WorkTime.PartTime,
                Position.Tester,
                LocalDate.of(2021,3,31),
                new ArrayList<>(){{add("Dell XPS 13");
                }}
        );
        testEmployee.reset();
    }

    @Test
    void getAllEmployees_countAllEmployees_returnNumberOfEmployees() {
        int expected = 3;
        int actual =  employeeRepository.getAllEmployees().size();

        assertEquals(expected,actual);
    }

    @Test
    void getEmployee_checkEmployeeUniqueCode_returnStringCode() {
        String expected = "2018269734";
        String actual = employeeRepository.getEmployee(1).getEmployeeCode();

        assertEquals(expected,actual);
    }

    @Test
    void employeeExists_checkingEmployeeInList_returnBoolean() {
        System.out.println(employeeRepository.getEmployee(1).getId());
        var actual = employeeRepository.employeeExists(1);
        assertTrue(actual);
    }

    @Test
    void addEmployee_countEmployees_returnSize() {
        employeeRepository.addEmployee(testEmployee);
        int expectedTotalEmployees = 4;
        int actualNumberOfEmployees = employeeRepository.getAllEmployees().size();

        assertEquals(expectedTotalEmployees,actualNumberOfEmployees);
    }

    @Test
    void replaceEmployee() {

        employeeRepository.replaceEmployee(2,testEmployee);
        var expected = testEmployee.getEmployeeCode();

        var actual = employeeRepository.getEmployee(4).getEmployeeCode();
        assertEquals(expected,actual);
    }

    @Test
    void modifyEmployee_modifyingEmployee_returnEmployeeCode() {
        testEmployee.setId(3);
        employeeRepository.modifyEmployee(3,testEmployee);
        var expected = testEmployee.getEmployeeCode();

        var actual = employeeRepository.getEmployee(3).getEmployeeCode();
        assertEquals(expected,actual);
    }

    @Test
    void isEmployeesEmpty_IfEmployeesListEmpty_returnBoolean() {
        var actual = employeeRepository.isEmployeesEmpty();

        assertFalse(actual);
    }

    @Test
    void employeeExists_checkIfIdEmployeeIsInTheList_returnBoolean() {
        var actual = employeeRepository.employeeExists(1);
        assertTrue(actual);
    }

    @Test
    void isEmployeeValid_checkAllTheConditions_returnBoolean() {

        var actual = employeeRepository.isEmployeeValid(testEmployee);

        assertTrue(actual);

    }

    @Test
    void codeExists_checkIfCodeIsUnique_returnBoolean(){
        var actual = employeeRepository.codeExists(testEmployee.getEmployeeCode());
        assertTrue(actual);
    }

    @Test
    void deleteEmployee_removeFromRepo_returnBoolean() {
        employeeRepository.deleteEmployee(1);

        var actual = employeeRepository.employeeExists(1);

        assertFalse(actual);
    }

    @Test
    void removeDevice_removeDeviceFromEmployee_ReturnBoolean() {
        employeeRepository.removeDevice(1,"Dell XPS 13");
        var actual = employeeRepository.getEmployee(1).getDevices().isEmpty();

        assertTrue(actual);
    }

    @Test
    void addDevice() {
        employeeRepository.addDevice(1, "test monitor");
        String expected = "test monitor";

        var actual = employeeRepository.getEmployee(1).getDevices().get(1);
        assertEquals(expected,actual);
    }

    @Test
    void employeesNumberOfDevices() {
        var expected = 1;

        var actual = employeeRepository.employeesNumberOfDevices().getEmployeeDevicesSummary().get("John Doe");

        assertEquals(expected,actual);
    }

    @Test
    void employeesHired() {
        var expected = "2018269734";

        var actual = employeeRepository.employeesHired().get(0).getEmployeeCode();

        assertEquals(expected,actual);
    }

    @Test
    void fullTimeEmployees() {
        var expected = 2;

        var actual = employeeRepository.fullTimeEmployees().size();

        assertEquals(expected,actual);

    }
}