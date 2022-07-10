package pl.dawid.app.validators;

import pl.dawid.app.exception.ValidationException;

import java.sql.Date;

public class DataValidators {
    private static final String DATA_CAN_NOT_BE_NULL = "Date can not be null.";
    private static final String DATA_SHOULD_BE_PAST = "Date should be past.";
    private static final String DATA_SHOULD_BE_FEATURE = "Date should be feature.";

    public static boolean validateDateIsNotNull(Date test, String fieldName) {
        if (test == null) {
            throw new ValidationException(fieldName + " - " + DATA_CAN_NOT_BE_NULL);
        }
        return true;
    }

    public static boolean validateDateIsBeforeOrEqual(Date test, Date target, String fieldName) {
        DataValidators.validateDateIsNotNull(test, fieldName);
        DataValidators.validateDateIsNotNull(target, fieldName);
        if (!(test.compareTo(target) <= 0)) {
            throw new ValidationException(fieldName + " - " + DATA_SHOULD_BE_PAST);
        }
        return true;
    }

    public static boolean validateDateIsAfterOrEqual(Date test, Date target, String fieldName) {
        DataValidators.validateDateIsNotNull(test, fieldName);
        DataValidators.validateDateIsNotNull(target, fieldName);
        if (!(test.compareTo(target) >= 0)) {
            throw new ValidationException(fieldName + " - " + DATA_SHOULD_BE_FEATURE);
        }
        return true;
    }
}
