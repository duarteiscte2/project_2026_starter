package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Bomb implements ImageTile {
    private Point2D position;

    public Bomb(Point2D position) { this.position = position; }

    @Override
    public String getName() { return "bomb"; }

    @Override
    public Point2D getPosition() { return position; }

    @Override
    public int getLayer() { return 2; }

    public void setPosition(Point2D position) { this.position = position; }
}