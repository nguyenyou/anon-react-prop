package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class MyButton(color: MyButton.Color = MyButton.Color.Primary, onClick: Callback = Callback.empty) {
  def apply(children: VdomNode*): VdomElement = {
    MyButton.component(this)(children*)
  }
}

object MyButton {
  type Props = MyButton

  sealed trait Color
  object Color {
    case object Primary extends Color
    case object Secondary extends Color
    case object Success extends Color
    case object Error extends Color
  }

  def colorToClass(color: Color) = {
    color match {
      case Color.Primary => "btn-primary"
      case Color.Secondary => "btn-secondary"
      case Color.Success => "btn-success"
      case Color.Error => "btn-error"
    }
  }

  private def render(props: Props, children: PropsChildren) = {
    <.button(
      ^.onClick --> props.onClick,
      ^.cls := s"btn ${colorToClass(props.color)}",
      children
    )
  }

  private val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .stateless
    .render_PC(render)
    .build

}