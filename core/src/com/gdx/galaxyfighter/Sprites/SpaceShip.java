package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.galaxyfighter.GalaxyFighter;

public class SpaceShip extends Sprite {
    public World world;
    public Body b2body;

    public SpaceShip(World world){
        this.world = world;
        defineSpaceShip();
    }

    public void defineSpaceShip(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(GalaxyFighter.V_WIDTH / 2 / GalaxyFighter.PPM, 3 / GalaxyFighter.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GalaxyFighter.PPM);
        //Autoriser collision
        fdef.filter.categoryBits = GalaxyFighter.SPACESHIP_BIT;
        fdef.filter.maskBits = GalaxyFighter.DEFAULT_BIT | GalaxyFighter.ASTEROIDE_BIT |GalaxyFighter.BONUS1_BIT|GalaxyFighter.BONUS2_BIT|GalaxyFighter.BONUS3_BIT|GalaxyFighter.PISTE_BIT|GalaxyFighter.BORDSMAP_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        //Creation d'une barre aux dessus du vaisseau qui nous permettra par la suite de détecter les collisions.
        //La barre ne peux pas crée de collisions elle sert uniquement de capteur
        //Creation d'une barre horizontale
        EdgeShape head_spaceship = new EdgeShape();
        head_spaceship.set(new Vector2(-4 / GalaxyFighter.PPM, 0), new Vector2(4 / GalaxyFighter.PPM, 0));
        fdef.shape = head_spaceship;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head_spaceship");

        //Creation d'une barre verticale
        EdgeShape head_spaceship2 = new EdgeShape();
        head_spaceship2.set(new Vector2(0, -4 / GalaxyFighter.PPM), new Vector2(0, 4 / GalaxyFighter.PPM));
        fdef.shape = head_spaceship2;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("head_spaceship2");
    }
}
