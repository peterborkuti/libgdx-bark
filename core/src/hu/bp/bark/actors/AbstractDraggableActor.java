package hu.bp.bark.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractDraggableActor extends Actor implements DraggableActor {

	protected Color _color;
	protected Color _defaultColor;
	protected Color _hitColor = Color.RED;

	public AbstractDraggableActor(Color defaultColor, Color hitColor) {
		_defaultColor = defaultColor;
		_color = defaultColor;
		_hitColor = hitColor;
	}

	@Override
	public void setColorToDefault() {
		_color = _defaultColor;
	}

	@Override
	public void setColorHitColor() {
		_color = _hitColor;
	}

}
