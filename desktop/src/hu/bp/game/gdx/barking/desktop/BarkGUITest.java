package hu.bp.game.gdx.barking.desktop;

import hu.bp.game.gdx.barking.BarkTestGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class BarkGUITest {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BarkTestGame(), config);
	}
}
