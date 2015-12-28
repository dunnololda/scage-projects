package com.github.dunnololda.scageprojects.orbitalkiller

import com.github.dunnololda.scage.ScageLibD._
import com.github.dunnololda.scageprojects.orbitalkiller.OrbitalKiller._

class SpaceStation(
             index:String,
             init_coord:DVec,
             init_velocity:DVec = DVec.dzero,
             init_rotation:Double = 0.0
             ) extends PolygonShip(index, init_coord, init_velocity, init_rotation) {
  def mass:Double = _payload + _fuel_mass
  private val _payload:Double = 5*1000
  private var _fuel_mass:Double = 5*1000
  override def fuelMass: Double = _fuel_mass

  override def fuelMass_=(m: Double): Unit = {
    _fuel_mass = m
  }


  val points:List[DVec] = List(
    DVec(-50.0, 50.0),
    DVec(-30.0, 50.0),
    DVec(-30.0, 30.0),
    DVec(-10.0, 10.0),
    DVec(-10.0, -10.0),
    DVec(-30.0, -30.0),
    DVec(-30.0, -50.0),
    DVec(-50.0, -50.0),
    DVec(-50.0, -110.0),
    DVec(-30.0, -110.0),
    DVec(-30.0, -70.0),
    DVec(30.0, -70.0),
    DVec(30.0, -110.0),
    DVec(50.0, -110.0),
    DVec(50.0, -50.0),
    DVec(30.0, -50.0),
    DVec(30.0, -30.0),
    DVec(10.0, -10.0),
    DVec(10.0, 10.0),
    DVec(30.0, 30.0),
    DVec(30.0, 50.0),
    DVec(50.0, 50.0),
    DVec(50.0, 110.0),
    DVec(30.0, 110.0),
    DVec(30.0, 90.0),
    DVec(30.0, 70.0),
    DVec(10.0, 70.0),
    DVec(-10.0, 70.0),
    DVec(-30.0, 70.0),
    DVec(-30.0, 90.0),
    DVec(-30.0, 110.0),
    DVec(-50.0, 110.0)
  )

  val draw_points = points :+ points.head

  val four  = Engine("4", position = DVec(-10.0, 0.0),    force_dir = DVec(1.0, 0.0),  max_power = 10, default_power_percent = 1, this)
  val six   = Engine("6", position = DVec(10.0, 0.0),     force_dir = DVec(-1.0, 0.0), max_power = 10, default_power_percent = 1, this)
  val seven = Engine("7", position = DVec(-40.0, 110.0),  force_dir = DVec(0.0, -1.0), max_power = 10, default_power_percent = 1, this)
  val nine  = Engine("9", position = DVec(40.0, 110.0),   force_dir = DVec(0.0, -1.0), max_power = 10, default_power_percent = 1, this)
  val one   = Engine("1", position = DVec(-40.0, -110.0), force_dir = DVec(0.0, 1.0),  max_power = 10, default_power_percent = 1, this)
  val three = Engine("3", position = DVec(40.0, -110.0),  force_dir = DVec(0.0, 1.0),  max_power = 10, default_power_percent = 1, this)

  val engines = List(four, six, seven, nine, one, three)

  val engines_mapping = Map(
    KEY_NUMPAD4 -> four,
    KEY_NUMPAD6 -> six,
    KEY_NUMPAD7 -> seven,
    KEY_NUMPAD9 -> nine,
    KEY_NUMPAD1 -> one,
    KEY_NUMPAD3 -> three
  )

  def preserveVelocity(vel:DVec) {

  }

  def preserveAngularVelocity(ang_vel_deg: Double) {

  }

  render {
    /*if(renderingEnabled) {*/
      if(!drawMapMode) {
        openglLocalTransform {
          openglMove(coordOrFirstPartCoord - base)
          drawFilledCircle(DVec.zero, 2, GREEN)                                // mass center
          drawArrow(DVec.zero, linearVelocity.n * 100, CYAN)             // current velocity
          drawArrow(DVec.zero, (earth.coord - coordOrFirstPartCoord).n * 100, YELLOW)    // direction to earth
          drawArrow(DVec.zero, (moon.coord - coordOrFirstPartCoord).n * 100, GREEN)      // direction to moon

          openglRotateDeg(rotation)
          drawSlidingLines(draw_points, WHITE)

          engines.foreach {
            case e =>
              e.force_dir match {
                case DVec(0, -1) => drawEngine(e, e.position + DVec(0, 2.5),  10, 5,  is_vertical = false)
                case DVec(0, 1)  => drawEngine(e, e.position + DVec(0, -2.5), 10, 5,  is_vertical = false)
                case DVec(-1, 0) => drawEngine(e, e.position + DVec(2.5, 0),  5,  10, is_vertical = true)
                case DVec(1, 0)  => drawEngine(e, e.position + DVec(-2.5,0), 5,  10, is_vertical = true)
                case _ =>
              }
          }
        }
      }
    /*}*/
  }
}
