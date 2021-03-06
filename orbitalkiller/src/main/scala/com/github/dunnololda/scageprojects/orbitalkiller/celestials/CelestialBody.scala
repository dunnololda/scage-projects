package com.github.dunnololda.scageprojects.orbitalkiller.celestials

import com.github.dunnololda.scage.ScageLibD._
import com.github.dunnololda.scageprojects.orbitalkiller.components.BasicComponents._
import com.github.dunnololda.scageprojects.orbitalkiller.physics.{BodyState, MutableBodyState}
import com.github.dunnololda.scageprojects.orbitalkiller.render.OrbitRenderData
import com.github.dunnololda.scageprojects.orbitalkiller.util.LogUtils

trait CelestialBody {
  def index: Int

  def name: String

  def coord: DVec

  def linearVelocity: DVec

  def mass: Double

  def radius: Double

  lazy val radius2: Double = radius * radius

  def initState: BodyState

  def half_hill_radius: Double

  lazy val half_hill_radius2: Double = half_hill_radius * half_hill_radius

  def air_free_altitude: Double

  LogUtils.log(s"$name -> $index")
  val currentState: MutableBodyState = initState.toMutableBodyState
  val ground_length_km: Int = (2 * math.Pi * radius / 1000).toInt
  val groundSpeedMsec: Double = currentState.ang_vel.toRad * radius
  val length: Double = 2 * math.Pi * radius

  val g: Double = G * mass / (radius * radius) // ускорение свободного падения, м/с^2

  var orbitRender: Option[OrbitRenderData] = None
}

