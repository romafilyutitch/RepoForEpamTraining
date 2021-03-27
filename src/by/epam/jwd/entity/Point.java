package by.epam.jwd.entity;

import java.util.Arrays;
import java.util.Objects;

public class Point {
    private static final int GROWTH_FACTOR = 16;
    private static Point[] pointsCache = new Point[16];
    private static int cachedPointsAmount = 0;
    private int x;
    private int y;

    private Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    @Override
    public String toString(){
        return "Point { x = "+x+", y = "+y+"}";
    }

    public static Point getInstance(int x, int y){
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("arguments must be positive");
        }
        Point newPoint = null;
        for(int i = 0; i < cachedPointsAmount; i++){
            Point cachedElement = pointsCache[i];
            if(cachedElement.getX() == x && cachedElement.getY() == y){
                newPoint = cachedElement;
            }
        }
        if(newPoint == null){
            newPoint = new Point(x,y);
            if(cachedPointsAmount == pointsCache.length){
                pointsCache = Arrays.copyOf(pointsCache, pointsCache.length + GROWTH_FACTOR);
            }
            pointsCache[cachedPointsAmount] = newPoint;
            cachedPointsAmount++;
        }
        return newPoint;
    }
}