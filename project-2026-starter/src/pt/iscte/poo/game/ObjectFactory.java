package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class ObjectFactory {
    public static GameObject createTile(char c, Point2D p){
        switch (c){
            case 'X': return new Wall(p);
            case 'x': return new CrackedWall(p);
            case 'H': return new Hole(p);
            case 'g': return new Grass(p);
            case ' ': return null;
            default:
                throw new IllegalArgumentException("Caracter invalido");
        }
    }
    public static GameObject createObject(String linha){
        String[] parts= linha.split(",");
        if(parts.length<3){
            throw new IllegalArgumentException("linha fora");
        }
        String name= parts[0].trim();
        Point2D p;
        try{
            int x = Integer.parseInt(parts[1].trim());
            int y = Integer.parseInt(parts[2].trim());
            p= new Point2D(x,y);
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("coordenadas invalidas");
        }
        switch (name){
            case "baba":
                if(parts.length<4){
                    throw new IllegalArgumentException("falta a ddirecao");
                }
                return new Baba(p,parseDirection(parts[3].trim()));
            case "rock": return new Rock(p);
            case "bomb": return new Bomb(p);
            case "box": return new Box(p);
            case "flag": return new Flag(p);
            case "wordBox": return new WordBox(p);
            case "wordIs": return new WordIs(p);
            case "wordWin": return new WordWin(p);
            case "wordMove": return new WordMove(p);
            case "wordStop": return new WordStop(p);
            default:
                throw new IllegalArgumentException("objeto desconhecido");
        }
    }
    private static Direction parseDirection(String s){
        switch (s){
            case "u": return Direction.UP;
            case "d": return Direction.DOWN;
            case "l": return Direction.LEFT;
            case "r": return Direction.RIGHT;
            default:
                throw new IllegalArgumentException("Direcao invalida");
        }

    }
}
