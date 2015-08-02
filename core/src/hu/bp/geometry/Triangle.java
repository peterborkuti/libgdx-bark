package hu.bp.geometry;

import static hu.bp.geometry.Triangle.LEFT;
import static hu.bp.geometry.Triangle.LOWER;
import static hu.bp.geometry.Triangle.UPPER;

import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Triangle {

	public static final int LEFT = 0;

	public static final int LOWER = 2;

	public static final int UPPER = 1;

	private Vector2[] points = new Vector2[3];

	public Triangle(Actor a) {
		setPoints(a);
	}

	public boolean pointIn(Vector2 p) {
		Vector2 barycentricOut = new Vector2();

		GeometryUtils.toBarycoord(
			p, points[LEFT], points[UPPER], points[LOWER],
			barycentricOut);

		return (GeometryUtils.barycoordInsideTriangle(barycentricOut));		
	}

	public void setPoints(float x0, float y0, float x1, float y1, float x2, float y2) {
		points[LEFT] = new Vector2(x0, y0);
		points[UPPER] = new Vector2(x1, y1);
		points[LOWER] = new Vector2(x2, y2);
	}

	public boolean overlaps(Rectangle r) {
		return
			pointIn(new Vector2(r.getX(), r.getY())) ||
			pointIn(new Vector2(r.getX() + r.getWidth(), r.getY())) ||
			pointIn(new Vector2(r.getX(), r.getY() + r.getHeight())) ||
			pointIn(new Vector2(r.getX() + r.getWidth(), r.getY() + r.getHeight()));
	}

	public void setPoints(Actor a) {
		setPoints(
			a.getX(), a.getY() + a.getHeight() / 2,
			a.getX() + a.getWidth(), a.getY() + a.getHeight(),
			a.getX() + a.getWidth(), a.getY());
	}

	public Vector2[] getPoints() {
		Vector2[] p = new Vector2[3];

		for (int i = 0; i < 3; i++) {
			p[i] = new Vector2(points[i].x, points[i].y);
		}

		return p;
	}

}
