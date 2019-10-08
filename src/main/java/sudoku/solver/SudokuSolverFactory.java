package sudoku.solver;

import sudoku.strategy.EliminationStrategy;
import sudoku.strategy.ScanningStrategy;
import sudoku.strategy.OnlyOptionStrategy;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

import java.util.Arrays;

public class SudokuSolverFactory {
    public SudokuSolver getSolver() {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy[] stepSolvingStrategies = {
                new OnlyOptionStrategy(),
                new ScanningStrategy(),
                new EliminationStrategy()
        };

        return new LoopingSolver(validator, stepSolvingStrategies);
    }
}
