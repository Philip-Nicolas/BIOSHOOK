package com.mygdx.game;

/**
 * Created by Philip-Nicolas on 2017-06-05.
 * <p>
 * Generic class for levels.
 *
 * @author phil.nic
 */
abstract class Level {
    Player player;
    int timeLeft;

    /**
     * Class Constructor.
     * @param player The player to base the level around.
     */
    Level (Player player) {
        this.player = player;
    }

    /**
     * Check losing conditions.
     * @return Whether the player has lost.
     */
    boolean playerHasLost () {
        if (player.getHealth () <= 0) {
            return true;
        } else if (timeLeft <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Check winning conditions.
     * @return Whether the player has won.
     */
    abstract boolean playerHasWon ();
}