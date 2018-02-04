package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

//Основной класс, отвечающий за запуск нашего приложения
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		/** Задаем размеры игрового поля под размеры отрисованного заднего фона
		 */
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
