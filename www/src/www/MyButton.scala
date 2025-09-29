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

  enum Color {
    case Primary, Secondary, Success, Error
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
    .builder[Props](getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build

}