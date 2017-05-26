package com.trifluxgaming.game.util;

import com.trifluxgaming.game.entities.GameObject;
import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.entities.Player;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

    public static Player player;

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void ClearEnemies(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId() == ID.Player){
                object.clear();
                if(Main.gameState != Main.STATE.End) {
                    addObject(player = new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                }
            }
        }
    }

    public void ClearAll(){
        ClearEnemies();

        removeObject(player);

        removeObject(Main.player);
    }

    public void addObject(boolean add){
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
