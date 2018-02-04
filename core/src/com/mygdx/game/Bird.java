package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by HotaruSan on 04.02.2018.
 */
public class Bird {
    Texture brd;
    Vector2 positionBrd;
    float vy;
    float gravity;

    public Bird(){
        brd = new Texture("bird1.png");
        positionBrd = new Vector2(100,300);
        vy = 0;
        gravity = -0.3f;
    }

    public void render(SpriteBatch batch){
        batch.draw(brd, positionBrd.x, positionBrd.y);
    }

    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            vy = 7;
        }
        vy += gravity;
        positionBrd.y += vy;
    }

    public void recreate(){
        positionBrd = new Vector2(100,300);
        vy = 0;
    }
}
