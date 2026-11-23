package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.Room;

public abstract class MovableObject extends GameObject implements Pushable{
    public MovableObject(Point2D position){
        super(position);
    }
    @Override
    public boolean isPushable(){
        return true;
    }
    @Override
    public boolean canMove(Direction d){
        Point2D posicaoAtual= getPosition();
        Point2D NovaPosicao= posicaoAtual.plus(d.asVector());
        return getRoom().canEnter(NovaPosicao, d);
    }
    @Override
    public void push(Direction d){
        getRoom().moveObject(this, d);
    }
    @Override
    public int getLayer(){
        return 2;
    }
}
