package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class BalloonMenu extends BaseScreen {

    //disini kita membuat sebuah property
    private BaseActor bg;
    private BaseActor title;
    private Label text;


    //lalu kita membuah sebuah construktor
    public BalloonMenu(Game game) {
        super(game);
    }


    //memanggil method dari parent class untuk membuat sebuah backgorund dan juga sebuah title
    @Override
    protected void create() {
        bg = ActorManger.createActor(mainStage, "back.png", viewWidth / 2, viewHeight / 2);
        title = ActorManger.createActor(mainStage, "depan.png", viewWidth / 2, viewHeight / 2);

        BitmapFont font = new BitmapFont();
        LabelStyle style = new LabelStyle(font, Color.NAVY);

        text = new Label("Lets Go!!!", style);
        text.setPosition(viewWidth / 2 - text.getWidth() / 2, 100);
        text.setFontScale(2);
        text.setAlignment(Align.center);

        uiStage.addActor(text);
    }


    //membuat sebuah method turunan dari parent class
    //(baca dari atas sampe bawah)
    @Override
    protected void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new BalloonLevel(game));

        return false;
    }
}
