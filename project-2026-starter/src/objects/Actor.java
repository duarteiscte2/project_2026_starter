package objects;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.Room;


public abstract class Actor extends GameObject {
    private Direction facing;
    public Actor(Point2D position,Direction facing) {
        super(position);
        this.facing = facing;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }
    public boolean move(Direction d){
        Room room= getRoom();
        setFacing(d);
        Point2D next= getPosition().plus(d.asVector());
        if(room.hasGoalAt(next)){
            setPosition(next);
            return true;
        }
        if(!room.canEnter(next, d)){
            return false;
        }
        room.moveObject(this, d);
        return true;
    }

    @Override
    public int getLayer(){
        return 4;
    }
}
