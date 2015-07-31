package hu.bp.bark.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractDraggableActor extends Actor implements DraggableActor {
	
	class ActorState {
		public float x0, y0, dragX, dragY, width, height;
	}

	protected ActorState actorState;
	protected Color _color;
	protected Color _defaultColor;
	protected Color _hitColor = Color.RED;
	private Actor fakeActor;

	public AbstractDraggableActor(Color defaultColor, Color hitColor) {
		_defaultColor = defaultColor;
		_color = defaultColor;
		_hitColor = hitColor;
		actorState = new ActorState();
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
		getFakeActor().toBack();
		getFakeActor().setVisible(false);

		setBounds(getFakeActor().getX(), getFakeActor().getY(), getFakeActor().getWidth(), getFakeActor().getHeight());
		toFront();
	}

	private void switchOnFake() {
		getFakeActor().setBounds(getX(), getY(), getWidth(), getHeight());
		getFakeActor().toFront();
		getFakeActor().setVisible(true);
		getFakeActor().setColor(_hitColor);
		getFakeActor().debug();
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


}
