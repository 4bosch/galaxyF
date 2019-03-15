package com.gdx.galaxyfighter.Ecrans;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Scenes.Hud;
import com.gdx.galaxyfighter.Sprites.SpaceShip;
import com.gdx.galaxyfighter.Tools.B2WorldCreator;
import com.gdx.galaxyfighter.Tools.WorldContactListener;


public class EcranJeu implements Screen {
    private GalaxyFighter jeu;

    //rendu graphique variables
    private OrthographicCamera cam;
    private Viewport gamePort;
    private Hud hud;
    private float maxScrolling;

    //tilded map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private SpaceShip player;
    private Sprite spriteSpaceShip;

    //Constructeur de l'ecran de jeu prenant en entree un objet niveau afin de choisir la carte et le niveau de difficulte adequat
    public EcranJeu(GalaxyFighter jeu, float maxScrolling){
        this.jeu = jeu;
        //creation de la camera qui suivera le vaisseau
        cam = new OrthographicCamera();
        //creation d'un viewport qui conservera son ratio et qui ajoutera des bars si besoin
        gamePort = new FitViewport(GalaxyFighter.V_WIDTH , GalaxyFighter.V_HEIGHT , cam);
        //creation du Hud pour afficher le niveau, le score et les vies
        hud = new Hud(jeu.batch);
        //creation d'une variable permettant d'arreter le scrolling a la fin du niveau
        this.maxScrolling = maxScrolling;

        //charge la carte et mise en place du rendu de la carte
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 );

        //on change la position initialle de la camera sur le debut de la carte centre au milieu
        cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //creation d'un objet world qui n'a pas de gravite
        world = new World(new Vector2(0,0), true);
        //ajout de debug ou niveau des box de box2d
        b2dr = new Box2DDebugRenderer();
        //creation de l'objet physique qui representera notre vaisseau
        player = new SpaceShip(world);
        //creation du sprite du vaisseau
        spriteSpaceShip = new Sprite(new Texture("redship1.png"));
        //on relie le sprite au vaisseau
        player.b2body.setUserData(spriteSpaceShip);
        //creer le monde dans lequel le vaisseau evolura
        new B2WorldCreator(world, map);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show() {

    }

    //Cette methode gere les deplacements du vaisseau. En fonction de la direction une force lui est applique
    //avec une vitesse maximale.
    public void handleInput(float dt){
        float maxVel = 100f;
        float minVel = -100f;
        float plusVel = 50f;
        float minusVel = -50f;
        float shipVelX = player.b2body.getLinearVelocity().x;
        float shipVelY = player.b2body.getLinearVelocity().y;

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= maxVel)
            player.b2body.applyLinearImpulse(new Vector2(0, plusVel), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= minVel)
            player.b2body.applyLinearImpulse(new Vector2(0, minusVel), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= maxVel)
            player.b2body.applyLinearImpulse(new Vector2(plusVel, 0), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= minVel)
            player.b2body.applyLinearImpulse(new Vector2(minusVel, 0), player.b2body.getWorldCenter(), true);
        else if(shipVelX > 0 || shipVelY > 0 || shipVelX < 0 || shipVelY < 0)
            player.b2body.setLinearVelocity( shipVelX / 1.04f, shipVelY / 1.04f);
        System.out.println("Velocity x = " + shipVelX + " | y = " + shipVelY);
    }

    //Cette methode permet de limiter le joueur dans la zone de la camera. Il ne peut donc ainsi sortir du champ de la camera.
    //On applique une force oppose au mouvement du joueur afin de le stopper.
    //Cette force est un peu plus eleve de la vitesse du joueur pour le repousser.
    public void limitPlayer()
    {
        //La camera se situant au milieu du viewport on ajoute ou retire la moite de la hauteur du viewport
        //afin de se retrouver au niveau des bords.
        if(player.b2body.getPosition().y > cam.position.y - 5 + GalaxyFighter.V_HEIGHT / 2)
            player.b2body.applyLinearImpulse(new Vector2(0, player.b2body.getLinearVelocity().y * -1 - 20), player.b2body.getWorldCenter(), true);
        if(player.b2body.getPosition().y < cam.position.y + 10 - GalaxyFighter.V_HEIGHT / 2)
            player.b2body.applyLinearImpulse(new Vector2(0, player.b2body.getLinearVelocity().y * -1 + 50), player.b2body.getWorldCenter(), true);
    }

    //Cette methode se charge de mettre a jour tous les elements affiches
    public void update(float dt){
        handleInput(dt);
        //Indique le nombre de fois le moteur physique peut calculer par seconde
        world.step(1/60f, 6, 2);
        //Permet de stopper le scrolling a la fin du niveau
        if(cam.position.y < maxScrolling)
            cam.translate(0, 0.5f);
        //cette methode limite le joueur dans le champs de la camera
        limitPlayer();
        spriteSpaceShip = (Sprite) player.b2body.getUserData();
        spriteSpaceShip.setX(player.b2body.getPosition().x - 8);
        spriteSpaceShip.setY(player.b2body.getPosition().y - 8);
        //actualise la camera afin qu'elle affiche les changements effectue auparavant
        cam.update();
        renderer.setView(cam);
    }

    //Cette methode herite de la classe Game gere le rendu de l'ecran
    //Elle est appele en boucle environ toute les 0.01 secondes
    //Le delta est le nombre de secondes entre chaque frame/image
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
        //fait le rendu du sprite du vaisseau
        jeu.batch.begin();
        spriteSpaceShip.draw(jeu.batch);
        jeu.batch.end();
    }

    //Cette methode herite de la classe Game gere les ajustements de taille lorsque la fenetre de l'application change de taille
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    //Cette methode herite de la classe Game est appele lorsque le jeu est mis en arriere plan ou qu'une fonction d'android le met
    //au second plan comme par exemple un appel entrant
    @Override
    public void pause() {

    }

    //Cette methode herite de la classe Game est appele apres un appel de pause lors de la reouverture de la fenetre
    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    //Cette methode herite de la classe Game gere le nettoyage de la memoire a la fermeture du processus de l'application
    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        renderer.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
