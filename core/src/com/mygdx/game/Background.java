package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by HotaruSan on 04.02.2018.
 * В используемой библиотеке GDX следует учитывать, что лучи системы координат
 * для прорисовки изображения идут из нижнего левого угла |_
 */
public class Background {

    class BGPicture{
        private Texture tx;
        private Vector2 pos;

        public BGPicture(Vector2 pos){
            tx = new Texture("back.png");
            this.pos = pos;
        }
    }

    private int speed;
    private BGPicture[] backs;

    /** Мы задаем массив из двух картинок заднего фона,
     * чтобы осуществить плавную прорисовку движения фона до конца первой картинки
     * и переместить её заново в начало и при этом у нас не образовывалось пустот
      */
    public Background(){
        speed = 4;
        backs = new BGPicture[2];
        backs[0] = new BGPicture(new Vector2(0,0));
        backs[1] = new BGPicture(new Vector2(800,0));
    }

    /** В данном методе мы бедем отрисовывать две картинки
     * @param batch
     */
    public void render(SpriteBatch batch){
        for (int i = 0; i < backs.length; i++) {
            batch.draw(backs[i].tx, backs[i].pos.x, backs[i].pos.y);
        }
        //batch.draw(tx, pos.x, pos.y);  -- Если бы мы рисовали фон одной картинкой
    }

    /** Задаем логику просчета прорисовки заднего фона
     */
    public void update(){
        for (int i = 0; i < backs.length; i++) {
            backs[i].pos.x -= speed;
        }
        if(backs[0].pos.x < -800){
            backs[0].pos.x = 0;
            backs[1].pos.x = 800;
        }
    }
}
