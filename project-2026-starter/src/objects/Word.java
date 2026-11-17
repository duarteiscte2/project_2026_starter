package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class Word extends GameObject{
    public Word(Point2D position){
        super(position);
    }
    @Override
    public int getLayer(){
        return 3;
    }
    @Override
    public boolean isSolid(){
        return true;
    }

}
