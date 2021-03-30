package by.epam.jwd.entity;

import java.util.Objects;

public class Square extends Figure{
    private Point firstVertex;
    private Point secondVertex;
    private Point thirdVertex;
    private Point fourthVertex;

    private Square(String name, Point firstVertex, Point secondVertex, Point thirdVertex, Point fourthVertex){
        super(name);
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.thirdVertex = thirdVertex;
        this.fourthVertex = fourthVertex;
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

    public Point getFourthVertex(){
        return fourthVertex;
    }

    public static Square newInstance(String name, Point firstVertex, Point secondVertex, Point thirdVertex, Point fourthVertex){
        if(name == null || firstVertex == null || secondVertex == null || thirdVertex == null || fourthVertex == null){
            throw new IllegalArgumentException("Arguments must be not null");
        }
        return new Square(name, firstVertex,secondVertex,thirdVertex,fourthVertex);
    }

    @Override
    public Point[] getPoints(){
        return new Point[]{firstVertex, secondVertex, thirdVertex, fourthVertex};
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        if(!super.equals(obj)){
            return false;
        }
        Square other = (Square) obj;
        return Objects.equals(firstVertex,other.firstVertex)
                && Objects.equals(secondVertex, other.secondVertex)
                && Objects.equals(thirdVertex, other.thirdVertex)
                && Objects.equals(fourthVertex, other.fourthVertex);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), firstVertex,secondVertex,thirdVertex, fourthVertex);
    }

    @Override
    public String toString(){
        return "Square { name = "+getName()
                +"first vertex = "+firstVertex
                +", second vertex = "+secondVertex
                +", third vertex = "+thirdVertex
                +", fourth vertext = "+fourthVertex;
    }
}