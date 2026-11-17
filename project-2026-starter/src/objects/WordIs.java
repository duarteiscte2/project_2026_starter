package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class WordIs implements ImageTile {
    private Point2D position;

    public WordIs(Point2D position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "wordIs";
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public int getLayer() {
        return 3;
    }
}