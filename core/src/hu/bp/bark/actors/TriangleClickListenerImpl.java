package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TriangleClickListenerImpl extends ClickListener implements TriangleClickListener {

	/**
	 * 
	 */
	private Actor triangleActor;

	private float touchDownX, touchDownY, touchWidth, touchHeight;
	private boolean isTouchDown = false;
	private int minWidth, minHeight;

	public TriangleClickListenerImpl(int minWidth, int minHeight) {
		this.minHeight = minHeight;
		this.minWidth = minWidth;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		// TODO Auto-generated method stub
		boolean sup = super.touchDown(event, x, y, pointer, button);
		Gdx.app.log("TriangleActorListener", x + "," + y + " touchDown" + sup);
		touchDownX = x;
		touchDownY = y;
		touchWidth = triangleActor.getWidth();
		touchHeight = triangleActor.getHeight();
		isTouchDown = true;

		triangleActor.toFront();

		return sup;
		
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		// TODO Auto-generated method stub
		super.touchUp(event, x, y, pointer, button);
		isTouchDown = false;
		Gdx.app.log("TriangleActorListener", x + "," + y + " touchUp");
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		super.touchDragged(event, x, y, pointer);
		Gdx.app.log("TriangleActorListener", (x -this.triangleActor.getX()) + "," + (y - this.triangleActor.getY())+ "touchDragged");
		if (isTouchDown) {
			float newWidth = Math.max((x - touchDownX + touchWidth), minWidth);
			float newHeight = Math.max((y - touchDownY + touchHeight), minHeight);

			triangleActor.setWidth(newWidth);
			triangleActor.setHeight(newHeight);
		}
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer,
			Actor fromActor) {
		// TODO Auto-generated method stub
		super.enter(event, x, y, pointer, fromActor);
		Gdx.app.log("TriangleActorListener", x + "," + y + "enter");
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer,
			Actor toActor) {
		super.exit(event, x, y, pointer, toActor);
		//Gdx.app.log("TriangleActorListener", x + "," + y + "exit");
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		super.clicked(event, x, y);
		//Gdx.app.log("TriangleActorListener", x + "," + y + "clicked");
	}

	@Override
	public void setActor(Actor actor) {
		triangleActor = actor;
	}
	
}