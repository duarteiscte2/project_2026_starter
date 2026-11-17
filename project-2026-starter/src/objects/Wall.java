package objects;

import pt.iscte.poo.utils.Point2D;


public class Wall extends StaticObject {
    public Wall(Point2D position){
        super(position);
    }
    @Override
    public String getName(){
        return "wall";
    }
    @Override
    public boolean isSolid(){
        return true;
    }

}
