package com.trifluxgaming.game.util;

import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.entities.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    public boolean[] keyDown = new boolean[10];

    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        keyDown[4] = false;
        keyDown[5] = false;
        keyDown[6] = false;
        keyDown[7] = false;
        keyDown[8] = false;
        keyDown[9] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                //key events for player 1
                if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[2] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[3] = true; }

                if(key == KeyEvent.VK_UP) { tempObject.setVelY(-5); keyDown[4] = true;}
                if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(5); keyDown[5] = true; }
                if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(5); keyDown[6] = true; }
                if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5); keyDown[7] = true; }
            }
        }

        if(key == KeyEvent.VK_P){
            for(int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                tempObject.pause();
                Main.run.hud.gamePaused = true;
                Main.run.spawner.spawnPaused = true;
            }
            keyDown[8] = true;
        }else if(key == KeyEvent.VK_ESCAPE){
            for(int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                tempObject.resume();
                Main.run.hud.gamePaused = false;
                Main.run.spawner.spawnPaused = false;
            }
            keyDown[9] = true;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //key events for player 1
                if(key == KeyEvent.VK_W) keyDown[0] = false;
                if(key == KeyEvent.VK_S) keyDown[1] = false;
                if(key == KeyEvent.VK_D) keyDown[2] = false;
                if(key == KeyEvent.VK_A) keyDown[3] = false;

                if(key == KeyEvent.VK_UP) keyDown[4] = false;
                if(key == KeyEvent.VK_DOWN) keyDown[5] = false;
                if(key == KeyEvent.VK_RIGHT) keyDown[6] = false;
                if(key == KeyEvent.VK_LEFT) keyDown[7] = false;

                //vertical movement
                if(!keyDown[0] && !keyDown[1] && !keyDown[4] && !keyDown[5]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3] && !keyDown[6] && !keyDown[7]) tempObject.setVelX(0);
            }
        }
    }
}
