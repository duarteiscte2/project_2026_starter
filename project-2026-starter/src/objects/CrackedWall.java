package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class CrackedWall implements ImageTile {
    private Point2D position;

    public CrackedWall(Point2D position) { this.position = position; }

    @Override
    public String getName() { return "x"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 1; }
}
