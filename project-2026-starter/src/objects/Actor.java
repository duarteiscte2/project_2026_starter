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
        setFacing(d);
        Point2D next= getPosition().plus(d.asVector());
        if(!canEnter(next)){
            return false;
        }
        setPosition(next);
        return true;
    }
    public boolean canEnter(Point2D p){
        Room room = getRoom();
        return room.isInside(p) && room.hasSolidAt(p);
    }
    @Override
    public int getLayer(){
        return 4;
    }
}
