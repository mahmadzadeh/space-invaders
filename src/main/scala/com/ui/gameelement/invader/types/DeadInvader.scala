package com.ui.gameelement.invader.types

import java.awt.{Graphics, Point, Rectangle}
import com.ui.gameelement.displayelement.SingleDisplayElement

object DeadInvader extends Invader(new Point(0,0))  {

    override val parts: List[SingleDisplayElement] = Nil

    override def draw(g:Graphics) :Unit = Unit

    override def moveTo(point:Point): Invader = this

    override def boundingBox: Rectangle = new Rectangle(0 ,0, 0, 0)

    override def feelLikeDroppingABomb: Boolean = false

    override def pointsWorth = 0

    override def getOppositeCharacterAtPoint(point: Point, isHit: Boolean): Invader = this

}
