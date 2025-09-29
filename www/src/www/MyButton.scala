package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class MyButton(color: String, onClick: Callback = Callback.empty) {
  def apply(children: VdomNode*): VdomElement = {
    MyButton.component(this)(children*)
  }
}

object MyButton {
  type Props = MyButton

  private def render(props: Props, children: PropsChildren) = {
    <.button(
      ^.onClick --> props.onClick,
      ^.cls := "btn btn-primary",
      children
    )
  }

  private val component = ScalaComponent
    .builder[Props](getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build

}