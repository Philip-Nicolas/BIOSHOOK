package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

/**
 * The main GameLevel3 class for BIOSHOOK™, Level 1
 *
 * @author phil.nic
 */
public class GameLevel2 extends ApplicationAdapter implements Disposable {
    private SpriteBatch batch;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMap map;
    private World world;
    private Player player;
    private RBCs rbcs;
    private GlucoseTransporters glucoseTransporters;
    private Proteins proteins;
    private GameHUD hud;
    private Antigens antigens;
    private FPSLogger logger;
    private float zoom;
    private float physicsStep;
    private float cameraX;
    private float cameraY;

    /**
     * Does all the necessary camera operation.
     */
    private void moveCamera () {
        if (Gdx.input.isKeyPressed (Input.Keys.O) && zoom > 0.5f) {
            zoom *= 0.9f;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.L) && zoom < 5f) {
            zoom /= 0.9f;
        }

        if (cameraX != player.getX () * 32f) {
            cameraX += (player.getX () * 32f - cameraX) * 0.1;
        }

        if (cameraY != player.getY () * 32f) {
            cameraY += (player.getY () * 32f - cameraY) * 0.2;
        }

        camera.position.set (cameraX, cameraY, 1f);
        camera.zoom = zoom;

    }

    /**
     * Controls the physics step based on user input.
     */
    private void physicsStepControl () {
        if (Gdx.input.isKeyPressed (Input.Keys.C) && physicsStep > 1) {
            physicsStep -= 1f;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.X)) {
            physicsStep = 60;
        }

        if (Gdx.input.isKeyPressed (Input.Keys.Z) && physicsStep < 120) {
            physicsStep += 1f;
        }
    }

    private void doSteps () {
        rbcs.step (player, zoom);
        glucoseTransporters.step (player, zoom);
        proteins.step (player, zoom);
        antigens.step (player, zoom);
        player.step (map);
    }

    /**
     * Makes sure aspect ratio is correct when canvas is resized.
     *
     * @param width  Width of something.
     * @param height Height of something.
     */
    @Override
    public void resize (int width, int height) {
        super.resize (width, height);
        camera.setToOrtho (false, Gdx.graphics.getWidth (), Gdx.graphics.getHeight ());
        camera.position.set (player.getX () * 32f, player.getY () * 32f, 1f);
    }

    /**
     * Initializer for the GameLevel3.
     */
    @Override
    public void create () {
        AssetManager assetManager = new AssetManager ();
        logger = new FPSLogger ();

        zoom = 0.5f;
        physicsStep = 60;
        camera = new OrthographicCamera ();
        camera.setToOrtho (false, Gdx.graphics.getWidth (), Gdx.graphics.getHeight ());
        camera.zoom = zoom;
        batch = new SpriteBatch ();
        world = new World (new Vector2 (0f, 0f), true);
        world.setContactListener (new PlayerContactListener ());

        assetManager.setLoader (TiledMap.class, new TmxMapLoader (new InternalFileHandleResolver ()));
        assetManager.load ("maps/colon/colon.tmx", TiledMap.class);
        assetManager.finishLoading ();

        map = assetManager.get ("maps/colon/colon.tmx");

        new TMXToBox2d (world, map).populateBox2dWorld ();

        player = new Player (world, map, 1, 2, "lymphocyte");
        rbcs = new RBCs (world, map, 200);
        glucoseTransporters = new GlucoseTransporters (world, map, 20);
        proteins = new Proteins (world, map, 30);
        antigens = new Antigens (world, map, 40, 0.7f, "brown");
        hud = new GameHUD (player);

        renderer = new HexagonalTiledMapRenderer (map, 1f);

        BionicMaps maps = new BionicMaps ();
    }

    /**
     * Runs every frame. Used for GameLevel3 functions and drawing.
     */
    @Override
    public void render () {
        logger.log ();
        Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);

        physicsStepControl ();
        doSteps ();
        moveCamera ();

        camera.update ();

        renderer.setView (camera);
        renderer.render ();
        batch.setProjectionMatrix (camera.combined);
        batch.begin ();
        {
            rbcs.draw (batch);
            glucoseTransporters.draw (batch);
            proteins.draw (batch);
            player.draw (batch);
            antigens.draw (batch);

            hud.draw (batch, camera);
        }
        batch.end ();

        world.step (1 / physicsStep, 6, 2);
    }

    /**
     * Destructor for the GameLevel3.
     */
    @Override
    public void dispose () {
        batch.dispose ();
    }
}