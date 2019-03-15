package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Scenes.Hud;

public class Bonus1 extends InteractiveTileObject {
    public Bonus1(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GalaxyFighter.BONUS1_BIT);

    }

    @Override
    public void OnHit() {
        Gdx.app.log("Bonus1","Collision");
        //On détruit le bonus après contact
        setCategoryFilter(GalaxyFighter.DESTROYED_BIT);
        //On enleve l'élément du décor
        getCell().setTile(null);
        //On ajoute 1000 points aux score
        Hud.ajoutScore(1000);

    }

}
