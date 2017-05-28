package com.trifluxgaming.game.screens;

import com.trifluxgaming.game.util.HUD;
import com.trifluxgaming.game.util.ID;
import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.entities.Player;
import com.trifluxgaming.game.entities.BasicEnemy;
import com.trifluxgaming.game.util.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.trifluxgaming.game.Main.HEIGHT;
import static com.trifluxgaming.game.Main.WIDTH;

public class Menu extends MouseAdapter{

    public Main game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    Font fnt = new Font("arial", Font.PLAIN, 50);
    Font fnt2 = new Font("arial", Font.PLAIN, 30);
    Font fnt3 = new Font("arial", Font.PLAIN, 20);
    Font fnt4 = new Font("arial", Font.PLAIN, 12);

    public Menu(Main game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(Main.gameState == Main.STATE.Menu){
            //play button
            if(mouseOver(mx, my, 150)){
                Main.gameState = Main.STATE.Game;
                handler.addObject(new Player(Main.WIDTH/2-32, Main.HEIGHT/2-32, ID.Player, handler));
                handler.ClearEnemies();
            }

            //help button
            if(mouseOver(mx, my, 250)){
                Main.gameState = Main.STATE.Help;
            }

            //quit button
            if(mouseOver(mx, my, 350)){
                System.exit(1);
            }
        }

        //back button for help
        if(Main.gameState == Main.STATE.Help){
            if(mouseOver(mx, my, 350)){
                Main.gameState = Main.STATE.Menu;
                return;
            }
        }

        //try again button
        if(Main.gameState == Main.STATE.End) {
            if (mouseOver(mx, my, 350)) {
                System.exit(0);
            }
            if(mouseOver(mx, my, 265)){
                Main.gameState = Main.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Main.WIDTH / 2 - 32, Main.HEIGHT / 2 - 32, ID.Player, handler));
                handler.ClearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy, handler));
            }
        }

        //Exit button
        if(Main.gameState == Main.STATE.Win) {
            if (mouseOver(mx, my, 350)) {
                System.exit(0);
            }
            if(mouseOver(mx, my, 265)){
                Main.gameState = Main.STATE.Game;
                HUD.HEALTH = 100;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Main.WIDTH / 2 - 32, Main.HEIGHT / 2 - 32, ID.Player, handler));
                handler.ClearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy, handler));
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int y) {
        return mx > 210 && mx < 210 + 200 && my > y && my < y + 64;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(Main.gameState == Main.STATE.Menu) {
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("LightBeam", 190, 100);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);

            g.setFont(fnt4);
            g.drawString("Version 1:5.28.17", 510, 425);
        }else if(Main.gameState == Main.STATE.Help){
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD/Arrow keys to move the player and dodge enemies", 50, 200);
            g.drawString("P key to pause", 50, 230);
            g.drawString("Escape key to resume", 50, 260);
            g.drawString("Blue blocks are health packs", 50, 290);
            g.drawString("Orange blocks are speed packs", 50, 320);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }else if(Main.gameState == Main.STATE.End){
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
            g.drawString("You were on level: " + hud.getLevel(), 175, 230);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Exit", 275, 390);

            g.setFont(fnt2);
            g.drawRect(210, 265, 200, 64);
            g.drawString("Try Again", 245, 305);
        }else if(Main.gameState == Main.STATE.Win) {
            g.setFont(fnt);
            g.setColor(Color.black);
            g.drawString("You win!", 205, 70);

            g.setFont(fnt3);
            g.drawString("Final Score: " + hud.getScore(), 210, 200);

            g.setFont(fnt2);
            g.drawRect(210, 265, 200, 64);
            g.drawString("Play Again", 240, 305);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Exit", 270, 390);
        }
    }
}
