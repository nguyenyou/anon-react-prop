package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class MyInput(value: String, onChange: String => Callback = _ => Callback.empty) {
  def apply(): VdomElement = {
    MyInput.component(this)
  }
}

object MyInput {
  type Props = MyInput

  case class State(value: String)

  case class Backend(scope: BackendScope[Props, State]) {
    def handleOnChange(props: Props)(e: ReactEventFromInput) = {
      val value = e.target.value
      props.onChange(value)
    }

    def render(props: Props, state: State): VdomElement = {
      <.input(
        ^.value := props.value,
        ^.onChange ==> handleOnChange(props)
      )
    }
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .initialState(State(""))
    .renderBackend[Backend]
    .build
}