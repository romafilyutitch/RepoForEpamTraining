package by.epam.jwd.factory;

public enum FigureType {
    LINE(2), TRIANGLE(3), SQUARE(4), MULTI_ANGLE_FIGURE(5);
    private int amountOfPoints;

    FigureType(int amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    public int getAmountOfPoints() {
        return amountOfPoints;
    }
}
