package sudoku.validator;

public class ValidatorFactory {
    public Validator getValidator() {
        return new HashSetValidator();
    }
}
