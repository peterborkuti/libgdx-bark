package hu.bp.game.gdx.barking;

import hu.bp.bark.actors.DraggableListener;
import hu.bp.bark.actors.DraggableListenerImpl;
import hu.bp.bark.actors.DraggableRectangleActor;
import hu.bp.bark.actors.DraggableTriangleActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BarkTestScreen implements Screen {
	private Stage stage;

	public BarkTestScreen() {

		DraggableRectangleActor fakeEnemy = new DraggableRectangleActor(Color.MAGENTA, 90, 50, 20, 20);
		fakeEnemy.setVisible(false);

		DragListener enemyListener = new DraggableListenerImpl();
		DraggableRectangleActor enemy = new DraggableRectangleActor(Color.MAGENTA, 90, 50, 20, 20);
		enemy.setFakeActor(fakeEnemy);
		((DraggableListener) enemyListener).setActor(enemy);
		enemy.addListener(enemyListener);
		enemy.debug();

		DragListener pirListener = new DraggableListenerImpl();
		DraggableTriangleActor pirSensor = new DraggableTriangleActor(Color.GREEN, 0, 50, 50, 50);
		((DraggableListener) pirListener).setActor(pirSensor);
		pirSensor.addListener(pirListener);
		pirSensor.debug();

		DragListener distanceListener = new DraggableListenerImpl();
		DraggableTriangleActor distanceSensor = new DraggableTriangleActor(Color.BLUE, 0, 50, 30, 50);
		((DraggableListener) distanceListener).setActor(distanceSensor);
		distanceSensor.addListener(distanceListener);
		distanceSensor.debug();
	

		//Actor a = new TestActor();
		//a.debug();

		stage = new Stage(new StretchViewport(100, 100));
		//stage.addActor(a);
		stage.addActor(pirSensor);
		stage.addActor(distanceSensor);
		stage.addActor(enemy);
		stage.addActor(fakeEnemy);
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
