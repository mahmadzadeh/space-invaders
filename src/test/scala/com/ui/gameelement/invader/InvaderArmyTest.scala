
package com.ui.gameelement.invader

import java.awt.{Point, Graphics}
import com.ui.gameelement.invader.types.{CrabInvader, Invader, ExplodedInvader, DeadInvader}
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

class InvaderArmyTest extends FunSuite with MockitoSugar {
    val startingPoint: Point = new Point(11, 11)

    test("can create an instance") {
        val armyOfOne = List(new CrabInvader(startingPoint))

        new InvaderArmy(armyOfOne)
    }

    test("given a an army of one then invader army can draw it") {
        val mockGraphics = mock[Graphics]
        val mockInvader = mock[Invader]

        doNothing().when(mockInvader).draw(mockGraphics)

        val armyOfOne = List(mockInvader)
        val invaderArmy = new InvaderArmy(armyOfOne)

        invaderArmy.draw(mockGraphics)

        verify(mockInvader).draw(mockGraphics)
    }

    test("given an army then invader army can move to point(x,y)") {
        val invaderArmy = new InvaderArmy(ArmyCommander.formAnArmy(startingPoint))

        val point = new Point(22,22)

       assertResult(point) {
           invaderArmy.moveTo(point).army(0).topLeft
       }
    }

    test("given an army of one then invader army can move to point(x,y) and back") {

        val invaderArmy = new InvaderArmy(ArmyCommander.formAnArmy(startingPoint))

        val point = new Point(22,22)

        assertResult(startingPoint) {
            invaderArmy.moveTo(point).moveTo(startingPoint).army(0).topLeft
        }
    }

    test("given an invader army the get its bounding box") {
        val invaderArmy = new InvaderArmy(ArmyCommander.formAnArmy(startingPoint))

        assertResult(startingPoint.x) {
            invaderArmy.getBoundingBox.getX
        }
        assertResult(ArmyCommander.armyWidth(Invader.INVADER_WIDTH)) {
            invaderArmy.getBoundingBox.width
        }
    }



    test("given one exploding invader when exploding to long enough then makeDeadInvadersInvisible will make it invisible") {
        val soldier= new ExplodedInvader(new Point(0, 0), System.currentTimeMillis() - 1000)
        val invaderArmy = new InvaderArmy(List(soldier))

        val newArmy     = invaderArmy.makeDeadInvadersInvisible

        assert(newArmy.army.size === 1)

        assert(newArmy.army(0) == DeadInvader)
    }

    test("given all soldiers have not been hit with missile then  makeDeadInvadersInvisible has no effect") {
        val soldier= new CrabInvader(new Point(0, 0))
        val invaderArmy = new InvaderArmy(List(soldier))

        val newArmy     = invaderArmy.makeDeadInvadersInvisible

        assert(newArmy.army.size === 1)

        assert(newArmy.army(0).isInstanceOf[Invader])
    }

    test("given an army of all dead invader soldiers then call to dropRandomBomb returns no bombs..the dead can't drop bombs") {
        val invaderArmy = new InvaderArmy(List(DeadInvader))

        assertResult(None){
            invaderArmy.dropRandomBomb(new Point(0,0))
        }
    }

    test("given an army and player location then filterSoldiersAboveShooter returns list of army soldiers directly above the player") {
        val invaderArmy     = new InvaderArmy(ArmyCommander.formAnArmy(new Point(0,0)))

        val shooterPosition = new Point(0,0)
        val soldiersAbove   = invaderArmy.filterSoldiersAboveShooter(shooterPosition)

        assertResult(5){ soldiersAbove.size    }
    }

}


