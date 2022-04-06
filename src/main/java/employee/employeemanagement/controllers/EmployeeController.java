package employee.employeemanagement.controllers;

import employee.employeemanagement.dataaccess.IEmployeeRepository;
import employee.employeemanagement.models.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeRepository employeeRepository;

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

}
