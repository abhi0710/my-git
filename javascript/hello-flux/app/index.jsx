import React from 'react'
import ReactDom from 'react-dom'
import constants from './constants/app-constants.js'
import dispatcher from './dispatcher/dispatcher.js'
import {incrementActions} from './actions/app-actions.js'
import TDS from './stores/count-store.js'

console.log(constants)
console.log(dispatcher)
class App extends React.Component {
	constructor(props) {
		super(props)
		this.state = {count:0};
		this.increment = this.increment.bind(this)
	}
	render() {
		return (
			
			<div> <p> Hello {this.state.count}</p>
			<button onClick = {this.increment}>Increment</button>
			</div>
	 	)	
	}
	
	increment() {
		//this.setState({count: this.state.count + 1})
		incrementActions.increment()
	}
}

ReactDom.render(<App/>, document.getElementById("app"))
