package employee.employeemanagement.models.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class HiredEmployeeDto {
    String employeeFullName;
    String employeeCode;
}
