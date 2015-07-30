package hu.bp.bark.actors;

public interface DraggableActor {

	public void dragged(float dx, float dy);
	public void toFront();
	public void setColorToDefault();
	public void setColorHitColor();

}
