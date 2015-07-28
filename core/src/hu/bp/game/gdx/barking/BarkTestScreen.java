package hu.bp.game.gdx.barking;

import hu.bp.bark.actors.TriangleActor;
import hu.bp.bark.actors.TriangleClickListener;
import hu.bp.bark.actors.TriangleClickListenerImpl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BarkTestScreen implements Screen {
	private Stage stage;

	public BarkTestScreen() {
		ClickListener pirListener = new TriangleClickListenerImpl(10, 10);
		TriangleActor pirSensor = new TriangleActor(Color.GREEN, 100, 100, 0, 100);
		((TriangleClickListener) pirListener).setActor(pirSensor);
		pirSensor.addListener(pirListener);

		ClickListener distanceListener = new TriangleClickListenerImpl(10, 10);
		TriangleActor distanceSensor = new TriangleActor(Color.BLUE, 100, 100, 0, 100);
		((TriangleClickListener) distanceListener).setActor(distanceSensor);
		distanceSensor.addListener(distanceListener);

		stage = new Stage(new StretchViewport(200, 200));
		stage.addActor(pirSensor);
		stage.addActor(distanceSensor);
	}

	@Override
	public void show() {
		stage.getViewport().update(200,200);
		stage.getViewport().apply(true);
	    Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		//stage.getViewport().apply();
	    stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();

	}

}
