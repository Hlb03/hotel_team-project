import {createStore, combineReducers} from "redux";
import { applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import { authReducer } from "./reducers/auth-reducer";


let reducers = combineReducers({//записиваем сюда все Reducer и комбинуем их методом combineReducers
  auth: authReducer
});

let store = createStore(reducers, applyMiddleware(thunkMiddleware) );//applyMiddleware - придпринять промежуточние слои, стобы вклиниться в конвеер

window.store = store;


export default store;


