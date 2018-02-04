package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 * Created by HotaruSan on 04.02.2018.
 */
public class Obstacles {
    /** Клас для управления парами преград
     */
    class WallPair{
        Vector2 positionWallPair;
        float speed;
        int offset;
        Rectangle emptySpace;

        public WallPair(Vector2 pos){
            positionWallPair = pos;
            speed = 2;
            offset = new Random().nextInt(250);
            emptySpace = new Rectangle(positionWallPair.x, positionWallPair.y - offset + 300, 50, betweenDistance);
        }

        public void update(){
            positionWallPair.x -= speed;
            if(positionWallPair.x < -50){
                positionWallPair.x = 800;
                offset = new Random().nextInt(250);
            }
            emptySpace.x = positionWallPair.x;
        }
    }

    //Задаем общие переменные для нашего класа преград
    static WallPair[] obs;
    Texture wall;
    int betweenDistance;

    public Obstacles(){
        wall = new Texture("wall.png");
        obs = new WallPair[4];
        betweenDistance = 250;
        int startPosition = 400;

        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPair(new Vector2(startPosition, 0));
            startPosition += 220;
        }
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < obs.length; i++) {
            batch.draw(wall, obs[i].positionWallPair.x, obs[i].positionWallPair.y - obs[i].offset);
            batch.draw(wall, obs[i].positionWallPair.x, obs[i].positionWallPair.y + betweenDistance + wall.getHeight() - obs[i].offset);
        }
    }

    public void update(){
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();
        }
    }

    public void recreate(){
        int startPosition = 400;

        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPair(new Vector2(startPosition, 0));
            startPosition += 220;
        }
    }
}
