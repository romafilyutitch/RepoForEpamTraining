package by.epam.jwd.service.impl;

import by.epam.jwd.model.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class Criteria {
    private final Collection<Predicate<Figure>> predicates = new ArrayList<>();

    private void addPredicate(Predicate<Figure> newPredicate) {
        predicates.add(newPredicate);
    }

    public Collection<Predicate<Figure>> getCriteriaPredicates() {
        return predicates;
    }

    public static class Builder {
        private final Criteria criteria;

        public Builder() {
            criteria = new Criteria();
        }

        public Builder whereIdEquals(long id) {
            criteria.addPredicate(figure -> figure.getId() == id);
            return this;
        }

        public Builder whereIdMoreThan(long id) {
            criteria.addPredicate(figure -> figure.getId() > id);
            return this;
        }

        public Builder whereIdLessThan(long id) {
            criteria.addPredicate(figure -> figure.getId() < id);
            return this;
        }

        public Builder whereSquareEquals(double square) {
            criteria.addPredicate(figure -> figure.getSquare() == square);
            return this;
        }

        public Builder whereSquareMoreThan(double square) {
            criteria.addPredicate(figure -> figure.getSquare() > square);
            return this;
        }

        public Builder whereSquareLessThan(double square) {
            criteria.addPredicate(figure -> figure.getSquare() < square);
            return this;
        }

        public Builder wherePerimeterEquals(double perimeter) {
            criteria.addPredicate(figure -> figure.getPerimeter() == perimeter);
            return this;
        }

        public Builder wherePerimeterMoreThan(double perimeter) {
            criteria.addPredicate(figure -> figure.getPerimeter() > perimeter);
            return this;
        }


        public Builder wherePerimeterLessThan(double perimeter) {
            criteria.addPredicate(figure -> figure.getPerimeter() < perimeter);
            return this;
        }

        public Builder whereFigureTypeStartsWith(String start) {
            criteria.addPredicate(figure -> figure.getClass().getSimpleName().startsWith(start));
            return this;
        }

        public Builder whereFigureTypeEndsWith(String end) {
            criteria.addPredicate(figure -> figure.getClass().getSimpleName().endsWith(end));
            return this;
        }

        public Builder whereFigureTypeLike(String regex) {
            criteria.addPredicate(figure -> figure.getClass().getSimpleName().matches(regex));
            return this;
        }

        public Criteria build() {
            return criteria;
        }
    }
}
