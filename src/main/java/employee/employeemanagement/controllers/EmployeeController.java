package employee.employeemanagement.controllers;

import employee.employeemanagement.dataaccess.IEmployeeRepository;
import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.dto.EmployeeDevicesSummaryDto;
import employee.employeemanagement.models.dto.FullTimeEmployeesDto;
import employee.employeemanagement.models.dto.HiredEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees(){
        if (employeeRepository.isEmployeesEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeRepository.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id){
        if (employeeRepository.employeeExists(id))
            return new ResponseEntity<>(employeeRepository.getEmployee(id), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        if (employeeRepository.isEmployeeValid(employee))
            return new ResponseEntity<>(employeeRepository.addEmployee(employee), HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> replaceEmployee(@PathVariable int id, @RequestBody Employee employee){
        if (!employeeRepository.employeeExists(id))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if (!employeeRepository.isEmployeeValid(employee))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(employeeRepository.replaceEmployee(id,employee), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable int id, @RequestBody Employee employee){
        if (!employeeRepository.employeeExists(id))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if (!employeeRepository.isEmployeeValid(employee))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(employeeRepository.modifyEmployee(id, employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        if(employeeRepository.employeeExists(id)) {
            employeeRepository.deleteEmployee(id);
            return new ResponseEntity<>("Employee with ID " + id + " has been removed", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/removeDevice/{id}")
    public ResponseEntity<Employee> removeDevice(@PathVariable int id, @RequestBody String deviceName){
        if (!employeeRepository.employeeExists(id))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeRepository.removeDevice(id, deviceName), HttpStatus.CREATED);
    }

    @PatchMapping("/addDevice/{id}")
    public ResponseEntity<Employee> addDevice(@PathVariable int id, @RequestBody String deviceName){
        if (!employeeRepository.employeeExists(id))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeRepository.addDevice(id, deviceName), HttpStatus.CREATED);
    }

    @GetMapping("/devices/summary")
    public ResponseEntity<EmployeeDevicesSummaryDto> employeesNumberOfDevices(){
        return new ResponseEntity<>(employeeRepository.employeesNumberOfDevices(), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<ArrayList<HiredEmployeeDto>> employeesHired(){
        return new ResponseEntity<>(employeeRepository.employeesHired(), HttpStatus.OK);
    }

    @GetMapping("/birthdays")
    public ResponseEntity<ArrayList<FullTimeEmployeesDto>> fullTimeEmployees(){
        return  new ResponseEntity<>(employeeRepository.fullTimeEmployees(),HttpStatus.OK);
    }
}
