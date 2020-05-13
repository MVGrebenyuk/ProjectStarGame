package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

import ru.geekbrains.math.Rect;


public class MainShip extends Sprite {

    private static final float V_LEN = 0.001f;

    private Vector2 touch;
    private Vector2 v;
    private Vector2 common;
    private Vector2 c2;
    private Rect worldBounds;
    private final float BOTTOM = -0.43f;

    public MainShip(TextureAtlas ship) {
        super(ship.findRegion("main_ship"));
        super.regions[0].setRegionWidth(regions[0].getRegionWidth()/2);
        worldBounds = new Rect();
        touch = new Vector2(0, 0);
        common = new Vector2();
        c2 = new Vector2(0, 0);
        v = new Vector2();
    }


    @Override
    public void setHeightProportion(float height) {
        super.setHeightProportion(height);
    }



    @Override
    public void update(float delta) {
        if(touch.x == 0 & touch.y == 0) {
            pos.set(0, BOTTOM);
        }
        common.set(touch);
        if (common.sub(pos).len() > V_LEN) {
            pos.add(v.x*4, 0);
        } else {
            pos.set(touch);
            v.setZero();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) { ;
        this.worldBounds = worldBounds;
        setHeightProportion(0.1f);
        pos.set(touch);
}

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos)).setLength(V_LEN);
        return false;
    }

}
