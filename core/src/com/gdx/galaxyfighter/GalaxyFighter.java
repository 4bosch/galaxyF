package com.gdx.galaxyfighter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.galaxyfighter.Ecrans.EcranJeu;

public class GalaxyFighter extends Game {
    public SpriteBatch batch;

    public static final int V_WIDTH = 208;
    public static final int V_HEIGHT = 400;
    public static final float PPM = 100;

    public static final short SPACESHIP_BIT = 2;
    public static final short BONUS1_BIT = 8;
    public static final short BONUS2_BIT = 8;
    public static final short BONUS3_BIT = 8;
    public static final short PISTE_BIT = 4;
    public static final short BORDSMAP_BIT = 4;
    public static final short ASTEROIDE_BIT = 4;
    public static final short DESTROYED_BIT = 16;
    public static final short DEFAULT_BIT = 1;


    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new EcranJeu(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
