package com.github.dunnololda.scageprojects.orbitalkiller.interface.switchers

import com.github.dunnololda.scage.support.messages.ScageMessage
import com.github.dunnololda.scageprojects.orbitalkiller.interface.{InterfaceHolder, InterfaceSwitcher}
import com.github.dunnololda.scageprojects.orbitalkiller.{OrbitalKiller, RealTrajectory}
import com.github.dunnololda.scageprojects.orbitalkiller.util.StringUtils._

class RealTrajectorySwitcher extends InterfaceSwitcher {
  private def enabledRealTrajectorySwitcher = if (InterfaceHolder.realTrajectorySwitcher.numPoints > RealTrajectory.curPoints) {
    s"RT\\[${timeStrSec(RealTrajectory.curPoints * 1000)}/${timeStrSec(numPoints * 1000)}\\]"
  } else {
    s"RT\\[${timeStrSec(numPoints * 1000)}\\]"
  }

  override def strVariants: Array[String] = Array("RToff", enabledRealTrajectorySwitcher)

  override def selectedStrVariantLen = ScageMessage.messageBounds(selectedStrVariant).ix

  def showRealTrajectory: Boolean = selectedVariant != 0

  var numPoints: Long = 24 * 3600

  override def switchForward(): Unit = {
    super.switchForward()
    OrbitalKiller.needToUpdateOrbits("changed central body")
  }

  override def switchBack(): Unit = {
    super.switchBack()
    OrbitalKiller.needToUpdateOrbits("changed central body")
  }
}
