package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Box implements ImageTile {
    private Point2D position;

    public Box(Point2D position) { this.position = position; }

    @Override
    public String getName() { return "box"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 2; }

    public void setPosition(Point2D position) { this.position = position; }
}
