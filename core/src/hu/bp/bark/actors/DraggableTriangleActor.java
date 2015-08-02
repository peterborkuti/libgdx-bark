package hu.bp.bark.actors;

import static hu.bp.geometry.Triangle.LEFT;
import static hu.bp.geometry.Triangle.LOWER;
import static hu.bp.geometry.Triangle.UPPER;
import hu.bp.geometry.Triangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class DraggableTriangleActor extends AbstractDraggableActor {

	public DraggableTriangleActor(Actors actor, Color defaultColor,
			Color hitColor, float x0, float y0, float width, float height) {
		super(actor, defaultColor, hitColor, x0, y0, width, height);
	}


	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;

		Triangle t = new Triangle(this);

		Vector2 p = new Vector2(x + getX(), y + getY());

		if (t.pointIn(p)) {
			return this;
		}

		return null;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Triangle triangle = new Triangle(this);
		Vector2[] p = triangle.getPoints();

		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		_renderer.begin(ShapeType.Filled);
		_renderer.setColor(_color);

		_renderer.triangle(p[LEFT].x, p[LEFT].y, p[UPPER].x, p[UPPER].y,
				p[LOWER].x, p[LOWER].y);

		_renderer.end();

		batch.begin();
	}


}
