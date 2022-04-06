package employee.employeemanagement.models.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckEmployeeCodeTest {

    private CheckEmployeeCode checkEmployeeCodeNumber;

    @BeforeEach
    void setUp(){
        checkEmployeeCodeNumber = new CheckEmployeeCode();
    }

    @Test
    public void luhnNumberLength_isTenDigit_returnBoolean() {
        String numberToTest = "1234567890";

        //act
        boolean actual = checkEmployeeCodeNumber.luhnNumberLength(numberToTest);

        //assert
        assertTrue(actual);
    }

    @Test
    public void isLuhnNumber_returnBoolean() {
        //9863003985 8072635082
        String employeeCodeToTest = "8072635082";

        //act
        boolean actual = checkEmployeeCodeNumber.isLuhnNumber(employeeCodeToTest);

        //assert
        assertTrue(actual);
    }


}