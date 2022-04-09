package employee.employeemanagement.models.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class FullTimeEmployeesDto {
    String firstName;
    LocalDate dateOfBirth;
    long remainingDaysToBirthday;
}
