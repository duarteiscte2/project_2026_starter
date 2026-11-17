package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Rock implements ImageTile {
    private Point2D position;

    public Rock(Point2D position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "rock";
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public int getLayer() {
        return 2;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}