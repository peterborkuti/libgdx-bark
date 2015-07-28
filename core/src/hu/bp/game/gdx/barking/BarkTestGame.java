package hu.bp.game.gdx.barking;

import com.badlogic.gdx.Game;

public class BarkTestGame extends Game {

	@Override
	public void create() {
		this.setScreen(new BarkTestScreen());

	}

	@Override
	public void render () {
		super.render();
	}

}
