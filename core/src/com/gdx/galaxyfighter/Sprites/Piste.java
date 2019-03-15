package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Scenes.Hud;

public class Piste extends InteractiveTileObject {
    public Piste(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(GalaxyFighter.PISTE_BIT);
    }

    @Override
    public void OnHit() {
        Gdx.app.log("Piste","Collision");
        //On ajoute 50 000 point aux score.
        Hud.ajoutScore(25000);
    }
}
