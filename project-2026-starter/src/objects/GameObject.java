package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameObject implements ImageTile {
    private Point2D position;
    private Room room;
    public GameObject(Point2D position){
        this.position=position;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public boolean isSolid(){
        return false;
    }
    public boolean isGoal(){
        return false;
    }

}
