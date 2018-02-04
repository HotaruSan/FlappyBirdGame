package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.Texture;  -- Импорт данного пакета в нашем случае не востребован

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	/** Texture img; -- В исходной библиотеке GDX обозначали переменную под картинку
	 * Заменяем на нашу переменную заднего фона
	 */
	Background bg;
	//Добавляем нашу птичку
	Bird bird;
	//Добавляем препятствия
	Obstacles obstacles;
	//Обуславливаем состояние игры и задаем кнопку для рестарта в случае проигрыша
	boolean gameOver;
	Texture rBtn;

	@Override
	public void create () {
		//отрисовка объектов
		batch = new SpriteBatch();
		/** img = new Texture("badlogic.jpg"); -- Здесь инициализировалась картинка GDX
		 * А мы инициализируем свой бэкграунд из своего класса
		 */
		bg = new Background();
		//Инициализируем новую птичку
		bird = new Bird();
		//Инициализируем объекты припятствий
		obstacles = new Obstacles();
		gameOver = false;
		rBtn = new Texture("RestartBtn.png");
	}

	//Данный код отвечает за прорисовку игрового поля 60 раз в секунду (рендеринг изображения)
	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//начало отрисовки
		batch.begin();
		//отрисовываем объект
		/** batch.draw(img, 0, 0);  -- Здесь собственно и происходит отрисовка картинки
		 * А мы тем временем рисуем свой фон
		 */
		bg.render(batch);
		obstacles.render(batch);
		//Отрисовываем птичку после фона и препятсвий, чтобы она у нас была на переднем плане!
		if(!gameOver){
			bird.render(batch);
		} else batch.draw(rBtn, 200, 200);
		//заканчивается отрисовка
		batch.end();
	}

	/** Обновляем позиции объектов на нашем игровом поле
	 * За счет метода super.render обновление позиций происходит со скоростью 60FPS
	 */
	public void update(){
		bg.update();
		obstacles.update();
		bird.update();
		for (int i = 0; i < Obstacles.obs.length; i++) {
			if ((bird.positionBrd.x	> Obstacles.obs[i].positionWallPair.x) && (bird.positionBrd.x < Obstacles.obs[i].positionWallPair.x + 50)){
				if (!Obstacles.obs[i].emptySpace.contains(bird.positionBrd)){
					gameOver = true;
				}
			}
		}
		if ((bird.positionBrd.y < 0) || (bird.positionBrd.y > 600)){
			gameOver = true;
		}
		/** Перезапускаем нашу игру после проигрыша
		 */
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}
	}

	//освобождение ресурсов
	@Override
	public void dispose () {
		batch.dispose();
		/** img.dispose();  -- Поскольку данную картинку мы не используем, то и очишать уже нечего
		 */
	}

	public void recreate(){
		gameOver = false;
		obstacles.recreate();
		bird.recreate();
	}
}
