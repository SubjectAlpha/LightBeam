package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.Main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;
    Random r = new Random();

    private int timer = 80;
    private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) {
            velY = 0;
        }else{
            timer--;
        }

        if(timer <= 0){
            timer2--;
        }

        if(timer2 <= 0){
            if(velX == 0){
                velX = 2;
            }

            if(velX > 0){
                velX += 0.005f;
            }else if(velX < 0){
                velX -= 0.005f;
            }

            velX = Main.clamp(velX, -10, 10);

            int spawn = r.nextInt(10);

            if(spawn == 0){
                handler.addObject(new EnemyBossBullet((int)x + 30, (int)y - 10, ID.BasicEnemy, handler));
                handler.addObject(new EnemyBossBullet((int)x + 30, (int)y - 10, ID.BasicEnemy, handler));
            }
        }

        if(x <= 0 || x >= Main.WIDTH - 96){
            velX *= -1;
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}