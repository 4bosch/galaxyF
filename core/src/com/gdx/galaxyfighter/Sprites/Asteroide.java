package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.galaxyfighter.Ecrans.EcranJeu;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Scenes.Hud;

public class Asteroide extends InteractiveTileObject{
    public Asteroide(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GalaxyFighter.ASTEROIDE_BIT);
    }

    @Override
    public void OnHit() {
        Gdx.app.log("Asteroide","Collision");

        //Si collision avec asteroide on enleve 500 point, le score ne peux pas être négatif

        if(Hud.returnScore() >= 500){

            Hud.ajoutScore(-500);

        }



    }
}
