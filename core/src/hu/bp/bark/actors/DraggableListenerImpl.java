package hu.bp.bark.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class DraggableListenerImpl extends DragListener implements DraggableListener {

	public DraggableListenerImpl() {
		super();
		this.setButton(-1); //Listen to all buttons
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer,
			Actor fromActor) {
		super.enter(event, x, y, pointer, fromActor);
		actor.setColorHitColor();
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer,
			Actor toActor) {
		super.exit(event, x, y, pointer, toActor);
		actor.setColorToDefault();
	}

	private DraggableActor actor;

	private boolean isTouchDown = false;
	private int pressedButton = -1;

	private int pressedPointer = -1;

	public int getPressedButton() {
		return pressedButton;
	}

	public int getPressedPointer() {
		return pressedPointer;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {

		if (button != -1 && !isTouchDown) {
			isTouchDown = true;

			actor.toFront();
			pressedButton = button;
			pressedPointer = pointer;
		}

		return super.touchDown(event, x, y, pointer, button);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {

		if (isTouchDown && pressedButton == button) {
			isTouchDown = false;
			pressedButton = -1;
			pressedPointer--;
		}

		super.touchUp(event, x, y, pointer, button);
	}

	@Override
	public void setActor(DraggableActor actor) {
		this.actor = actor;
	}

	public void dragStart (InputEvent event, float x, float y, int pointer) {
		if (pressedPointer != -1 && pointer == pressedPointer) {
			actor.dragStart(x, y, pressedButton);
		}
	}

	public void drag (InputEvent event, float x, float y, int pointer) {
		if (pressedPointer != -1 && pointer == pressedPointer) {
			actor.drag(x, y, pressedButton);
		}
	}

	public void dragStop (InputEvent event, float x, float y, int pointer) {
		if (pressedPointer != -1 && pointer == pressedPointer) {
			actor.dragStop(x, y, pressedButton);
		}
	}
}