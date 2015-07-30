package hu.bp.bark.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TestActor extends Actor {

	private ShapeRenderer _renderer;
	private Rectangle _bound;
	private Color _color;
	private final Color hitColor = Color.RED;
	private final Color missColor = Color.GREEN;
	private Matrix4 translation;

	private class TestClickListener extends ClickListener {

		@Override
		public void enter(InputEvent event, float x, float y, int pointer,
				Actor fromActor) {
			super.enter(event, x, y, pointer, fromActor);
			_color = hitColor;
		}

		@Override
		public void exit(InputEvent event, float x, float y, int pointer,
				Actor toActor) {
			super.exit(event, x, y, pointer, toActor);
			_color = missColor;
		}

		
		
	}

	public TestActor() {
		_renderer = new ShapeRenderer();
		_bound = new Rectangle(0, 0, 30, 20);
		setBounds(_bound.x, _bound.y, _bound.width, _bound.height);
		_color = missColor;
		translation = new Matrix4();
		translation.idt();
		translation.translate(100, 50, 0);
		this.addListener(new TestClickListener());
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		_renderer.setTransformMatrix(batch.getTransformMatrix());

		//_renderer.translate(100, 50, 0);
		//_renderer.scale(2, 2, 0);
		setBounds(100, 50, getWidth(), getHeight());

		_renderer.begin(ShapeType.Filled);
		_renderer.setColor(_color);
		_renderer.rect(getX(), getY(), getWidth(), getHeight());
		_renderer.end();

		batch.begin();
	}

}
