package com.gdx.galaxyfighter.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Asteroide extends InteractiveTileObject{
    public Asteroide(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
