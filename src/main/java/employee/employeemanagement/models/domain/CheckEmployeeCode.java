package employee.employeemanagement.models.domain;

import employee.employeemanagement.dataaccess.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CheckEmployeeCode {

//    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    public CheckEmployeeCode(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    public boolean isLuhnNumber(String number){
        //check if value is 10 digits

        int[] arrayNumber = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            arrayNumber[i] = Integer.parseInt(String.valueOf(number.charAt(i)));
        }


        for (int i = 0; i < arrayNumber.length - 1; i+=2) {
            arrayNumber[i] = arrayNumber[i] * 2;
            if (arrayNumber[i] > 9){
                arrayNumber[i] = arrayNumber[i] % 10 + 1;
            }
        }


        return Arrays.stream(arrayNumber).sum() % 10 == 0;

    }
    public boolean codeExist(String number, List<Employee> employees ){
//        employeeRepository = new EmployeeRepository();
//        var employees = employeeRepository.getAllEmployees();
        for (Employee employee: employees) {
            if (employee.getEmployeeCode().equals(number))
                return false;
        }
        return true;
    }
}
