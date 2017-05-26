package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.Trail;

import java.awt.*;

public class FastEnemyY extends GameObject {

    private Handler handler;

    public FastEnemyY(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 9;
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

        //Graphics2D g2d = (Graphics2D) g;

        //g.setColor(Color.green);
        //g2d.draw(getBounds());

        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}