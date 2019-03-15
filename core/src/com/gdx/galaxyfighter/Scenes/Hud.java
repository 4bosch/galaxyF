package com.gdx.galaxyfighter.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.galaxyfighter.GalaxyFighter;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer life;
    private static Integer score;
    private Integer niveau;

    Label lifeLabel;
    private static Label scoreLabel;
    Label niveauLabel;
    Label lifeStrLabel;
    Label scoreStrLabel;
    Label niveauStrLabel;

    public Hud(SpriteBatch sb){
        life = 3;
        score = 0;
        niveau = 1;

        viewport = new FitViewport(GalaxyFighter.V_WIDTH, GalaxyFighter.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        lifeLabel = new Label(String.format("%02d", life), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        niveauStrLabel = new Label("NIVEAU", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeStrLabel = new Label("VIE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreStrLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        niveauLabel = new Label(String.format("%02d", niveau), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(lifeStrLabel).expandX().padTop(10);
        table.add(niveauStrLabel).expandX().padTop(10);
        table.add(scoreStrLabel).expandX().padTop(10);
        table.row();
        table.add(lifeLabel).expandX();
        table.add(niveauLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    public static void ajoutScore(int valeur){
        score += valeur;
        scoreLabel.setText(String.format("%04d", score));

    }

    public static int returnScore(){
        return score;
    }

    public void perdVie(){
        life--;
        lifeLabel.setText(String.format("%02d", life));
    }

    public int returnVie(){
        return life;
    }

    @Override
    public void dispose(){
        stage.dispose();
    }
}
