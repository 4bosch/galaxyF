package com.gdx.galaxyfighter.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.galaxyfighter.GalaxyFighter;
import com.gdx.galaxyfighter.Sprites.Asteroide;
import com.gdx.galaxyfighter.Sprites.Bonus1;
import com.gdx.galaxyfighter.Sprites.Bonus2;
import com.gdx.galaxyfighter.Sprites.Bonus3;
import com.gdx.galaxyfighter.Sprites.BordsMap;
import com.gdx.galaxyfighter.Sprites.Piste;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //creation des body et des fixture des asteroides
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           new Asteroide(world, map, rect);
        }

        //creation du body et de la fixture de la piste de fin
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Piste(world, map, rect);
        }

        //creation des body et des fixture des bonus1
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bonus1(world, map, rect);
        }

        //creation des body et des fixture des bonus2
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bonus2(world, map, rect);
        }
        //creation des body et des fixture des bonus3
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Bonus3(world, map, rect);
        }

        //creation des body et des fixture de la piste d'atterissage
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Piste(world, map, rect);
        }

        //creation des body et des fixture des bords de la map
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BordsMap(world, map, rect);
        }
    }
}
