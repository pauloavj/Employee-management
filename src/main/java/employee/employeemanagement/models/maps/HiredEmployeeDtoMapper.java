package employee.employeemanagement.models.maps;

import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.dto.HiredEmployeeDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HiredEmployeeDtoMapper {

    public static ArrayList<HiredEmployeeDto> employeesHired(List<Employee> employees){
        ArrayList<HiredEmployeeDto> newEmployees = new ArrayList<>();
        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
//        HiredEmployeesDto employeeHired = null;
        for (Employee employee: employees) {
            if(twoYearsAgo.isBefore(employee.getDateHired())){
                var employeeCode = employee.getEmployeeCode();
                var employeeFullName = employee.getFirstName() + " " + employee.getLastName();
                HiredEmployeeDto newEmployee = new  HiredEmployeeDto(employeeFullName,employeeCode);
                newEmployees.add(newEmployee);
            }
        }
        return newEmployees;
    }

}
