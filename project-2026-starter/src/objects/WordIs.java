package objects;

import pt.iscte.poo.utils.Point2D;

public class WordIs extends Word {
    public WordIs(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "word-is";
    }
}
