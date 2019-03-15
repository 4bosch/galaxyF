package com.gdx.galaxyfighter.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.galaxyfighter.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();

        Fixture fixB = contact.getFixtureB();

        //Afin d'utiliser le capteur barre "horizontale", et savoir on entre en collision dans quoi

        if(fixA.getUserData() == "head_spaceship" || fixB.getUserData() == "head_spaceship"){

            Fixture head_spaceship = fixA.getUserData() == "head_spaceship" ? fixA : fixB;

            Fixture object = head_spaceship == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){

                ((InteractiveTileObject) object.getUserData()).OnHit();
            }

        }

        //Afin d'utiliser le capteur barre "horizontale", et savoir on entre en collision dans quoi

        if(fixA.getUserData() == "head_spaceship2" || fixB.getUserData() == "head_spaceship2"){

            Fixture head_spaceship2 = fixA.getUserData() == "head_spaceship2" ? fixA : fixB;

            Fixture object = head_spaceship2 == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){

                ((InteractiveTileObject) object.getUserData()).OnHit();
            }

        }



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
