package objects;

import pt.iscte.poo.utils.Point2D;

public class Rock extends MovableObject{
    public Rock(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "rock";
    }
}
