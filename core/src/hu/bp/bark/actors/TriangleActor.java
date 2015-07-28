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

public class TriangleActor extends Actor {

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;
		Vector2[] t = getPoints();
		Vector2 p = new Vector2(x, y);
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
	private ShapeRenderer _renderer = new ShapeRenderer();
	private int _leftX, _leftY;
	public final int LEFT = 0;

	public final int LOWER = 2;

	public final int UPPER = 1;
	

	public TriangleActor(Color color, int height, int width, int leftX, int leftY) {
		super();
		this.setVisible(true);
		_color = color;
		_leftX = leftX;
		_leftY = leftY;
		setSize(width, height);
		Vector2[] p = this.getPoints();
		setPosition(p[LEFT].x, p[LOWER].y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Vector2[] p = getPoints();

		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		_renderer.translate(getX(), getY(), 0);

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
		// TODO Auto-generated method stub
		return 10;
	}
	public float getMinWidth() {
		// TODO Auto-generated method stub
		return 10;
	}

	//(getX(), getY()) gives the bottom-left corner of the bounder rectangle
	public Vector2[] getPoints() {
		Vector2[] points = 
			{new Vector2(_leftX, _leftY),
				new Vector2(_leftX + getWidth(), _leftY + getHeight() / 2),
				new Vector2(_leftX + getWidth(), _leftY - getHeight() / 2)};
		Gdx.app.log("TriangleActor", "points:" +points[0] + "," + points[1] + "," + points[2]);
		return points;
	}


}
