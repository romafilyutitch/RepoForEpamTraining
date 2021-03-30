package by.epam.jwd.validation;

public class SimpleValidationStrategyFactory implements ValidationStrategyFactory {

    private SimpleValidationStrategyFactory(){}

    public static SimpleValidationStrategyFactory newInstance(){
        return new SimpleValidationStrategyFactory();
    }

    @Override
    public ValidationStrategy getValidationStrategy(Figure figure) {
        switch (figure){
            case LINE: return new LineValidationStrategy();
            case TRIANGLE: return new TriangleValidationStrategy();
            case SQUARE: return new SquareValidationStrategy();
            default: throw new EnumConstantNotPresentException(figure.getDeclaringClass(), figure.name());
        }
    }
}
