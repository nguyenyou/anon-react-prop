package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class ReactApp() {
  def apply(): VdomElement = {
    ReactApp.component(this)
  }
}

object ReactApp {
  type Props = ReactApp

  case class Backend(scope: BackendScope[Props, Unit]) {
    def render(props: Props): VdomElement = {
      <.div(
        ^.cls := "container mx-auto py-5",
        <.div("React App"),
        TodoApp()(),
      )
    }
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .renderBackend[Backend]
    .build
}