package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.Baba;
import objects.Dirt;
import objects.GameObject;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

import pt.iscte.poo.game.ObjectFactory;

public class Room {

    private List<GameObject> objects=new ArrayList<>();
    private String roomName;
    private Baba player;
    private int width;
    private int height;

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
    public void readObjects(Scanner scanner){
        while(scanner.hasNextLine()){
            String linha = scanner.nextLine().trim();
            if(!linha.isEmpty()){
                GameObject obj= ObjectFactory.createObject(linha);
                if(! isInside(obj.getPosition())){
                    throw new IllegalArgumentException("objeto fora do mapa");
                }
                addObject(obj);
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
}