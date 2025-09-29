package www

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class ChangeButtonColorApp() {
  def apply(): VdomElement = {
    ChangeButtonColorApp.component(this)
  }
}

object ChangeButtonColorApp {
  type Props = ChangeButtonColorApp

  case class State(buttonColor: MyButton.Color)

  case class Backend(scope: BackendScope[Props, State]) {
    def handleOnChange(props: Props)(e: ReactEventFromInput) = {
      val value = e.target.value
      println(value)
      if (value == "primary") {
        scope.modState(_.copy(buttonColor = MyButton.Color.Primary))
      } else if (value == "secondary") {
        scope.modState(_.copy(buttonColor = MyButton.Color.Secondary))
      } else if (value == "success") {
        scope.modState(_.copy(buttonColor = MyButton.Color.Success))
      } else if (value == "error") {
        scope.modState(_.copy(buttonColor = MyButton.Color.Error))
      }
    }

    def render(props: Props, state: State): VdomElement = {
      <.div(
        ^.cls := "space-y-5",
        <.div("Change Button Color App"),
        ChangeButtonColor(onSubmit = (value: String) => {
          val color = value match {
            case "primary" => MyButton.Color.Primary
            case "secondary" => MyButton.Color.Secondary
            case "success" => MyButton.Color.Success
            case "error" => MyButton.Color.Error
            case _ => MyButton.Color.Primary
          }
          scope.modState(_.copy(buttonColor = color))
        })(),
        MyButton(
          color = state.buttonColor,
          onClick = Callback.log("Success")
        )("Change Button Color App")
      )
    }
  }

  val component = ScalaComponent
    .builder[Props](getClass.getSimpleName.stripSuffix("$"))
    .initialState(State(MyButton.Color.Primary))
    .renderBackend[Backend]
    .build
}