
package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;

import objects.Baba;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {

    private static GameEngine INSTANCE = new GameEngine();
    private static final String ROOMS_DIRECTORY = "rooms";

    private Room currentRoom;
    private int currentLevel;
    private int moves;
    private int lastTickProcessed = 0;
    private boolean levelCompleted=false;

    private GameEngine() {
        try {
            currentRoom = Room.readRoom(new File(ROOMS_DIRECTORY, "room3.txt"));
        } catch (FileNotFoundException e) {
            ImageGUI.getInstance().showMessage("Erro", "Nivel 0 nao encontrado");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            ImageGUI.getInstance().showMessage("Erro", "Erro no nivel 0: " + e.getMessage());
            System.exit(1);
        }
        currentLevel = 0;
        moves = 0;
        updateGUI();
        updateStatus();
    }

    public static GameEngine getInstance() {
        return INSTANCE;
    }

    @Override
    public void update(Observed source) {
        int t = ImageGUI.getInstance().getTicks();
        if (t > lastTickProcessed) {
            while (lastTickProcessed < t) {
                processTick();
            }
        } else if (ImageGUI.getInstance().wasKeyPressed() && !levelCompleted) {
            int k = ImageGUI.getInstance().keyPressed();
            if (Direction.isDirection(k)) {
                Direction d = Direction.directionFor(k);
                if (getPlayer().move(d)) {
                    moves++;
                    currentRoom.updateRules();
                    updateStatus();
                    checkGoal();
                }
            }
        }
        ImageGUI.getInstance().update();
    }

    private void processTick() {
        lastTickProcessed++;
    }

    private void updateGUI() {
        ImageGUI.getInstance().clearImages();
        ImageGUI.getInstance().addImages(currentRoom.getObjects());
    }

    private void updateStatus() {
        String rules= "";
        for(String r: currentRoom.getRules()){
            rules+= "  BOX IS " + r;
        }
        if(rules.isEmpty())
            rules="  nenhuma";
        ImageGUI.getInstance().setStatusMessage("Nivel " + currentLevel + "   |   Movimentos: " + moves);
    }

    public Baba getPlayer() {
        return currentRoom.getPlayer();
    }

    private void checkGoal(){
        if(currentRoom.isGoalReached()){
            levelCompleted=true;
            ImageGUI.getInstance().setStatusMessage("NIVEL " +currentLevel+ " concluido em " + moves +" movimentos!");
            ImageGUI.getInstance().showMessage("Parabens ","NIVEL "+ currentLevel+ " concluido em " + moves +" movimentos!");
        }
    }

}
