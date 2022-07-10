package pl.dawid.app.validators;

import pl.dawid.app.exception.ValidationException;

import java.sql.Time;

public class TimeValidators {
    private static final String TIME_CAN_NOT_BE_NULL = "Time can not be null.";
    public static boolean validateTimeIsNotNull(Time test, String fieldName){
        if (test == null ) {
            throw new ValidationException(fieldName+" - "+TIME_CAN_NOT_BE_NULL);
        }
        return true;
    }
}
