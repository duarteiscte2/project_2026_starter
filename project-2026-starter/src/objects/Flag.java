package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Flag implements ImageTile {
    private Point2D position;

    public Flag(Point2D position) { this.position = position; }

    @Override
    public String getName() { return "flag"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 2; }
}
