package com.github.dunnololda.scageprojects.orbitalkiller.physics

import com.github.dunnololda.scage.support.DVec
import com.github.dunnololda.scageprojects.orbitalkiller._
import com.github.dunnololda.scageprojects.orbitalkiller.util.physics.PhysicsUtils._

import scala.collection.mutable

class EvolutionHelper(mutable_system: mutable.HashMap[Int, MutableSystemPart]) {
  def gravityForceFromTo(planet_index: Int, ship_index: Int): DVec = {
    (for {
      planet <- mutable_system.get(planet_index).map(_.body)
      ship <- mutable_system.get(ship_index).map(_.body)
    } yield gravityForce(planet.coord, planet.mass, ship.coord, ship.mass, G)).getOrElse(DVec.zero)
  }

  def funcOrDVecZero(body_index: Int, func: MutableBodyState => DVec): DVec = {
    mutable_system.get(body_index).map(bs => func(bs.body)).getOrElse(DVec.zero)
  }

  def funcOfArrayOrDVecZero(body_indicies: Array[Int], func: Array[MutableBodyState] => DVec): DVec = {
    val bodies = body_indicies.flatMap(idx => mutable_system.get(idx).map(bs => bs.body))
    if (bodies.length == body_indicies.length) func(bodies) else DVec.zero
  }

  def funcOrDoubleZero(body_index: Int, func: MutableBodyState => Double): Double = {
    mutable_system.get(body_index).map(bs => func(bs.body)).getOrElse(0.0)
  }

  def bodyStates(indicies: collection.Set[Int]) = mutable_system.filter(kv => kv._2.body.active && indicies.contains(kv._1)).map(kv => kv._2.body).toSeq

  def bodyStatesMap(indicies: Set[Int]) = mutable_system.filter(kv => kv._2.body.active && indicies.contains(kv._1)).map(kv => (kv._1, kv._2.body))

  def bodyState(index: Int) = mutable_system.get(index).filter(_.body.active).map(_.body)
}
