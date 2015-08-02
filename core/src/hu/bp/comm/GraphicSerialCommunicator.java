package hu.bp.comm;

import hu.bp.bark.actors.AbstractDraggableActor;
import hu.bp.bark.actors.ActorState;
import hu.bp.geometry.Triangle;
import static hu.bp.geometry.Triangle.LEFT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GraphicSerialCommunicator implements SerialCommunicator {

	public static final int DATA_ARRIVAL_DELAY = 200; // ms
	public static final long PIR_MEMORY_DELAY = 1000; // ms
	private long lastData = 0;
	private AbstractDraggableActor enemy;
	private AbstractDraggableActor pirSensor;
	private AbstractDraggableActor distanceSensor;

	public GraphicSerialCommunicator(
			AbstractDraggableActor enemy, AbstractDraggableActor pirSensor,
			AbstractDraggableActor distanceSensor) {

		this.enemy = enemy;
		this.pirSensor = pirSensor;
		this.distanceSensor = distanceSensor;
	}

	@Override
	public void stop() {
	}

	@Override
	public void start() {
	}

	@Override
	public boolean isDataAvailable() {
		if ((System.currentTimeMillis() - lastData) > DATA_ARRIVAL_DELAY) {
			lastData = System.currentTimeMillis();

			return true;
		}

		return false;
	}

	@Override
	public BarkData getData() {
		BarkData data = new BarkData();
		data.setMaxDistance(getMaxDistance());
		data.setPir(getPIRValue());
		data.setDistance(GetDistanceValue());

		return data;
	}

	private int getMaxDistance() {
		return Math.round(distanceSensor.getWidth());
	}

	private boolean getPIRValue() {
		//if PIR sensor place changed, PIR should sign it
		if ((System.currentTimeMillis() - pirSensor.getLastChange()) <
			PIR_MEMORY_DELAY) {
			return true;
		}

		if ((System.currentTimeMillis() - enemy.getLastChange()) >
				PIR_MEMORY_DELAY) {
			return false;
		}

		//if enemy's place changed and start or end place is in PIR sensor's sight
		//PIR should sign it
		//(It also should sign if the enemy's route crosses PIR's sight, but that
		//would need too many codes)

		Triangle tPir = new Triangle((Actor)pirSensor);

		Rectangle rEnemy = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

		if (tPir.overlaps(rEnemy)) {
			return true;
		}

		ActorState state = enemy.getActorStateBeforeDrag();

		rEnemy = new Rectangle(state.x0, state.y0, state.width, state.height);

		if (tPir.overlaps(rEnemy)) {
			return true;
		}

		return false;
	}

	private int GetDistanceValue() {

		Triangle tDis = new Triangle((Actor)distanceSensor);

		Rectangle rEnemy = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

		if (tDis.overlaps(rEnemy)) {
			return Math.round(Math.abs(enemy.getX() - tDis.getPoints()[LEFT].x));
		}

		return 0;
	}

}
