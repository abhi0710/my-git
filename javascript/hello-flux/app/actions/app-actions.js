import constants from '../constants/app-constants.js'
import dispatcher from '../dispatcher/dispatcher.js'

export let incrementActions = {
	increment :  () => {
				console.log("dispatching") 
				dispatcher.dispatch( {actionType : constants.INCREMENT})}
}


