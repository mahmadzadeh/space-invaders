package com.ui

import org.scalatest.FunSuite
import com.ui.gameelement.invader.types.Invader
import java.awt.Point

class ScoreCalculationTest extends FunSuite {

    test("given no invader by player missiles then calculateScore returns 0") {
        assertResult(0) {
            ScoreCalculation.calculatePoints(Nil)
        }
    }

    test("given 1 invader hit that is worth 20 pts then calculateScore returns 20") {
        val invaderHitByMissile = new Invader(new Point(0,0))

        assertResult(20) {
            ScoreCalculation.calculatePoints(Seq(invaderHitByMissile))
        }
    }
}