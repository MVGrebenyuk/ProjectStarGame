package com.mygdx.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ScaledButton;
import com.mygdx.game.math.Rect;
import com.mygdx.game.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {

    private GameScreen game;
    private Game game1;
    private static final float MARGIN = 0.3f;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game1 = game;
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void action() {
        game1.setScreen(new GameScreen(game1));
    }
}
