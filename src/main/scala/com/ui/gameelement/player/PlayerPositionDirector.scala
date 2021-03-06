package com.ui.gameelement.player

import java.awt.Point
import com.ui.gameelement.player.types.{ShootingPlayer, Player}

object PlayerPositionDirector {

    val SINGLE_HOP_IN_X = 20

    def playerInitialPosition(screenWidth:Int, screenHeight:Int ):Point =
    new Point(screenWidth / 5, screenHeight - (screenHeight / 9))

    def newPositionToLeft(player:Player):Option[Point] =
    if(!hasReachedLeftEdgeOfScreen(player)) {
        Some(getNewPositionForPlayer(player, -SINGLE_HOP_IN_X))
    } else None

    def newPositionToRight(player:Player, screenWidth:Int):Option[Point]  =
    if(!hasReachedRightEdgeOfScreen(player, screenWidth)) {
        Some(getNewPositionForPlayer(player, SINGLE_HOP_IN_X))
    } else None

    val BUFFER_BETWEEN_LIVES = 10
    def calculatePlayerPositionsOnLivesPanel(startingPoint: Point, count:Int = 3): Seq[Point] = {
        val playerBoundingBox = new ShootingPlayer(new Point(0,0)).boundingBox

        0 until (count) map{ i:Int =>
            new Point(startingPoint.x + (i*playerBoundingBox.width) + (i*BUFFER_BETWEEN_LIVES), startingPoint.y )
        }
    }

    private def hasReachedRightEdgeOfScreen(player: Player, screenWidth: Int): Boolean =
    player.boundingBox.width + player.topLeft.x + SINGLE_HOP_IN_X > screenWidth

    private def hasReachedLeftEdgeOfScreen(player: Player): Boolean = player.topLeft.x - SINGLE_HOP_IN_X < 0

    private def getNewPositionForPlayer(player:Player, dir:Int ):Point = new Point(player.topLeft.x + dir, player.topLeft.y)



}

