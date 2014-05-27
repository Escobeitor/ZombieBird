package com.kilobolt.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kilobolt.TweenAccessors.SpriteAccessor;
import com.kilobolt.Helpers.AssetLoader;
import com.kilobolt.ZombieBird.ZBGame;

public class SplashScreen implements Screen {

    private TweenManager manager;
    private SpriteBatch batcher;
    private OrthographicCamera cam;
    private Sprite sprite;
    private ZBGame game;

    public SplashScreen(ZBGame game) {
        this.game = game;
        
    	float screenWidth = Gdx.graphics.getWidth();
	    float screenHeight = Gdx.graphics.getHeight();      
	    float gameWidth = 136;
    	float gameHeight = screenHeight / (screenWidth / gameWidth);
    	cam = new OrthographicCamera();
    	
        cam.setToOrtho(true, 136, gameHeight);
    	batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
    }

    @Override
    public void show() {
    	

        
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .7f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
                - (sprite.getHeight() / 2));
        setupTween();
        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        batcher.enableBlending();
        //sprite.draw(batcher);
        
//    	batcher.begin();
        
        AssetLoader.shadow.draw(batcher, "AAAAAAAAAAAA",23,76);
        AssetLoader.font.draw(batcher, "AAAAAAAAAAAA",23,76);
        
        batcher.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}