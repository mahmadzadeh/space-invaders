package com.ui.gameelement.invader

import java.awt.Point
import com.ui.gameelement.invader.types.MysteryInvader
import com.ui.gameelement.invader.MysteryInvaderDirection.{LeftToRight, RightToLeft}
import com.GameConfiguration
import GameConfiguration.currentConfig

object MysteryInvaderPositionDirector {

    def nextPosition(invader: MysteryInvader): Point =
        invader.direction match {
            case RightToLeft =>
                new Point(invader.topLeft.x - currentConfig.mysteryInvaderVelocity, invader.topLeft.y)
            case LeftToRight =>
                new Point(invader.topLeft.x + currentConfig.mysteryInvaderVelocity, invader.topLeft.y)
        }

}
