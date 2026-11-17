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
    private List<ImageTile> objects;
    private String roomName;
    private Baba player;

    public Room(String roomName) {
        this.roomName = roomName;
        this.objects = new ArrayList<>();
    }

    /**
     * Lê o ficheiro de configuração do nível (roomN.txt) e cria a sala correspondente.
     * @param file Ficheiro de configuração do nível.
     * @return Objeto Room preenchido com todos os elementos.
     */
    public static Room readRoom(File file) {
        Room room = new Room(file.getName());
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) return room;

            // 1. Ler dimensões do mapa: <cols>,<rows>[cite: 3]
            String[] dimensions = scanner.nextLine().split(",");
            int cols = Integer.parseInt(dimensions[0].trim());
            int rows = Integer.parseInt(dimensions[1].trim());

            // Colocar Dirt (chão) em todas as células do mapa por defeito[cite: 3]
            for (int x = 0; x < cols; x++) {
                for (int y = 0; y < rows; y++) {
                    room.objects.add(new Dirt(new Point2D(x, y)));
                }
            }

            // 2. Ler as 'rows' linhas seguintes com o mapa fixo do ambiente[cite: 3]
            int currentRow = 0;
            while (scanner.hasNextLine() && currentRow < rows) {
                String line = scanner.nextLine();
                if (line.isEmpty() && currentRow == 0) continue; // Ignorar linha vazia inicial se existir
                if (line.isEmpty()) break; // Linha em branco separa o mapa dos objetos

                for (int x = 0; x < line.length() && x < cols; x++) {
                    char c = line.charAt(x);
                    Point2D pos = new Point2D(x, currentRow);
                    switch (c) {
                        case 'X': // Parede[cite: 3]
                            room.objects.add(new Wall(pos));
                            break;
                        case 'x': // Parede rachada[cite: 3]
                            room.objects.add(new CrackedWall(pos));
                            break;
                        case 'H': // Buraco[cite: 3]
                            room.objects.add(new Hole(pos));
                            break;
                        case 'g': // Erva decorativa[cite: 3]
                            room.objects.add(new Grass(pos));
                            break;
                        default:
                            // Espaço vazio -> mantém apenas o Dirt de fundo[cite: 3]
                            break;
                    }
                }
                currentRow++;
            }

            // 3. Ler a lista de objetos do nível após a linha em branco[cite: 3]
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                String type = parts[0].trim();
                int x = Integer.parseInt(parts[1].trim());
                int y = Integer.parseInt(parts[2].trim());
                Point2D pos = new Point2D(x, y);

                ImageTile obj = null;
                switch (type) {
                    case "baba":
                        // O Baba pode ter direção opcional (ex: baba,8,6,r)[cite: 3]
                        room.player = new Baba(pos);
                        obj = room.player;
                        break;
                    case "rock":
                        obj = new Rock(pos);
                        break;
                    case "bomb":
                        obj = new Bomb(pos);
                        break;
                    case "box":
                        obj = new Box(pos);
                        break;
                    case "flag":
                        obj = new Flag(pos);
                        break;
                    case "wordBox":
                        obj = new WordBox(pos);
                        break;
                    case "wordIs":
                        obj = new WordIs(pos);
                        break;
                    case "wordStop":
                        obj = new WordStop(pos);
                        break;
                    case "wordMove":
                        obj = new WordMove(pos);
                        break;
                    case "wordWin":
                        obj = new WordWin(pos);
                        break;
                    default:
                        break;
                }
                if (obj != null) {
                    room.objects.add(obj);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return room;
    }

    public List<ImageTile> getObjects() {
        return objects;
    }

    public Baba getPlayer() {
        return player;
    }
}