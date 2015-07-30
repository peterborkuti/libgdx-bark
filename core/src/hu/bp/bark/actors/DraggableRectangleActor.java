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

public class DraggableRectangleActor extends AbstractDraggableActor {

	private ShapeRenderer _renderer;

	public DraggableRectangleActor(Color color, int leftX, int leftY,int width, int height) {
		super(color, Color.RED);
		_renderer = new ShapeRenderer();
		this.setVisible(true);
		dragged(width,height);
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

	public float getMinHeight() {
		return 10;
	}
	public float getMinWidth() {
		return 10;
	}

	@Override
	public void dragged(float dx, float dy) {
		float newHeight = Math.max(getMinHeight(), getHeight() + dy);
		float newWidth = Math.max(getMinWidth(), getWidth() + dx);

		setBounds(getX(), getY(), newWidth, newHeight);
	}


}
