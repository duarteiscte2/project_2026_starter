package objects;

import pt.iscte.poo.utils.Point2D;

public class Bomb extends MovableObject{
    public Bomb(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "bomb";
    }
}
