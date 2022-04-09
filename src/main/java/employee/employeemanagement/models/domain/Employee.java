package employee.employeemanagement.models.domain;

//import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
//@AllArgsConstructor
public class Employee {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String firstName;
    private String lastName;
    private String employeeCode;
    private LocalDate dateOfBirth;
    private WorkTime workTime;
    private Position position;
    private LocalDate dateHired;
    private ArrayList<String> devices;


    public Employee(String firstName,
                    String lastName,
                    String employeeCode,
                    LocalDate dateOfBirth,
                    WorkTime workTime,
                    Position position,
                    LocalDate dateHired,
                    ArrayList<String> devices) {
        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeCode = employeeCode;
        this.dateOfBirth = dateOfBirth;
        this.workTime = workTime;
        this.position = position;
        this.dateHired = dateHired;
        this.devices = devices;
    }
}
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmployeeCode() {
//        return employeeCode;
//    }
//
//    public void setEmployeeCode(String employeeCode) {
//        this.employeeCode = employeeCode;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public WorkTime getWorkTime() {
//        return workTime;
//    }
//
//    public void setWorkTime(WorkTime workTime) {
//        this.workTime = workTime;
//    }
//
//    public Position getPosition() {
//        return position;
//    }
//
//    public void setPosition(Position position) {
//        this.position = position;
//    }
//
//    public LocalDate getDateHired() {
//        return dateHired;
//    }
//
//    public void setDateHired(LocalDate dateHired) {
//        this.dateHired = dateHired;
//    }
//
//    public ArrayList<String> getDevices() {
//        return devices;
//    }
//
//    public void setDevices(ArrayList<String> devices) {
//        this.devices = devices;
//    }
//}
