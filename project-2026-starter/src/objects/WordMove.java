package objects;

import pt.iscte.poo.utils.Point2D;

public class WordMove extends PropertyWord{
    public WordMove(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "word-move";
    }
    @Override
    public String getProperty(){
        return "MOVE";
    }
}
