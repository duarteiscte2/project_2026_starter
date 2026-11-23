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
    @Override
    public boolean isPushable(){
        return getRoom().hasRule("MOVE");
    }
    @Override
    public boolean isSolid(){
        return getRoom().hasRule("STOP");
    }
    @Override
    public boolean isGoal(){
        return getRoom().hasRule("WIN");
    }
}
