package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DraggableRectangleActor extends AbstractDraggableActor {

	private ShapeRenderer _renderer;

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
	public void drag(float x, float y, int button) {
		if (Input.Buttons.LEFT == button) {
			Gdx.app.log("Draggable", "LBT " + x + "," + y);
			float newHeight = Math.max(getMinHeight(), actorState.height + y -actorState.dragY);
			float newWidth = Math.max(getMinWidth(), actorState.width + x - actorState.dragX);

			getFakeActor().setBounds(getX(), getY(), newWidth, newHeight);
		} else if (Input.Buttons.RIGHT == button) {
			Gdx.app.log("Draggable", "RBT " + x + "," + y);
			getFakeActor().setBounds(actorState.x0 + x - actorState.dragX, actorState.y0 + y - actorState.dragY, actorState.width, actorState.height);
		}
	}



}
