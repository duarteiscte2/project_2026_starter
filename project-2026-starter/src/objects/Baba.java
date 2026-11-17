package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.Random;

public class Baba extends Actor {

    public Baba(Point2D position, Direction facing) {
        super(position, facing);
    }

    @Override
    public String getName() {
        return "baba-" + letterFor(getFacing());
    }
    //Uso de IA claude  oppus5.5 para fazer o metodo letterFor
    private char letterFor(Direction d){
        switch (d) {
            case UP:    return 'u';
            case DOWN:  return 'd';
            case LEFT:  return 'l';
            default:    return 'r';
        }
    }
}
