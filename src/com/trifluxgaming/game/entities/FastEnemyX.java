package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.*;
import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.util.Trail;

import java.awt.*;

public class FastEnemyX extends GameObject {

    private Handler handler;

    public FastEnemyX(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 9;
        velY = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {

        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}