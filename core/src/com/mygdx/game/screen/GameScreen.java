package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.MainShip;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;


public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private MainShip hero;
    private TextureAtlas atlas;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        hero = new MainShip(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        hero.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        hero.touchDown(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        hero.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        hero.draw(batch);
        batch.end();
    }
}
