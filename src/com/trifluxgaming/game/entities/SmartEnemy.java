package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.*;
import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.util.Trail;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public boolean smartPaused = false;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i< handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        if(!smartPaused) {
            x += velX;

            y += velY;

            float diffX = x - player.getX() - 5;
            float diffY = y - player.getY() - 5;
            float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

            velX = (float) ((-3.0 / distance) * diffX);
            velY = (float) ((-3.0 / distance) * diffY);

            if (y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;
            if (x <= 0 || x >= Main.WIDTH - 16) velX *= -1;
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public void pause(){
        smartPaused = true;
    }

    public void resume(){
        smartPaused = false;
    }
}