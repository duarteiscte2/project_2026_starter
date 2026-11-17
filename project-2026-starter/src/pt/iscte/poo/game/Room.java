package pt.iscte.poo.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import objects.Baba;
import objects.Dirt;
import objects.GameObject;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

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

    public void addObject(ImageTile obj) {
        objects.add(obj);
    }

    public List<ImageTile> getObjects() {
        return objects;
    }

    public static Room readRoom(File f) {
        Room r = new Room();
        r.setName(f.getName());

        for (int i = 0; i <= 10; i++) {
            ImageTile dirt = new Dirt(new Point2D(i, i));
            r.addObject(dirt);
            ImageTile dirt2 = new Dirt(new Point2D(10 - i, i));
            r.addObject(dirt2);
        }

        Baba baba = new Baba(new Point2D(5, 5));
        r.addObject(baba);

        r.player = baba;
        return r;
    }

    public Baba getPlayer() {
        return player;
    }
}