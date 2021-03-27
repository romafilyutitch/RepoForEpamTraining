package by.epam.jwd.entity;

public abstract class Figure {
    private String name;

    public Figure(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract Point[] getPoints();

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Figure other = (Figure) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }
}