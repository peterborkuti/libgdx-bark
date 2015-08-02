package hu.bp.game.gdx.barking;

import hu.bp.bark.actors.Actors;
import hu.bp.bark.actors.DraggableActor;
import hu.bp.bark.actors.DraggableListener;
import hu.bp.bark.actors.DraggableListenerImpl;
import hu.bp.bark.actors.DraggableRectangleActor;
import hu.bp.bark.actors.DraggableTriangleActor;
import hu.bp.geometry.Triangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BarkTestScreen implements Screen {
	private Stage stage;
	private DraggableRectangleActor enemy;
	private DraggableTriangleActor pirSensor;
	private DraggableTriangleActor distanceSensor;

	public BarkTestScreen() {

		DraggableRectangleActor fakeEnemy = new DraggableRectangleActor(Actors.FAKE, Color.MAGENTA, Color.RED, 90, 50, 20, 20);
		fakeEnemy.setVisible(false);

		DragListener enemyListener = new DraggableListenerImpl();
		enemy = new DraggableRectangleActor(Actors.ENEMY, Color.MAGENTA, Color.RED, 90, 50, 20, 20);
		enemy.setFakeActor(fakeEnemy);
		((DraggableListener) enemyListener).setActor(enemy);
		enemy.addListener(enemyListener);

		DraggableTriangleActor fakeTriangle = new DraggableTriangleActor(Actors.FAKE, Color.MAGENTA, Color.RED, 90, 50, 20, 20);
		fakeTriangle.setVisible(false);

		DragListener pirListener = new DraggableListenerImpl();
		pirSensor = new DraggableTriangleActor(Actors.PIR, Color.GREEN, Color.RED, 0, 50, 50, 50);
		pirSensor.setFakeActor(fakeTriangle);
		((DraggableListener) pirListener).setActor(pirSensor);
		pirSensor.addListener(pirListener);

		DragListener distanceListener = new DraggableListenerImpl();
		distanceSensor = new DraggableTriangleActor(Actors.HCSR04, Color.BLUE, Color.RED, 0, 50, 30, 50);
		distanceSensor.setFakeActor(fakeTriangle);
		((DraggableListener) distanceListener).setActor(distanceSensor);
		distanceSensor.addListener(distanceListener);

		//Actor a = new TestActor();
		//a.debug();

		stage = new Stage(new StretchViewport(100, 100));
		//stage.addActor(a);
		stage.addActor(pirSensor);
		stage.addActor(distanceSensor);
		stage.addActor(enemy);
		stage.addActor(fakeEnemy);
		stage.addActor(fakeTriangle);
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
	    checkCollisions();
	}

	private void checkCollisions() {

		Triangle tPir = new Triangle(pirSensor);
		Triangle tDis = new Triangle(distanceSensor);

		
		Rectangle rEnemy = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

		if (tPir.overlaps(rEnemy)) {
			Gdx.app.log("Bark", "PIR");
		}
		if (tDis.overlaps(rEnemy)) {
			Gdx.app.log("Bark", "INSIGHT");
		}
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
