// import React from 'react';
// import ReactDOM from 'react-dom/client';
// import './index.css';
// import App from './App';
// import reportWebVitals from './reportWebVitals';
// import {BrowserRouter, Route} from "react-router-dom";
// import {Provider} from 'react-redux';

// ReactDOM.render(
//   <BrowserRouter>{/* обгортка для route */}
//     <Provider store={store}>{/* провайдер, поставщик даних к дочерним елементам */}
//       {/* value={store} тоисть всем дочерним компонентам будет доступерн через параметр value гловальная переменая store */}
//       <App />
//       {/* bind(store) - біндим у store бо ми його взяли у store, щоб всередині dispatch this був store  */}
//     </Provider>
//   </BrowserRouter>, document.getElementById('root'));
// // If you want to start measuring performance in your app, pass a function
// // to log results (for example: reportWebVitals(console.log))
// // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();





import './index.css';

import store from './redux/redux-store';
import reportWebVitals from './reportWebVitals';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Provider} from 'react-redux';


  ReactDOM.render(
    <BrowserRouter>{/* обгортка для route */}
      
        <Provider store={store}>{/* провайдер, поставщик даних к дочерним елементам */}
          {/* value={store} тоисть всем дочерним компонентам будет доступерн через параметр value гловальная переменая store */}
          <App />
          {/* bind(store) - біндим у store бо ми його взяли у store, щоб всередині dispatch this був store  */}
        </Provider>
    </BrowserRouter>, document.getElementById('root'));



// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
reportWebVitals();
