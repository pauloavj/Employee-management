package employee.employeemanagement.models.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CheckEmployeeCode {

    public boolean luhnNumberLength(String luhnNumber){
        //check if value is 10 digits
        return luhnNumber.length() == 10;

    }
    public boolean isLuhnNumber(String number){
        //check if value is 10 digits
        if (!luhnNumberLength(number))
            return false;

        int[] arrayNumber = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            arrayNumber[i] = Integer.parseInt(String.valueOf(number.charAt(i)));
        }


        for (int i = 0; i < arrayNumber.length - 1; i+=2) {
            arrayNumber[i] = arrayNumber[i] * 2;
            if (arrayNumber[i] > 9){
                arrayNumber[i] = arrayNumber[i] % 10 + 1;
            }
        }


        return Arrays.stream(arrayNumber).sum() % 10 == 0;

    }
}
