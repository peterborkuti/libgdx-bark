package hu.bp.bark.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface DraggableActor {

	public void toFront();
	public void setColorToDefault();
	public void setColorHitColor();

	// from DragListener
	public void dragStart (float x, float y, int button);
	public void drag (float x, float y, int button);
	public void dragStop (float x, float y, int button);

}
