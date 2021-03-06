package com.ui.panel

import javax.swing.{JLabel, JPanel}
import java.awt.{Color, Point, Graphics, Font}
import com.ui.gameelement.player.PlayerPositionDirector
import com.ui.gameelement.player.types.ShootingPlayer
import com.GameLogic


object GameStatusPanel extends JPanel {

    private[this] val panelTitleName  = "G A M E    S T A T S"
    private[this] val panelTextFont   = new Font("Verdana", Font.BOLD, 12)
    private[this] val score           = new JLabel

    private[this] var livesLeft       = GameLogic.INITIAL_NUM_LIVES

    private val mainPanelLayout = new org.jdesktop.layout.GroupLayout(this)
    setPanelAttributes

    override def paintComponent(g: Graphics): Unit = {
        super.paintComponent(g)
        displayLives(g)
    }

    def updateStats(lives: Int, pnts:Int): Unit = {
        livesLeft = lives

        score.setForeground(Color.RED)
        score.setFont(panelTextFont)
        score.setText(s"SC0RE: ${pnts}")

        repaint()
    }

    private def displayLives(g:Graphics):Unit = {
        val startingPoint = new Point(getWidth/3 , 21)
        PlayerPositionDirector.calculatePlayerPositionsOnLivesPanel(startingPoint, livesLeft)
        .foreach { point=>
            val player = new ShootingPlayer(point)
            player.draw(g)
        }
    }

    private def setPanelAttributes {
        setBorder(javax.swing.BorderFactory.createTitledBorder(panelTitleName))
        setToolTipText("")
        setFont(panelTextFont)
        setName("mainPanel")

        score.setText("")
        score.getAccessibleContext.setAccessibleName("livesCountLabel")

        setLayout(mainPanelLayout)

        mainPanelLayout
        .setHorizontalGroup(
                mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mainPanelLayout.createSequentialGroup.add(22, 22, 22)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(score, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, java.lang.Short.MAX_VALUE)))

        mainPanelLayout
        .setVerticalGroup(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                          .add(mainPanelLayout.createSequentialGroup.addContainerGap
                               .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(score)).addContainerGap(40, java.lang.Short.MAX_VALUE)))
    }
}
