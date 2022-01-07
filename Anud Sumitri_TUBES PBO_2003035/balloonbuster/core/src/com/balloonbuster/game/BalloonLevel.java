package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class BalloonLevel extends BaseScreen {

    //membuat sebuah property
    protected final int mapWidth = 1260;
    protected final int mapHeight = 960;

    private BaseActor background;

    private float spawnTimer;
    private float spawnInterval;

    private int popped;
    private int escaped;
    private int clickCount;

    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;

    private int popToWin = 100;
    private int escapedToFail = 15;

    //membuah sebuah constructor dengan parent class yaitu Game, didalam android studio class Game sudah tersedia otomatis
    public BalloonLevel(Game game) {
        super(game);
    }

    //menurunkan sebuah method create dari parent class
    @Override
    protected void create() {

        //disini kita membuat sebuah variabel dari sebuah class BaseActor
        background = new BaseActor();

        //lalu kita akan memasukan gambar untuk background, yang ada di dalam asset
        background.setTexture(new Texture(Gdx.files.internal("back.png") ));
        background.setOrigin(background.getWidth() / 2, background.getHeight() / 2);
        background.setPosition((viewWidth / 2) - background.getOriginX(),(viewHeight / 2) - background.getOriginY());
        background.setVisible(true);
        mainStage.addActor(background);

        //spawn objek slime akan dimunculkan setiap saat
        spawnTimer = 0;
        //dan berinterfal 0,5 detik
        spawnInterval = 0.5f;

        //membuat sebuah warna font
        BitmapFont font = new BitmapFont();
        LabelStyle style  = new LabelStyle(font, Color.NAVY);

        //mengset poped = 0;
        popped = 0;

        //membuat sebuah label dengan kata "popped" : 0 dan mengset posisi dan font nya di bagian ini
        poppedLabel = new Label("Popped: 0", style);
        poppedLabel.setFontScale(2);
        poppedLabel.setPosition(20, 440);
        uiStage.addActor(poppedLabel);

        //membuat sebuah label dengan nama escaped untuk menghitung jumlah objek slime yang kelewat atau tidak ditekan
        //sama seperti label popped disini kita mengset font dan posisinya
        escaped = 0;
        escapedLabel = new Label("Escaped: 0", style);
        escapedLabel.setFontScale(2);
        escapedLabel.setPosition(220, 440);
        uiStage.addActor(escapedLabel);


        //membuat sebuah label dengan nama clickCount dimana label ini berfungsi untuk menghitung jumlah objek yang kita klik
        //disini sama seperti label label yang lainya kita mengset font size dan juga posisi dari labelnya
        clickCount = 0;
        hitRatioLabel = new Label("Hit ratio: ---", style);
        hitRatioLabel.setFontScale(2);
        hitRatioLabel.setPosition(420,440);
        uiStage.addActor(hitRatioLabel);
    }


    //untuk ovveride dibawah ini kita menurunkan method update
    //di method ini kita akan mengupdate pooped, escaped dan hit ratio.
    @Override
    protected void update(float dt) {
        spawnTimer += dt;

            //jika spawsn timer kurang dari spawn iterval maka spawntimer dikrnagi dengan spawninterval
        if (spawnTimer > spawnInterval) {

            spawnTimer -= spawnInterval;

            //maka disini kita akan menambahkan sebuah objek baru dengan nama b
            final Balloon b = new Balloon(this);
            b.addListener(

                    //jika objek slime di klik maka akan memunculkan objek slime yang baru secara terus menerus
                    new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            popped++;
                            //dan objek yang diklik akan otomatis terhapus
                            b.remove();
                            return true;
                        }
                    }
            );
            mainStage.addActor(b);
        }

        //popedLabel akan terupdate jika kita mengklik slime yang ada dan escapeLabel akan terhitung jika
        //ada slime yang terlewatkan
        poppedLabel.setText("Popped: " + popped);
        escapedLabel.setText("Escaped: " + escaped);

        //disini kita membuat sebuah coding untuk menghitung ratio klik yang kita punya
        if (clickCount > 0) {
            int percent = (int) (100 * popped / clickCount);
            hitRatioLabel.setText("Hit ratio: " + percent + "%");
        }

        if (popped >= popToWin) {
            game.setScreen(new BalloonMenu(game));
        }

        if (escaped >= escapedToFail) {
            game.setScreen(new BalloonMenu(game));
        }
    }

    public void escapedInc() {
        escaped++;
        clickCount++;
    }
}
