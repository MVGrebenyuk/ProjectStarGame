package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.ScaledButton;
import com.mygdx.game.math.Rect;

public class ButtonSound extends ScaledButton {

    public ButtonSound(TextureAtlas atlas) {
        super(atlas.findRegion("soundon"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setTop(worldBounds.getTop() - 0.03f);
        setLeft(worldBounds.getLeft() + 0.03f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    @Override
    public void action() {
    }
}

