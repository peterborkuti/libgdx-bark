package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TriangleClickListenerImpl extends ClickListener implements TriangleClickListener {

	/**
	 * 
	 */
	private TriangleActor triangleActor;

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
		super.touchUp(event, x, y, pointer, button);
		isTouchDown = false;
		Gdx.app.log("TriangleActorListener", x + "," + y + " touchUp");
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		super.touchDragged(event, x, y, pointer);
		if (isTouchDown) {
			float newWidth = Math.max((x - touchDownX + touchWidth), minWidth);
			float newHeight = Math.max((y - touchDownY + touchHeight), minHeight);

			triangleActor.setBounds(newWidth, newHeight);
		}
	}

	@Override
	public void setActor(TriangleActor actor) {
		triangleActor = actor;
	}
	
}