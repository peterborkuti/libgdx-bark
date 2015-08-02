package hu.bp.bark.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractDraggableActor extends Actor implements DraggableActor {
	
	class ActorState {
		public float x0, y0, dragX, dragY, width, height;
	}

	protected ActorState actorState;
	public ActorState getActorState() {
		return actorState;
	}

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

		init(x0, y0, width, height);
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
	}

	public void switchOnFake() {
		fakeActor.setBounds(getX(), getY(), getWidth(), getHeight());
		fakeActor.toFront();
		fakeActor.setVisible(true);
		fakeActor.setColor(_hitColor);
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

	public void drag(float x, float y, int button) {
		if (Input.Buttons.LEFT == button) {
			float newHeight = Math.max(getMinHeight(), actorState.height + y -actorState.dragY);
			float newWidth = Math.max(getMinWidth(), actorState.width + x - actorState.dragX);

			getFakeActor().setBounds(getX(), getY(), newWidth, newHeight);
		} else if (Input.Buttons.RIGHT == button) {
			getFakeActor().setBounds(actorState.x0 + x - actorState.dragX, actorState.y0 + y - actorState.dragY, actorState.width, actorState.height);
		}
	}

	public float getMinHeight() {
		return 10;
	}

	public float getMinWidth() {
		return 10;
	}





}
