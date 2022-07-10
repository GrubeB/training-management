package pl.dawid.app.validators;

import pl.dawid.app.exception.ValidationException;

public class NumberValidators {
    private static final String NUMBER_CAN_NOT_BE_NULL = "Number can not be null.";
    private static final String NUMBER_SHOULD_BE_POSITIVE = "Number should be positive.";
    private static final String NUMBER_SHOULD_BE_POSITIVE_OR_ZERO = "Number should be positive or zero.";
    private static final String NUMBER_SHOULD_BE_NEGATIVE = "Number should be negative.";
    private static final String NUMBER_SHOULD_BE_NEGATIVE_OR_ZERO = "Number should be negative or zero.";

    public static boolean validateNumberIsNotNull(Number test, String fieldName) {
        if (test == null) {
            throw new ValidationException(fieldName + " - " + NUMBER_CAN_NOT_BE_NULL);
        }
        return true;
    }

    public static boolean validateNumberIsPositive(Number test, String fieldName) {
        NumberValidators.validateNumberIsNotNull(test, fieldName);
        if (!(test.doubleValue() > 0)) {
            throw new ValidationException(fieldName + " - " + NUMBER_SHOULD_BE_POSITIVE);
        }
        return true;
    }

    public static boolean validateNumberIsPositiveOrZero(Number test, String fieldName) {
        NumberValidators.validateNumberIsNotNull(test, fieldName);
        if (!(test.doubleValue() >= 0)) {
            throw new ValidationException(fieldName + " - " + NUMBER_SHOULD_BE_POSITIVE_OR_ZERO);
        }
        return true;
    }

    public static boolean validateNumberIsNegative(Number test, String fieldName) {
        NumberValidators.validateNumberIsNotNull(test, fieldName);
        if (!(test.doubleValue() < 0)) {
            throw new ValidationException(fieldName + " - " + NUMBER_SHOULD_BE_NEGATIVE);
        }
        return true;
    }

    public static boolean validateNumberIsNegativeOrZero(Number test, String fieldName) {
        NumberValidators.validateNumberIsNotNull(test, fieldName);
        if (!(test.doubleValue() <= 0)) {
            throw new ValidationException(fieldName + " - " + NUMBER_SHOULD_BE_NEGATIVE_OR_ZERO);
        }
        return true;
    }
}
