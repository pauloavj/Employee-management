package employee.employeemanagement.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeDevicesSummaryDto {
    //Full name of the employees
    // numbers of device
    private Map<String, Integer> employeeDevicesSummary;
}
