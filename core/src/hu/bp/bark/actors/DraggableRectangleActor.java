package hu.bp.bark.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DraggableRectangleActor extends AbstractDraggableActor {

	public DraggableRectangleActor(Actors actor, Color defaultColor,
			Color hitColor, float x0, float y0, float width, float height) {
		super(actor, defaultColor, hitColor, x0, y0, width, height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		_renderer.begin(ShapeType.Filled);
		_renderer.setColor(_color);
		_renderer.rect(getX(), getY(), getWidth(), getHeight());
		_renderer.end();

		batch.begin();
	}


}
