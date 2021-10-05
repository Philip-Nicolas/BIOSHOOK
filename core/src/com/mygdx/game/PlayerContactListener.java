package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Philip-Nicolas on 2017-06-02.
 * <p>
 * Detects collisions with the player, and adds stats accordingly.
 *
 * @author phil.nic
 */
class PlayerContactListener implements ContactListener {


    /**
     * Class constructor.
     */
    PlayerContactListener () {
        super ();
    }

    /**
     * Called when contact occurs between two bodies.
     *
     * @param contact The contact information.
     */
    @Override
    public void beginContact (Contact contact) {
        /* Somehow, FixtureA always seems to be the player, which is why it is the only one to be checked. */
        if (contact.getFixtureA ().getBody ().getUserData () instanceof Player) {
            if ((contact.getFixtureB ().getBody ().getUserData () instanceof AutomatedSimpleOrganicCellWithResources)) {
                ((Player) contact.getFixtureA ().getBody ().getUserData ()).incrementResources (((AutomatedSimpleOrganicCellWithResources) contact.getFixtureB ().getBody ().getUserData ()).getResourceType (), ((AutomatedSimpleOrganicCellWithResources) contact.getFixtureB ().getBody ().getUserData ()).giveResources ());
            }
            if ((contact.getFixtureB ().getBody ().getUserData () instanceof NiceVirus)) {
                ((NiceVirus) contact.getFixtureB ().getBody ().getUserData ()).toBeKilled ();
                ((Player) contact.getFixtureA ().getBody ().getUserData ()).incrementPts ();
            }
            if ((contact.getFixtureB ().getBody ().getUserData () instanceof MeanVirus)) {
                ((MeanVirus) contact.getFixtureB ().getBody ().getUserData ()).toBeKilled ();
                ((Player) contact.getFixtureA ().getBody ().getUserData ()).takeDamage ();
            }
        }
    }

    /**
     * Called when contact ends between two bodies.
     *
     * @param contact The contact information.
     */
    @Override
    public void endContact (Contact contact) {
    }

    /**
     * From interface declaration:
     * This is called after a contact is updated. This allows you to inspect a contact before it goes to the solver. If you are
     * careful, you can modify the contact manifold (e.g. disable contact). A copy of the old manifold is provided so that you can
     * detect changes. Note: this is called only for awake bodies. Note: this is called even when the number of contact points is
     * zero. Note: this is not called for sensors. Note: if you set the number of contact points to zero, you will not get an
     * EndContact callback. However, you may get a BeginContact callback the next step.
     *
     * @param contact     The contact information.
     * @param oldManifold I'm not quite sure what this does.
     * @see ContactListener documentation for more info
     */
    @Override
    public void preSolve (Contact contact, Manifold oldManifold) {
    }

    /**
     * From interface declaration:
     * This lets you inspect a contact after the solver is finished. This is useful for inspecting impulses. Note: the contact
     * manifold does not include time of impact impulses, which can be arbitrarily large if the sub-step is small. Hence the
     * impulse is provided explicitly in a separate data structure. Note: this is only called for contacts that are touching,
     * solid, and awake.
     *
     * @param contact The contact information.
     * @param impulse I'm not quite sure what this does.
     * @see ContactListener documentation for more info
     */
    @Override
    public void postSolve (Contact contact, ContactImpulse impulse) {
    }
}