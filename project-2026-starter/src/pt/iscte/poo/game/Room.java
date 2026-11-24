package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import objects.*;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import pt.iscte.poo.game.ObjectFactory;
import pt.iscte.poo.utils.Vector2D;


public class Room {

    private List<GameObject> objects=new ArrayList<>();
    private String roomName;
    private Baba player;
    private int width;
    private int height;
    private Set<String> rules= new HashSet<>();

    public Room(String roomName, int width, int height) {
        this.roomName = roomName;
        this.width = width;
        this.height = height;
    }
    private void setName(String name) {
        roomName = name;
    }

    public String getName() {
        return roomName;
    }

    public List<GameObject> getObjectsAt(Point2D p){
        List<GameObject> result= new ArrayList<>();
        for(GameObject o: objects){
            if(o.getPosition().equals(p))
                result.add(o);
        }
        return result;
    }
    public boolean hasSolidAt(Point2D p){
        for(GameObject o: getObjectsAt(p)){
            if(o.isSolid()){
                return true;
            }
        }
        return false;
    }

    public void addObject(GameObject obj) {
        if(obj instanceof Baba){
            if(player!=null){
                throw new IllegalArgumentException("ha mais que um baba");
            }
            player=(Baba)obj;
        }
        obj.setRoom(this);
        objects.add(obj);
    }

    public List<GameObject> getObjects() {
        return objects;
    }
    public boolean isInside(Point2D p){
        return p.getX()>=0&& p.getX()<width && p.getY()>=0 && p.getY()<height;
    }

    private static Room readHeader(String name,Scanner scanner){
        if(!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Ficheiro vazio");
        }
        String header= scanner.nextLine();
        String[] dims = header.split(",");
        if(dims.length!= 2 ){
            throw new IllegalArgumentException("header invalido");
        }
        int rows;
        int cols;
        try{
            cols= Integer.parseInt(dims[0].trim());
            rows= Integer.parseInt(dims[1].trim());
        }
        catch( NumberFormatException e ){
            throw new IllegalArgumentException("header invalido");
        }
        if(cols<=0 || rows<=0){
            throw new IllegalArgumentException("dimensoes invalidas");
        }
        return new Room( name, cols, rows);
    }

    public void readMap(Scanner scanner){
        for(int y=0; y<height;y++){
            if(!scanner.hasNextLine()){
                throw new IllegalArgumentException("mapa incompleto");
            }
            String linha = scanner.nextLine();
            for(int x=0; x<width; x++ ){
                Point2D ponto = new Point2D(x,y);
                addObject(new Dirt(ponto));
                char c  = linha.charAt(x);
                GameObject tile = ObjectFactory.createTile(c,ponto);
                if(tile!=null){
                    addObject(tile);
                }
            }
        }
    }
    private void readObjects(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (!linha.isEmpty()) {
                String[] parts = linha.split(",");
                if (parts.length < 3) {
                    throw new IllegalArgumentException("linha invalida: " + linha);
                }
                String name = parts[0].trim();
                Point2D p;
                try {
                    int x = Integer.parseInt(parts[1].trim());
                    int y = Integer.parseInt(parts[2].trim());
                    p = new Point2D(x, y);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("coordenadas invalidas: " + linha);
                }
                if (!isInside(p)) {
                    throw new IllegalArgumentException("objeto fora do mapa: " + linha);
                }

                String direction = null;
                if (parts.length >= 4) {
                    direction = parts[3].trim();
                }

                addObject(ObjectFactory.createObject(name, p, direction));
            }
        }
    }

    public static Room readRoom(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        Room room = readHeader(f.getName(), scanner);
        room.readMap(scanner);
        room.readObjects(scanner);
        scanner.close();
        return room;
    }

    public Baba getPlayer() {
        return player;
    }

    public boolean canEnter(Point2D p, Direction d) {
        if (!isInside(p))
            return false;
        for (GameObject o : getObjectsAt(p)) {
            if (isPushable(o)) {
                if (!((Pushable) o).canMove(d))
                    return false;
            } else if (o.isSolid()) {
                return false;
            }
        }
        return true;
    }
    private boolean isPushable(GameObject o) {
        return o instanceof Pushable && ((Pushable) o).isPushable();
    }

    public void moveObject(GameObject obj, Direction d){
        Point2D next= obj.getPosition().plus(d.asVector());
        for(GameObject o: getObjectsAt(next) ){
            if(isPushable(o)){
                ((Pushable)o).push(d);
            }
        }
        obj.setPosition(next);
    }
    public boolean hasGoalAt(Point2D p){
        for(GameObject o: getObjectsAt(p)){
            if(o.isGoal()){
                return true;
            }
        }
        return false;
    }
    public Word getWordAt(Point2D p){
        for(GameObject o: getObjectsAt(p) ){
            if(o instanceof Word){
                return (Word) o;
            }
        }
        return null;
    }
    public boolean isGoalReached(){
        return hasGoalAt(player.getPosition());
    }

    public void updateRules(){
        rules.clear();
        for(GameObject o: objects){
            if(o instanceof WordBox){
                checkRule(o.getPosition(),Direction.RIGHT.asVector());
                checkRule(o.getPosition(), Direction.DOWN.asVector());
            }

        }
    }

    private void checkRule(Point2D boxPosition,  Vector2D d){
        Point2D isPosition = boxPosition.plus(d);
        Point2D propertyPosition=isPosition.plus(d);
        Word is= getWordAt(isPosition);
        Word property = getWordAt(propertyPosition);
        if(is instanceof WordIs && property instanceof PropertyWord){
            rules.add(((PropertyWord) property).getProperty());
        }
    }

    public boolean hasRule(String property){
        return rules.contains(property);
    }

    public Set<String> getRules() {
        return rules;
    }
}