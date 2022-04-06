package employee.employeemanagement.models.domain;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String firstName;
    private String lastName;
    private String employeeCode;
    private LocalDate dateOfBirth;
    private boolean isFullTime;
    private Position position;
    private LocalDate dateHired;

    public Employee(String firstName, String lastName, String employeeCode, LocalDate dateOfBirth, boolean isFullTime, Position position, LocalDate dateHired) {
        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeCode = employeeCode;
        this.dateOfBirth = dateOfBirth;
        this.isFullTime = isFullTime;
        this.position = position;
        this.dateHired = dateHired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }
}
