package by.epam.jwd.entity;

import java.util.Objects;

public class Line extends Figure{
    private Point startPoint;
    private Point endPoint;

    private Line(String name, Point startPoint, Point endPoint){
        super(name);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint(){
        return startPoint;
    }

    public Point getEndPoint(){
        return endPoint;
    }

    public static Line getInstance(String name, Point startPoint, Point endPoint){
        if(name == null || startPoint == null || endPoint == null){
            throw new IllegalArgumentException("Arguments must be not null");
        }
        return new Line(name, startPoint, endPoint);
    }

    @Override
    public Point[] getPoints(){
        return new Point[]{startPoint, endPoint};
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
        Line other = (Line) obj;
        return startPoint.equals(other.startPoint) && endPoint.equals(other.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startPoint, endPoint);
    }

    @Override
    public String toString(){
        return "Line { name = "+getName()+", startPoint = "+startPoint+", end Point = "+endPoint+"}";
    }
}