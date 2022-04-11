package employee.employeemanagement.models.maps;

import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.domain.WorkTime;
import employee.employeemanagement.models.dto.FullTimeEmployeesDto;
import org.springframework.stereotype.Component;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class FullTimeEmployeeDtoMapper {
    public static ArrayList<FullTimeEmployeesDto> getFullTimeEmployees(List<Employee> employees){
        ArrayList<FullTimeEmployeesDto> fullTimeEmployees = new ArrayList<>();

        for (Employee employee: employees) {
            if(employee.getWorkTime().equals(WorkTime.FullTime)){
                var employeeName = employee.getFirstName();
                var employeeDateOfBirth = employee.getDateOfBirth();
                long daysToBirthday;

                var today = LocalDate.now();
                var birthday = employeeDateOfBirth.withYear(today.getYear());

                if(birthday.isBefore(today)){
                    var nextBirthday = birthday.plusYears(1);
                    daysToBirthday = ChronoUnit.DAYS.between(today,nextBirthday);
                }else {
                    daysToBirthday = ChronoUnit.DAYS.between(today, birthday);
                }
                FullTimeEmployeesDto fullTimeEmployeesDto = new FullTimeEmployeesDto(employeeName,employeeDateOfBirth,daysToBirthday);
                fullTimeEmployees.add(fullTimeEmployeesDto);
            }
        }
        return fullTimeEmployees;
    }
}
