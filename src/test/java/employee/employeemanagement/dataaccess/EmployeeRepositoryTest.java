package employee.employeemanagement.dataaccess;

import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.domain.Position;
import employee.employeemanagement.models.domain.WorkTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Employee testEmployee;

    @BeforeEach
    void setUp(){
//        employeeRepository = new EmployeeRepository();

        testEmployee = new Employee(
                "Test",
                "Test",
                "8072635082",
                LocalDate.of(1980, 02, 14),
                WorkTime.PartTime,
                Position.Tester,
                LocalDate.of(2021, 3, 31),
                new ArrayList<>() {{
                    add("Dell XPS 13");
                }}
        );
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
    void addEmployee_countEmployees_returnSize() {
        employeeRepository.addEmployee(testEmployee);
        int expectedTotalEmployees = 4;
        int actualNumberOfEmployees = employeeRepository.getAllEmployees().size();

        assertEquals(expectedTotalEmployees,actualNumberOfEmployees);
    }

    @Test
    void replaceEmployee() {
        employeeRepository.replaceEmployee(1,testEmployee);
        var expected = testEmployee.getEmployeeCode();

        var actual = employeeRepository.getEmployee(4).getEmployeeCode();
        assertEquals(expected,actual);
    }

    @Test
    void modifyEmployee_modifingEmployee_returnEmployeeCode() {
        testEmployee.setId(3);
        employeeRepository.modifyEmployee(3,testEmployee);
        var expected = testEmployee.getEmployeeCode();

        var actual = employeeRepository.getEmployee(3).getEmployeeCode();
        assertEquals(expected,actual);
    }

    @Test
    void isEmployeesEmpty() {
        var actual = employeeRepository.isEmployeesEmpty();

        assertFalse(actual);
    }

    @Test
    void employeeExists() {
        var actual = employeeRepository.employeeExists(1);
        assertTrue(actual);
    }

    @Test
    void isEmployeeValid() {

        var actual = employeeRepository.isEmployeeValid(testEmployee);

        assertFalse(actual);

    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void removeDevice() {
    }

    @Test
    void addDevice() {
    }

    @Test
    void employeesNumberOfDevices() {
    }

    @Test
    void employeesHired() {
    }

    @Test
    void fullTimeEmployees() {
    }
}