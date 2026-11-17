package objects;

import pt.iscte.poo.utils.Point2D;

public class Hole extends StaticObject{
    public Hole(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "hole";
    }
}
