package employee.employeemanagement.models.maps;

import employee.employeemanagement.models.domain.Employee;
import employee.employeemanagement.models.dto.EmployeeDevicesSummaryDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDevicesSummaryDtoMapper {

    public static EmployeeDevicesSummaryDto EmployeeDevices(List<Employee> employees){
        Map<String, Integer> employeeDevicesMap = new HashMap<>();

        for (Employee employee: employees) {
            var fullName = employee.getFirstName() + " " + employee.getLastName();
            var devices = employee.getDevices().size();
            employeeDevicesMap.put(fullName,devices);
        }
        EmployeeDevicesSummaryDto employeeDevicesSummaryDto = new EmployeeDevicesSummaryDto(employeeDevicesMap);

        return employeeDevicesSummaryDto;
    }
}
