package objects;

import pt.iscte.poo.utils.Point2D;

public class WordStop extends PropertyWord{
    public WordStop(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "word-stop";
    }
}
