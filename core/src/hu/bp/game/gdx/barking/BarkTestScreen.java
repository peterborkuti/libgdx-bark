package hu.bp.game.gdx.barking;

import hu.bp.bark.actors.DraggableClickListener;
import hu.bp.bark.actors.DraggableClickListenerImpl;
import hu.bp.bark.actors.DraggableRectangleActor;
import hu.bp.bark.actors.DraggableTriangleActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BarkTestScreen implements Screen {
	private Stage stage;

	public BarkTestScreen() {

		ClickListener enemyListener = new DraggableClickListenerImpl();
		DraggableRectangleActor enemy = new DraggableRectangleActor(Color.MAGENTA, 150, 150, 30, 30);
		((DraggableClickListener) enemyListener).setActor(enemy);
		enemy.addListener(enemyListener);
		enemy.debug();

		ClickListener pirListener = new DraggableClickListenerImpl();
		DraggableTriangleActor pirSensor = new DraggableTriangleActor(Color.GREEN, 0, 150, 100, 100);
		((DraggableClickListener) pirListener).setActor(pirSensor);
		pirSensor.addListener(pirListener);
		pirSensor.debug();

		ClickListener distanceListener = new DraggableClickListenerImpl();
		DraggableTriangleActor distanceSensor = new DraggableTriangleActor(Color.BLUE, 0, 50, 30, 100);
		((DraggableClickListener) distanceListener).setActor(distanceSensor);
		distanceSensor.addListener(distanceListener);
		distanceSensor.debug();
	

		//Actor a = new TestActor();
		//a.debug();

		stage = new Stage(new StretchViewport(200, 200));
		//stage.addActor(a);
		stage.addActor(pirSensor);
		stage.addActor(distanceSensor);
		stage.addActor(enemy);
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
