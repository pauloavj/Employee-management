package employee.employeemanagement.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HiredEmployeeDto {
    private String employeeFullName;
    private String employeeCode;
}
