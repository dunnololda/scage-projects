package com.github.dunnololda.scageprojects.orbitalkiller

import com.github.dunnololda.scage.ScageLib._

object HelpScreen extends ScageScreen("Help Screen") {
  val s =
    """|Клавиши цифровой клавиатуры (Num Lock = on) - выбор двигателей:
      |1,4,7 - двигатели левой стороны
      |3,6,9 - двигатели правой стороны
      |2,8 - нижний и верхний двигатели
      |5 - отключить все двигатели
      |
      |+, - - ускорение/замедление времени
      |
      |Стрелки вверх/вниз - добавить/убавить мощность выбранного двигателя
      |Стрелки влево/вправо - добавить/убавить время работы выбранного двигателя
      |
      |W, A, S, D - управление камерой
      |Колесо мышки - приближение/отдаление
      |Пробел - Ориентировать корабль по центру окна
      |P - Пауза
      |
      |F2 - Камера, свободный режим
      |F3 - Камера, фиксация на корабле
      |F4 - Камера, посадка на планету
      |F5 - Камера, фиксация на солнце
      |F6 - Камера, фиксация на планете
      |
      |1 - Режим полета, свободный
      |2 - Режим полета, запрет вращения
      |3 - Режим полета, ориентация по осям
      |4 - Режим полета, ориентация по траектории (не доделано)
      |
      |V - рисовать/не рисовать пройденную траекторию/будущую траекторию/интерфейс
      |
      |Нажмите Escape
    """.stripMargin

  interface {
    print(s, 20, windowHeight - 30, color = WHITE)
  }

  key(KEY_ESCAPE, onKeyDown = stop())
  keyIgnorePause(KEY_Q, onKeyDown = {if(keyPressed(KEY_LCONTROL)) stopApp()})
}
