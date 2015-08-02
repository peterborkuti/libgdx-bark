package hu.bp.bark.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface DraggableActor {

	public void toFront();
	public void setColorToDefault();
	public void setColorHitColor();
	public void setFakeActor(Actor actor);
	public Actor getFakeActor();
	public Actors getActorType();

	public void init(float x0, float y0, float width, float height);
	
	// from DragListener
	public void dragStart (float x, float y, int button);
	public void drag (float x, float y, int button);
	public void dragStop (float x, float y, int button);

}
