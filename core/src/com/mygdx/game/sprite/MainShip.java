package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

import java.util.Timer;
import java.util.TimerTask;


public class MainShip extends Sprite {

    private static final float SIZE = 0.15f;
    private static final float MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private final Vector2 v0;
    private final Vector2 v;

    private int leftPointer;
    private int rightPointer;

    private boolean pressedLeft;
    private boolean pressedRight;

    private Rect worldBounds;

    private boolean flag;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV;
    private Sound shot;

    Timer timer = new Timer();


    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, 0.5f);
        v0 = new Vector2(0.5f, 0);
        v = new Vector2();
        leftPointer = INVALID_POINTER;
        rightPointer = INVALID_POINTER;
        shot = Gdx.audio.newSound(Gdx.files.internal("sounds/gun1.mp3"));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                shoot();
            };
        }, 0L,500);



    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SIZE);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (getLeft() < worldBounds.getLeft()) {
            stop();
            setLeft(worldBounds.getLeft());
        }
        if (getRight() > worldBounds.getRight()) {
            stop();
            setRight(worldBounds.getRight());
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    public void autoShoot(){
        if(flag = true){
            shoot();
        }
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.01f, worldBounds, 1);
        shot.play();
    }
}
