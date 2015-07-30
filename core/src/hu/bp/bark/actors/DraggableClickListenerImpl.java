package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DraggableClickListenerImpl extends ClickListener implements DraggableClickListener {

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

	private float touchDownX, touchDownY;
	private boolean isTouchDown = false;

	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		boolean sup = super.touchDown(event, x, y, pointer, button);
		Gdx.app.log("TriangleActorListener", x + "," + y + " touchDown" + sup);
		touchDownX = x;
		touchDownY = y;
		isTouchDown = true;

		actor.toFront();

		return sup;
		
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		super.touchUp(event, x, y, pointer, button);
		isTouchDown = false;
		Gdx.app.log("TriangleActorListener", x + "," + y + " touchUp");
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		super.touchDragged(event, x, y, pointer);
		if (isTouchDown) {
			actor.dragged(x - touchDownX, y - touchDownY);
			touchDownX = x;
			touchDownY = y;
		}
	}

	@Override
	public void setActor(DraggableActor actor) {
		this.actor = actor;
	}
	
}