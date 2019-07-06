import React from 'react';
import './App.css';

import {HashRouter,Switch,Route} from "react-router-dom";
import SmartVideosList from './view/SmartVideosList';
import SmartCreateVideo from './view/SmartCreateVideo';
import SmartSearchVideo from './view/SmartSearchVideo';
import SmartLogin from './view/SmartLogin';
import SmartCommentsList from './view/SmartCommentsList';

const App = () => (
      <div className="App">
        <HashRouter>
          <Switch>
            <Route exact={true} component={SmartLogin} path="/"/>
            <Route exact={true} component={SmartVideosList} path="/videos"/>
            <Route exact={true} component={SmartCreateVideo} path="/upload-video"/>
            <Route exact={true} component={SmartSearchVideo} path="/search"/>
            <Route exact={true} component={SmartCommentsList} path="/watch/:index"/>
          </Switch>
        </HashRouter>
      </div>
    );

export default App;
