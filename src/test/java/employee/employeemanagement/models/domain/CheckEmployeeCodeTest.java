package employee.employeemanagement.models.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckEmployeeCodeTest {

    private CheckEmployeeCode checkEmployeeCodeNumber;
//
//    @BeforeEach
//    void setUp(){
//        checkEmployeeCodeNumber = new CheckEmployeeCode();
//    }

    @Test
    void isLuhnNumber_getStringCode_returnBoolean() {
        //9863003985 8072635082
        String employeeCodeToTest = "8072635082";

        //act
        boolean actual = checkEmployeeCodeNumber.isLuhnNumber(employeeCodeToTest);

        //assert
        assertTrue(actual);
    }


//    @Test
//    void codeExist() {
//        //1634751505
//        String testCode = "1634751505";
//        boolean actual = checkEmployeeCodeNumber.codeExist(testCode);
//
//        assertFalse(actual);
//
//    }
}