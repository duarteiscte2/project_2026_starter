package objects;

import pt.iscte.poo.utils.Point2D;

public class Box extends MovableObject{
    public Box(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "box";
    }
}
