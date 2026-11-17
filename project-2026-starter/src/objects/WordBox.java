package objects;

import pt.iscte.poo.utils.Point2D;

public class WordBox extends Word{
    public WordBox(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "word-box";
    }
}
