package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class Word extends GameObject implements Pushable{
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
    @Override
    public boolean isPushable(){
        return true;
    }
    @Override
    public boolean canMove(Direction d){
        Point2D next= getPosition().plus(d.asVector());
        if(getRoom().getWordAt(next)!=null){
            return false;
        }
        return getRoom().canEnter(next, d);
    }
    @Override
    public void push(Direction d){
        getRoom().moveObject(this, d);
    }


}
