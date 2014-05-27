package com.kilobolt.ZombieBird;
import com.badlogic.gdx.Game;
import com.kilobolt.Helpers.AssetLoader;
import com.kilobolt.Screens.GameScreen;
import com.kilobolt.Screens.SplashScreen;

public class ZBGame extends Game{

	@Override
	public void create() {
		System.out.println("ZBGame Created!");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	
}
