package com.trifluxgaming.game;

import com.trifluxgaming.game.entities.Player;
import com.trifluxgaming.game.screens.Menu;
import com.trifluxgaming.game.screens.MenuParticle;
import com.trifluxgaming.game.util.*;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main extends Canvas implements Runnable{

    public static final int WIDTH = 624, HEIGHT = WIDTH / 12 * 9; //624 WIDTH / 12 * 9

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    public HUD hud;
    public Spawn spawner;
    private Menu menu;
    public static Player player;

    public static Main run;

    public enum STATE{
        Menu,
        Help,
        Game,
        Pause,
        Win,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Main(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "LightBeam", this);

        spawner = new Spawn(handler, hud);
        r = new Random();

        if(gameState == STATE.Game){
            handler.addObject( player = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        }else{
            for( int i = 0; i < 20; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running) {
                render();
            }
            frames++;
        }
        stop();
    }

    private void tick(){
        handler.tick();

        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                HUD.HEALTH = 100;
                gameState = STATE.End;
                handler.ClearEnemies();
                for( int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                }
            }

        }else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Win){
            menu.tick();
        }
        if(HUD.HEALTH <= 0 && gameState == STATE.Pause){
            run.hud.gamePaused = false;
            run.spawner.spawnPaused = false;
            run.spawner.handler.tempObject.resume();
            gameState = STATE.End;
            handler.ClearEnemies();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game || gameState == STATE.Pause){
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End|| gameState == STATE.Win){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max) {
            return var = max;
        }
        else if(var <= min) {
            return var = min;
        }else {
            return var;
        }
    }

    public static void main(String args[]){
        run = new Main();
    }
}