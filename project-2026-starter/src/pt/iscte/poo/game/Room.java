//package pt.iscte.poo.game;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import objects.Baba;
//import objects.Dirt;
//import pt.iscte.poo.gui.ImageTile;
//import pt.iscte.poo.utils.Point2D;
//import java.util.Scanner;
//import java.io.IOException;
//
//public class Room {
//
//    private List<ImageTile> objects;
//    private String roomName;
//    private Baba player;
//
//    public Room() {
//        objects = new ArrayList<>();
//    }
//
//    private void setName(String name) {
//        roomName = name;
//    }
//
//    public String getName() {
//        return roomName;
//    }
//
//    public void addObject(ImageTile obj) {
//        objects.add(obj);
//    }
//
//    public List<ImageTile> getObjects() {
//        return objects;
//    }
//
//    public static Room readRoom(File f) throws IOException{
////        Room r = new Room();
////        r.setName(f.getName());
////
////        for (int i = 0; i <= 10; i++) {
////            ImageTile dirt = new Dirt(new Point2D(i, i));
////            r.addObject(dirt);
////            ImageTile dirt2 = new Dirt(new Point2D(10 - i, i));
////            r.addObject(dirt2);
////        }
////
////        Baba baba = new Baba(new Point2D(5, 5));
////        r.addObject(baba);
////
////        r.player = baba;
////        return r;
//
//    }
//
//    public Baba getPlayer() {
//        return player;
//    }
//}
package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.*;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {
    private List<GameObject> objects; // Ajustado para List<GameObject> conforme o UML
    private int width;
    private int height;
    private Baba player;

    public Room(String roomName) {
        this.objects = new ArrayList<>();
    }

    public static Room readRoom(File file) {
        Room room = new Room(file.getName());
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) return room;

            // 1. Ler dimensões
            String[] dimensions = scanner.nextLine().split(",");
            room.width = Integer.parseInt(dimensions[0].trim());
            room.height = Integer.parseInt(dimensions[1].trim());

            // Colocar Dirt (chão) em todas as células por defeito
            for (int x = 0; x < room.width; x++) {
                for (int y = 0; y < room.height; y++) {
                    room.objects.add(ObjectFactory.createFromSymbol(' ', new Point2D(x, y), room));
                }
            }

            // 2. Ler mapa fixo
            int currentRow = 0;
            while (scanner.hasNextLine() && currentRow < room.height) {
                String line = scanner.nextLine();
                if (line.isEmpty() && currentRow == 0) continue;
                if (line.isEmpty()) break;

                for (int x = 0; x < line.length() && x < room.width; x++) {
                    char c = line.charAt(x);
                    if (c != ' ') {
                        Point2D pos = new Point2D(x, currentRow);
                        GameObject obj = ObjectFactory.createFromSymbol(c, pos, room);
                        if (obj != null) room.objects.add(obj);
                    }
                }
                currentRow++;
            }

            // 3. Ler objetos dinâmicos e palavras
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                String name = parts[0].trim();
                int x = Integer.parseInt(parts[1].trim());
                int y = Integer.parseInt(parts[2].trim());
                Point2D pos = new Point2D(x, y);

                GameObject obj = ObjectFactory.createFromName(name, pos, room);
                if (obj != null) {
                    room.objects.add(obj);
                    if (obj instanceof Baba) {
                        room.player = (Baba) obj;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return room;
    }

    public List<GameObject> getObjects() {
        List<ImageTile> tiles = new ArrayList<>(objects);
        // Retorna a lista adaptada para o ImageGUI se necessário
        return objects;
    }

    public Baba getPlayer() {
        return player;
    }
}