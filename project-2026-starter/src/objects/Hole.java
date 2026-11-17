package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Hole implements ImageTile {
    private Point2D position;

    public Hole(Point2D position) { this.position = position; }

    @Override
    public String getName() { return "H"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 1; }
}