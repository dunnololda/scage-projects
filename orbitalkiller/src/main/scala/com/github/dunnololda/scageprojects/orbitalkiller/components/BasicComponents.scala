package com.github.dunnololda.scageprojects.orbitalkiller.components

import com.github.dunnololda.scage.support.ScageId

/**
  * Created by andrey on 1/7/18.
  */
object BasicComponents {
  val G: Double = 6.6742867E-11

  val k: Double = 1 // доля секунды симуляции, которая обрабатывается за одну реальную секунду, если не применяется ускорение

  val linear_velocity_error: Double = 0.1
  val angular_velocity_error: Double = 0.0102
  // значение подобрано эмпирически при тестах с малым количеством топлива
  val angle_error = 0.1

  val scale: Double = 1e-6

  // движок делает вызов обработчика примерно 63 раза в секунду, за каждый вызов будет обрабатывать вот такую порцию симуляции
  // то есть, мы хотим, чтобы за одну реальную секунду обрабатывалось k секунд симуляции, поэтому за один такт движка (которых 60 в секунду)
  // будем обрабатывать k/60
  val base_dt: Double = 1.0 / 63 * k

  // какой длины в пикселях на экране будет реальная длина в 1 метр
  /*def zoom:Double = 10*/

  val realtime = (1.0 / k).toInt // 1/k соответствует реальному течению времени

  val sunIndex: Int = ScageId.nextId
  val earthIndex: Int = ScageId.nextId
  val moonIndex: Int = ScageId.nextId
}
