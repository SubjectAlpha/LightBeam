package com.trifluxgaming.game.util;

import com.trifluxgaming.game.Main;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_ESCAPE;

public class HUD {

    public static float HEALTH = 100;

    private float greenValue = 255;

    public static int score = 0;
    private int level = 0;

    public boolean gamePaused = false;

    public int prevLevel, prevScore;

    KeyEvent e;

    public void tick(){
        HEALTH = Main.clamp((int)HEALTH, 0, 100);
        greenValue = Main.clamp((int)greenValue, 0, 255);

        greenValue = HEALTH * 2;

        if (!gamePaused){
            score++;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.black);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    public void setScore(int score){

        HUD.score = score;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    /**public boolean pause(){
        int key = e.getKeyCode();


        if(key == VK_ESCAPE)
            return true;
        else
            return false;

    }*/
}
