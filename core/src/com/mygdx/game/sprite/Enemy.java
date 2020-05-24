package com.mygdx.game.sprite;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.base.Sprite;

public class Enemy extends Sprite {

    private static final float SIZE = 0.15f;
    private static final float MARGIN = 0.25f;
    private Rect worldBounds;
    private Vector2 v;


    public Enemy(TextureAtlas atlas) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        v = new Vector2(0, -0.1f);
    }


    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SIZE);
        setBottom(worldBounds.getTop() - MARGIN);
    }
}
