package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class AddTodo() {
  def apply(): VdomElement = {
    AddTodo.component(this)
  }
}

object AddTodo {
  type Props = AddTodo

  case class State(value: String)

  case class Backend(scope: BackendScope[Props, State]) {
    def handleOnChange(props: Props)(e: ReactEventFromInput) = {
      val value = e.target.value
      println(value)
    }

    def render(props: Props, state: State): VdomElement = {
      <.div(
        ^.cls := "space-y-5",
        <.div("Add Todo"),
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