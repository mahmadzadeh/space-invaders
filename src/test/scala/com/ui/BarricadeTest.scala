package com.ui

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import java.awt.{Rectangle, Point}
import com.ui.gameelement.barricade.Barricade


class BarricadeTest extends FunSuite with MockitoSugar {

    test("given instance of barricade then boundingBox returns bbx of barricade") {
        val instance = new Barricade(new Point(0, 0))

        val expectedWidth  = instance.parts.sortWith( _.getBoundingBox.width > _.getBoundingBox.width)(0).getBoundingBox.width
        val expectedHeight = instance.parts.take(12).map(_.getBoundingBox.height).sum

        assertResult(new Rectangle(0,0,expectedWidth,expectedHeight) ){
            instance.boundingBox
        }
    }

}
