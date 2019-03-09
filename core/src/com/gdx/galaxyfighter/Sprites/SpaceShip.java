package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
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

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
