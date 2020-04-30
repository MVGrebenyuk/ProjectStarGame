package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import BaseMenu.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture UFO;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 touch;
    private Vector2 point;
    private Vector2 dot;

    @Override
    public void show() {
        super.show();
        img = new Texture("SpaceTexture.jpg");
        UFO = new Texture( "UFO.png");
        pos = new Vector2(0,0);
        point = new Vector2();
        v = new Vector2(0, 0);
        touch = new Vector2();
        dot = new Vector2();


    }

   public void calculatedMovie(){
       Vector2 c = new Vector2();
       c.set((point.cpy().sub(pos)));//расстояние от позиции до точки
        if(dot.x == point.x & dot.y == point.y) {
            dot.sub(pos); // длина вектора
            dot.len(); // направление
            dot.nor(); // скорость
            v = dot; //шаг
        } else if (c.x <= v.x & c.y <= v.y) { //ВЕРОЯТНО, ТУТ ОШИБКА/
            pos.set(point);
        } else {
        }

    }


    @Override
    public void render(float delta) {
        calculatedMovie();
        super.render(delta);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(UFO, pos.x, pos.y, 200, 200);
        if(pos.x != point.x & point.y != pos.y){
            pos.add(v);
            batch.draw(UFO, pos.x, pos.y, 200, 200);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        point.set(touch);
        dot.set(touch);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
