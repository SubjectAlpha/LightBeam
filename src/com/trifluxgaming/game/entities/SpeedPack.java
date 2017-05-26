package com.trifluxgaming.game.entities;

import com.trifluxgaming.game.util.Handler;
import com.trifluxgaming.game.util.ID;

import java.awt.*;

public class SpeedPack extends GameObject{
    private Handler handler;

    public SpeedPack(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {}

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
