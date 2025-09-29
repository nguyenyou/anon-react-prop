package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class TodoApp() {
  def apply(): VdomElement = {
    TodoApp.component(this)
  }
}

object TodoApp {
  type Props = TodoApp

  case class State(value: String)

  case class Backend(scope: BackendScope[Props, State]) {
    def handleOnChange(props: Props)(e: ReactEventFromInput) = {
      val value = e.target.value
      println(value)
    }

    def render(props: Props, state: State): VdomElement = {
      <.div(
        <.div("Todo App"),
        MyInput(
          value = state.value,
          onChange = (value) => {
            scope.modState(_.copy(value = value))
          }
        )()
      )
    }
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .initialState(State("hello"))
    .renderBackend[Backend]
    .build
}