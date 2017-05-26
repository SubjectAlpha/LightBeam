package com.trifluxgaming.game.util;

import com.trifluxgaming.game.Main;
import com.trifluxgaming.game.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Spawn {

    private Handler handler;
    public HUD hud;
    private Random r = new Random();

    public int scoreKeep = 0;

    public boolean spawnPaused = false;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {

        if(!spawnPaused){
            scoreKeep++;
        }

        if (scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getLevel() == 1) {
                handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));

            }else if(hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));

            }else if(hud.getLevel() == 3) {
                handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));

            }else if(hud.getLevel() == 4) {
                handler.addObject(new FastEnemyY(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyY, handler));

            }else if(hud.getLevel() == 5) {
                handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));

            }else if(hud.getLevel() == 6) {
                handler.addObject(new FastEnemyY(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyY, handler));
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));

            }else if(hud.getLevel() == 7) {
                handler.addObject(new FastEnemyY(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyY, handler));

            }else if(hud.getLevel() == 10) {
                handler.ClearEnemies();
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));
                handler.addObject(new EnemyBoss((Main.WIDTH / 2 - 48), -150, ID.EnemyBoss, handler));
                handler.addObject(new SpeedPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SpeedPack, handler));

            }else if(hud.getLevel() == 15) {
                handler.ClearEnemies();
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));

            }else if(hud.getLevel() == 16) {
                handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));

            }else if(hud.getLevel() == 17) {
                handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));

            }else if(hud.getLevel() == 18) {
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));
                handler.addObject(new FastEnemyX(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyX, handler));

            }else if(hud.getLevel() == 20) {
                handler.addObject(new FastEnemyY(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyY, handler));
                handler.addObject(new EnemyBoss((Main.WIDTH / 2 - 48), -150, ID.EnemyBoss, handler));
                handler.addObject(new FastEnemyX(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemyX, handler));
                handler.addObject(new SpeedPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SpeedPack, handler));
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));

            }else if(hud.getLevel() == 23){
                handler.addObject(new HealthPack(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HealthPack, handler));

            }else if(hud.getLevel() == 25) {
                handler.ClearEnemies();

            }else if(hud.getLevel() == 26){
                handler.ClearAll();
                Main.gameState = Main.STATE.Win;
                scoreKeep = 0;
                hud.setLevel(0);
            }
        }
    }
}
