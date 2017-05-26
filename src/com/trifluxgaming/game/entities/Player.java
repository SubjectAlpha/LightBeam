package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.util.*;
import com.trifluxgaming.game.Main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    public Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick(){
        x += velX;
        y += velY;

        x = Main.clamp((int)x, 0, Main.WIDTH - 37);
        y = Main.clamp((int)y, 0, Main.HEIGHT - 60);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.black, 32, 32, 0.05f, handler));

        collision();
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemyY || tempObject.getId() == ID.FastEnemyX ||
                    tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){ //temp object is now basic enemy
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;
                    HUD.score -= 5;
                }
            }else if(tempObject.getId() == ID.HealthPack){
                if(HUD.HEALTH < 100) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH += Main.clamp((int) HUD.HEALTH, 50, 100);
                        handler.removeObject(tempObject);
                    }
                }
            }else if(tempObject.getId() == ID.SpeedPack){
                 if(getBounds().intersects(tempObject.getBounds())){
                     setVelX(velX*3);
                     setVelY(velY*3);
                     handler.removeObject(tempObject);
                 }
            }
        }
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}