package hu.bp.bark.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface DraggableActor {

	public void toFront();
	public void setColorToDefault();
	public void setColorHitColor();
	public void setFakeActor(Actor actor);
	public Actor getFakeActor();
	
	// from DragListener
	public void dragStart (float x, float y, int button);
	public void drag (float x, float y, int button);
	public void dragStop (float x, float y, int button);

}
