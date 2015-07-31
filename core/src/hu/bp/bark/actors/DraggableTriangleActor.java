package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class DraggableTriangleActor extends AbstractDraggableActor {

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;

		Vector2[] t = getPoints();
		Vector2 p = new Vector2(x + getX(), y + getY());
		Vector2 barycentricOut = new Vector2();

		GeometryUtils.toBarycoord(p, t[LEFT], t[UPPER], t[LOWER],
				barycentricOut);

		if (GeometryUtils.barycoordInsideTriangle(barycentricOut)) {
			return this;
		}

		return null;
	}

	private ShapeRenderer _renderer;
	private float _leftX, _leftY;
	public final int LEFT = 0;

	public final int LOWER = 2;

	public final int UPPER = 1;

	public DraggableTriangleActor(Color color, int leftX, int leftY, int width,
			int height) {
		super(color, Color.RED);
		_renderer = new ShapeRenderer();
		this.setVisible(true);
		_leftX = leftX;
		_leftY = leftY;
		drag(width, height, Input.Buttons.LEFT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Vector2[] p = getPoints();

		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		// bounds are not translated, so hit will not work
		// _renderer.translate(100, 50, 0);

		_renderer.begin(ShapeType.Filled);
		_renderer.setColor(_color);
		_renderer.triangle(p[LEFT].x, p[LEFT].y, p[UPPER].x, p[UPPER].y,
				p[LOWER].x, p[LOWER].y);
		_renderer.end();

		batch.begin();
	}

	public float getMinHeight() {
		return 10;
	}

	public float getMinWidth() {
		return 10;
	}

	// (getX(), getY()) gives the bottom-left corner of the bounder rectangle
	public Vector2[] getPoints() {
		Vector2[] points = new Vector2[3];
		points[LEFT] = new Vector2(getX(), getY() + getHeight() / 2);
		points[UPPER] = new Vector2(getX() + getWidth(), getY() + getHeight());
		points[LOWER] = new Vector2(getX() + getWidth(), getY());

		return points;
	}

	@Override
	public void drag(float x, float y, int button) {
		Gdx.app.log("Draggable", x + "," + y);
		if (Input.Buttons.LEFT == button) {
			float newHeight = Math.max(getMinHeight(), actorState.height + actorState.y0 - y);
			float newWidth = Math.max(getMinWidth(), actorState.width + actorState.x0 - y);

			float newY = _leftY - newHeight / 2;
			setBounds(_leftX, newY, newWidth, newHeight);
		} else if (Input.Buttons.RIGHT == button) {
			setBounds(actorState.x0 + x - actorState.dragX, actorState.y0 + y - actorState.dragY, actorState.width, actorState.height);
		}
	}


}
