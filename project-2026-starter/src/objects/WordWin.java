package objects;

import pt.iscte.poo.utils.Point2D;

public class WordWin extends PropertyWord{
    public WordWin(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "word-win";
    }
}
