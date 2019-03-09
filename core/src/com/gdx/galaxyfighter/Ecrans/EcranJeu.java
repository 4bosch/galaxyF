package com.gdx.galaxyfighter.Ecrans;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Scenes.Hud;
import com.gdx.galaxyfighter.Sprites.SpaceShip;
import com.gdx.galaxyfighter.Tools.B2WorldCreator;

import javax.swing.GroupLayout;

public class EcranJeu implements Screen {
    private GalaxyFighter jeu;

    private OrthographicCamera cam;
    private Viewport gamePort;
    private Hud hud;

    //tilded map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private SpaceShip player;

    public EcranJeu(GalaxyFighter jeu){
        this.jeu = jeu;
        //creation de la camera qui suivera le vaisseau
        cam = new OrthographicCamera();
        //creation d'un viewport qui conservera son ratio et qui ajoutera des bars si besoin
        gamePort = new FitViewport(GalaxyFighter.V_WIDTH / GalaxyFighter.PPM, GalaxyFighter.V_HEIGHT / GalaxyFighter.PPM, cam);
        //creation du Hud pour afficher le niveau, le score et les vies
        hud = new Hud(jeu.batch);

        //charge la carte et mise en place du rendu de la carte
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GalaxyFighter.PPM);

        //on change la position initialle de la camera sur le debut de la carte centre au milieu
        cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        player = new SpaceShip(world);

        new B2WorldCreator(world, map);
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0, 0.1f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= -1)
            player.b2body.applyLinearImpulse(new Vector2(0, -0.1f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -1)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        cam.position.y = player.b2body.getPosition().y;
        cam.update();
        renderer.setView(cam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        //nettoie l'ecran de jeu avec du noir
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //fait le rendu de la carte
        renderer.render();
        //fait le rendu de Box2DDebugLines
        b2dr.render(world, cam.combined);

        jeu.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        renderer.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
