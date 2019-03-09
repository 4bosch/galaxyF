package com.gdx.galaxyfighter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.galaxyfighter.Ecrans.EcranJeu;

public class GalaxyFighter extends Game {
    public SpriteBatch batch;

    public static final int V_WIDTH = 208;
    public static final int V_HEIGHT = 400;
    public static final float PPM = 100;
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
