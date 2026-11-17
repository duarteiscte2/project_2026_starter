package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Wall implements ImageTile {
    private Point2D position;

    public Wall(Point2D position) {
        this.position = position;
    }

    @Override
    public String getName() { return "X"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 1; }
}