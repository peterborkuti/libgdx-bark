package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TriangleActorImpl extends Actor implements TriangleActor {

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;

		Gdx.app.log("TriangleActor hit", x + "," + y);

		Vector2[] t = getPoints();
		Vector2 p = new Vector2(x + getX(), y + getY());
		Vector2 barycentricOut = new Vector2();

		GeometryUtils.toBarycoord(
			p, t[LEFT], t[UPPER], t[LOWER], barycentricOut);

		if (GeometryUtils.barycoordInsideTriangle(barycentricOut)) {
			Gdx.app.log("TriangleActor", "hit");
			return this;
		}

		Gdx.app.log("TriangleActor", "miss");
		return null;
	}

	private Color _color;
	private ShapeRenderer _renderer;
	private int _leftX, _leftY;
	public final int LEFT = 0;

	public final int LOWER = 2;

	public final int UPPER = 1;
	

	public TriangleActorImpl(Color color, int leftX, int leftY,int width, int height) {
		super();
		_renderer = new ShapeRenderer();
		this.setVisible(true);
		_color = color;
		_leftX = leftX;
		_leftY = leftY;
		setBounds(width, height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Vector2[] p = getPoints();

		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		//bounds are not translated, so hit will not work
		//_renderer.translate(100, 50, 0);

		_renderer.begin(ShapeType.Filled);
		_renderer.setColor(_color);
		_renderer.triangle(p[LEFT].x, p[LEFT].y, p[UPPER].x, p[UPPER].y, p[LOWER].x, p[LOWER].y);
		Gdx.app.log("TriangleActor", p[0] + "," + p[1] + "," + p[2]);
		_renderer.end();

		batch.begin();
	}

	@Override
	public void drawDebug(ShapeRenderer shapes) {
		Gdx.app.log("TriangleActor", "drawDebug");
		super.drawDebug(shapes);
	}

	@Override
	protected void drawDebugBounds(ShapeRenderer shapes) {
		//Gdx.app.log("TriangleActor", "drawDebugBounds (" +
		//	new Vector2(getX(),getY()) + "," + new Vector2(getWidth(),getHeight()));
		super.drawDebugBounds(shapes);
	}

	public float getMinHeight() {
		return 10;
	}
	public float getMinWidth() {
		return 10;
	}

	//(getX(), getY()) gives the bottom-left corner of the bounder rectangle
	public Vector2[] getPoints() {
		Vector2[] points = new Vector2[3];
		points[LEFT] = new Vector2(getX(), getY() + getHeight() / 2);
		points[UPPER] = new Vector2(getX() +getWidth(), getY() + getHeight());
		points[LOWER] = new Vector2(getX() + getWidth(), getY());
		Gdx.app.log("TriangleActor", "points:" +points[0] + "," + points[1] + "," + points[2]);
		return points;
	}

	@Override
	public void setBounds(float width, float height) {
		float newY = _leftY - height / 2;
		setBounds(getX(), newY, width, height);
	}


}
