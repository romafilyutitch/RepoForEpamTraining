package by.epam.jwd.entity;

import java.util.Objects;

public class Triangle extends Figure {
    private Point firstVertex;
    private Point secondVertex;
    private Point thirdVertex;

    private Triangle(String name, Point firstVertex, Point secondVertex, Point thirdVertex){
        super(name);
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.thirdVertex = thirdVertex;
    }

    public Point getFirstVertex(){
        return firstVertex;
    }

    public Point getSecondVertex(){
        return secondVertex;
    }

    public Point getThirdVertex(){
        return thirdVertex;
    }

    public static Triangle newInstance(String name, Point firstVertex, Point secondVertex, Point thirdVertex){
        if(name == null || firstVertex == null || secondVertex == null || thirdVertex == null){
            throw new IllegalArgumentException("Arguments must be not null");
        }
        return new Triangle(name,firstVertex,secondVertex,thirdVertex);
    }

    @Override
    public Point[] getPoints(){
        return new Point[]{firstVertex, secondVertex, thirdVertex};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Objects.equals(firstVertex, triangle.firstVertex)
                && Objects.equals(secondVertex, triangle.secondVertex)
                && Objects.equals(thirdVertex, triangle.thirdVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstVertex, secondVertex, thirdVertex);
    }

    @Override
    public String toString(){
        return "Triangle { name = "+getName()+", first vertext = "+firstVertex
                +", second vertext = "+secondVertex
                +", third vertex = "+thirdVertex+"}";
    }
}
