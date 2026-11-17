package objects;

import pt.iscte.poo.utils.Point2D;

public class Flag extends MovableObject{
    public Flag(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "flag";
    }
}
