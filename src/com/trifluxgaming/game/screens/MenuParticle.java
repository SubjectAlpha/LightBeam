package com.trifluxgaming.game.screens;

import com.trifluxgaming.game.entities.GameObject;
import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.entities.GameObject;
import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.Trail;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Handler handler;

    Random r = new Random();

    private Color col;

    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -7);
        velY = (r.nextInt(5 - -5) + -7);

        if(velX == 0){
            velX = 1;
        }
        if(velY == 0){
            velY = 1;
        }

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 40);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {

        //Graphics2D g2d = (Graphics2D) g;

        //g.setColor(Color.green);
        //g2d.draw(getBounds());

        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}