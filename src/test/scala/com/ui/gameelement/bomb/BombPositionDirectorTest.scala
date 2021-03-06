package com.ui.gameelement.bomb

import org.scalatest.FunSuite
import java.awt.Point
import com.GameConfiguration
import GameConfiguration.currentConfig

class BombPositionDirectorTest extends FunSuite {

    test("given current location of invader bomb then nextlocation "){

        val currentPos = new Point(0,0)

        assertResult(new Point(0, 0 + currentConfig.bombVelocity)){
            BombPositionDirector.nextPosition(currentPos)
        }
    }

}
