package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class MyInput(color: String, onClick: Callback = Callback.empty) {
  def apply(): VdomElement = {
    MyButton.component(this)
  }
}

object MyButton {
  type Props = MyButton

  private def render(props: Props, children: PropsChildren) = {
    <.button(
      ^.onClick --> props.onClick,
      children
    )
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .stateless
    .render_PC(render)
}