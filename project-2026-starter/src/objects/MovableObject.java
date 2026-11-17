package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class MovableObject extends GameObject{
    public MovableObject(Point2D position){
        super(position);
    }
    @Override
    public int getLayer(){
        return 2;
    }
}
