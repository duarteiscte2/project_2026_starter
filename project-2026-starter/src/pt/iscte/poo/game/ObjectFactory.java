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
    public static GameObject createObject(String name, Point2D p, String direction) {
        switch (name) {
            case "baba":
                if (direction == null)
                    throw new IllegalArgumentException("Falta a direcao da Baba");
                return new Baba(p, parseDirection(direction));
            case "rock":     return new Rock(p);
            case "bomb":     return new Bomb(p);
            case "box":      return new Box(p);
            case "flag":     return new Flag(p);
            case "wordBox":  return new WordBox(p);
            case "wordIs":   return new WordIs(p);
            case "wordWin":  return new WordWin(p);
            case "wordMove": return new WordMove(p);
            case "wordStop": return new WordStop(p);
            default:
                throw new IllegalArgumentException("Objeto desconhecido: " + name);
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
