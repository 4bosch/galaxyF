package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Piste extends InteractiveTileObject {
    public Piste(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
