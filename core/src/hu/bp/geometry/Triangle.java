package hu.bp.geometry;

import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
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

	private boolean rectangleCornerInTriangle(Rectangle r) {
		return
			pointIn(new Vector2(r.getX(), r.getY())) ||
			pointIn(new Vector2(r.getX() + r.getWidth(), r.getY())) ||
			pointIn(new Vector2(r.getX(), r.getY() + r.getHeight())) ||
			pointIn(new Vector2(r.getX() + r.getWidth(), r.getY() + r.getHeight()));
	}

	public Polygon getPolygon() {
		Polygon pol = new Polygon( new float[] {
				points[LEFT].x, points[LEFT].y,
				points[LOWER].x, points[LOWER].y,
				points[UPPER].x, points[UPPER].y});
		return pol;
	}

	private boolean triangleLinesCutRectangle(Rectangle r) {
		Polygon tr = getPolygon();

		Vector2 left1 = new Vector2(r.x, r.y + r.height);
		Vector2 left2 = new Vector2(r.x, r.y);

		Vector2 upper1 = new Vector2(r.x, r.y + r.height);
		Vector2 upper2 = new Vector2(r.x + r.width, r.y + r.height);

		Vector2 lower1 = new Vector2(r.x, r.y);
		Vector2 lower2 = new Vector2(r.x + r.width, r.y);

		return 
			(Intersector.intersectSegmentPolygon(left1, left2, tr) ||
			Intersector.intersectSegmentPolygon(upper1, upper2, tr) ||
			Intersector.intersectSegmentPolygon(lower1, lower2, tr));
	}

	public boolean overlaps(Rectangle r) {
		return
			rectangleCornerInTriangle(r) ||
			triangleLinesCutRectangle(r);
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
