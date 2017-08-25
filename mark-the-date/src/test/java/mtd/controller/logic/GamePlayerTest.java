/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.logic;

import mtd.controller.logic.GamePlayer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Sheridan
 */
public class GamePlayerTest {

    private GamePlayer gp1;
    private GamePlayer gp2;

    public GamePlayerTest() {
        gp1 = new GamePlayer();
        gp2 = new GamePlayer();
    }

    @Test
    public void guiThreadStartsCorrectly() {
        //One main thread and GUI thread.
        int threads = gp1.play();
        assertTrue(threads == 2);
    }

}
