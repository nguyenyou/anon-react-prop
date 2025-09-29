package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class ChangeButtonColor(onSubmit: String => Callback = _ => Callback.empty) {
  def apply(): VdomElement = {
    ChangeButtonColor.component(this)
  }
}

object ChangeButtonColor {
  type Props = ChangeButtonColor

  case class State(value: String)

  case class Backend(scope: BackendScope[Props, State]) {
    def handleOnChange = (value: String) => {
      scope.modState(_.copy(value = value))
    }

    def render(props: Props, state: State): VdomElement = {
      <.div(
        ^.cls := "flex items-center gap-2",
        MyInput(
          value = state.value,
          placeholder = "Enter button color",
          onChange = handleOnChange
        )(),
        MyButton(
          color = MyButton.Color.Primary,
          onClick = props.onSubmit(state.value)
        )("Change Button Color")
      )
    }
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .initialState(State(""))
    .renderBackend[Backend]
    .build
}