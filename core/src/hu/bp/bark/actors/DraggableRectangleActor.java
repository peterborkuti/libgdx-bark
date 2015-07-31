package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DraggableRectangleActor extends AbstractDraggableActor {

	private ShapeRenderer _renderer;
	private float _x0, _y0, _height0, _width0, _dragX, _dragY;

	public DraggableRectangleActor(Color color, int leftX, int leftY,
			int width, int height) {
		super(color, Color.RED);
		_renderer = new ShapeRenderer();
		this.setVisible(true);
		setBounds(leftX, leftY, width, height);
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
	public void dragStart(float x, float y, int button) {
		_x0 = getX();
		_y0 = getY();
		_dragX = x;
		_dragY = y;
		_width0 = getWidth();
		_height0 = getHeight();
		
	}

	@Override
	public void drag(float x, float y, int button) {
		Gdx.app.log("Draggable", x + "," + y);
		if (Input.Buttons.LEFT == button) {
			float newHeight = Math.max(getMinHeight(), _height0 + _y0 - y);
			float newWidth = Math.max(getMinWidth(), _width0 + _x0 - y);

			setBounds(getX(), getY(), newWidth, newHeight);
		} else if (Input.Buttons.RIGHT == button) {
			setBounds(_x0 + x - _dragX, _y0 + y - _dragY, _width0, _height0);
		}
		
	}

	@Override
	public void dragStop(float x, float y, int button) {
	}


}
