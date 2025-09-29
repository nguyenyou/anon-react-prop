package www

import org.scalajs.dom

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

def MainReactApp(): Unit = {
  val container = dom.document.getElementById("app")

  val Component = ScalaFnComponent
    .withHooks[Unit]
    .useState(0)
    .render((props, count) => {
      <.div(
        "React Hello"
      )
    })

  ReactDOMClient.createRoot(container).render(Component())
}
