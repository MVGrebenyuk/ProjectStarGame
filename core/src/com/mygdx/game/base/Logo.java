package ru.geekbrains.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Logo extends Sprite {

    public Logo(TextureRegion region) {
        super(new TextureRegion(region));
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
