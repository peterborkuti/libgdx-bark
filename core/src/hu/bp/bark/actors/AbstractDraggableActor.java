package hu.bp.bark.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractDraggableActor extends Actor implements DraggableActor {

	public ActorState getActorStateBeforeDrag() {
		return actorState;
	}

	protected ActorState actorState;
	private long lastChange;
	protected Actors actorType;
	protected Color _color;
	protected Color _defaultColor;
	protected Color _hitColor = Color.RED;
	protected ShapeRenderer _renderer;

	private Actor fakeActor;

	public AbstractDraggableActor(
		Actors actor, Color defaultColor, Color hitColor,
		float x0, float y0, float width, float height) {

		_defaultColor = defaultColor;
		_color = defaultColor;
		_hitColor = hitColor;
		actorState = new ActorState();
		actorType = actor;
		_renderer = new ShapeRenderer();
		lastChange = System.currentTimeMillis();

		init(x0, y0, width, height);
	}

	public long getLastChange() {
		return lastChange;
	}

	@Override
	public void init(float x, float y, float width, float height) {
		setBounds(x, y, width, height);
	}

	public Actors getActorType() {
		return actorType;
	}

	@Override
	public void setColorToDefault() {
		_color = _defaultColor;
	}

	@Override
	public void setColorHitColor() {
		_color = _hitColor;
	}

	@Override
	public void setFakeActor(Actor actor) {
		fakeActor = actor;
		
	}

	public Actor getFakeActor() {
		return fakeActor;
	}

	@Override
	public void dragStop(float x, float y, int button) {
		fakeActor.toBack();
		fakeActor.setVisible(false);

		setBounds(fakeActor.getX(), fakeActor.getY(), fakeActor.getWidth(), fakeActor.getHeight());
		toFront();
		lastChange = System.currentTimeMillis();
	}

	public void switchOnFake() {
		fakeActor.setBounds(getX(), getY(), getWidth(), getHeight());
		fakeActor.toFront();
		fakeActor.setVisible(true);
		fakeActor.setColor(_hitColor);
		fakeActor.setDebug(true);
	}

	@Override
	public void dragStart(float x, float y, int button) {
		actorState.x0 = getX();
		actorState.y0 = getY();
		actorState.dragX = x;
		actorState.dragY = y;
		actorState.width = getWidth();
		actorState.height = getHeight();

		switchOnFake();
	}

	public void resize(float newWidth, float newHeight) {
		getFakeActor().setBounds(getX(), getY(), newWidth, newHeight);
	}

	public void replace(float newX, float newY) {
		getFakeActor().setBounds(newX, newY, getWidth(), getHeight());
	}

	public void drag(float x, float y, int button) {
		if (Input.Buttons.RIGHT == button) {
			float newWidth = Math.max(getMinWidth(), actorState.width + x - actorState.dragX);
			float newHeight = Math.max(getMinHeight(), actorState.height + y -actorState.dragY);

			resize(newWidth, newHeight);
		} else if (Input.Buttons.LEFT == button) {
			float newX = actorState.x0 + x - actorState.dragX;
			float newY = actorState.y0 + y - actorState.dragY;

			replace(newX, newY);
		}
	}

	public float getMinHeight() {
		return 10;
	}

	public float getMinWidth() {
		return 10;
	}


}
