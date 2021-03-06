package com.ui.gameelement

import java.awt.Point

import com.CollisionDetection._
import com.ui.GameElements
import com.ui.gameelement.barricade.Barricades
import com.ui.gameelement.bomb.{Bomb, FallingBombs}
import com.ui.gameelement.invader.types.CrabInvader
import com.ui.gameelement.invader.{ArmyCommander, InvaderArmy}
import com.ui.gameelement.missile.{Missile, MissilesInFlight}
import com.ui.gameelement.player.types.ShootingPlayer
import org.scalatest.FunSuite

class CollisionDetectionTest extends FunSuite {

    val screenWidth = 100
    val screenHeight = 100
    val startingPosition: Point = new Point(0, 0)


    test("given a collision detector when there is no collision then no collision is detected"){
        val invaderArmy = new InvaderArmy(ArmyCommander.formAnArmy(startingPosition))
        val barricades  = new Barricades(startingPosition)
        val missiles    = new MissilesInFlight(List(new Missile(startingPosition)))
        val bombs       = new FallingBombs(List(new Bomb(startingPosition)))
        val player      = new ShootingPlayer(startingPosition)

        val positionManager = new GameElementPosition(screenWidth, screenHeight)
        val gameElementsWithUpdatedPositions = positionManager.repositionGameElements(GameElements(invaderArmy, missiles, barricades,player, bombs))

        val collidedElements = detectCollisions(gameElementsWithUpdatedPositions)

        assert(0 == collidedElements.shotInvaders.size)
        assert(0 == collidedElements.hitBarricadesByBombs.size)
        assert(!collidedElements.isPlayerShot)

    }

    test("given a collision detector when player is hit then isPlayerShot set to true"){
        val invaderArmy = new InvaderArmy(ArmyCommander.formAnArmy(startingPosition))
        val barricades  = new Barricades(startingPosition)
        val missiles    = new MissilesInFlight(List(new Missile(startingPosition)))
        val bombs       = new FallingBombs(List(new Bomb(startingPosition)))
        val player      = new ShootingPlayer(startingPosition)

        val positionManager = new GameElementPosition(screenWidth, screenHeight)
        val gameElementsWithUpdatedPositions = positionManager.repositionGameElements(GameElements(invaderArmy, missiles, barricades,player, bombs))

        val playerPosition  = gameElementsWithUpdatedPositions.player.topLeft
        val bombHittingPlayer = new Bomb(playerPosition)

        val newPositions  = gameElementsWithUpdatedPositions.copy(droppingBombs = new FallingBombs(Seq(bombHittingPlayer)))
        val collidedElements = detectCollisions(newPositions)

        assert(collidedElements.isPlayerShot)
    }

    /**
     * "not quite" in this context means the bounding boxes are touching but have not intersected yet
     */
    test("given a missile that has not quite collided with a soldier then hasCollided returns false") {
        val invaderArmy = new InvaderArmy(List(new CrabInvader(new Point(0,0))))

        val missiles = List(new Missile(new Point(3,27)))

        assertResult(false) {
            hasCollided(missiles(0), invaderArmy.army(0))
        }
    }

    test("given a missile that has collided with a soldier then hasCollided returns true") {
        val invaderArmy = new InvaderArmy(List(new CrabInvader(new Point(0,0))))

        val missiles = List(new Missile(new Point(0,0)))

        assert(hasCollided(missiles(0), invaderArmy.army(0)))
    }

    test("given a missile that has just collided with a soldier then hasCollided returns true") {

        val invaderArmy = new InvaderArmy(List(new CrabInvader(new Point(0,0))))

        val missiles = List(new Missile(new Point(0,0)))

        assert(hasCollided(missiles(0), invaderArmy.army(0)))
    }
}
