import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Recuperator from './recuperator';
import RecuperatorDetail from './recuperator-detail';
import RecuperatorUpdate from './recuperator-update';
import RecuperatorDeleteDialog from './recuperator-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RecuperatorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RecuperatorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RecuperatorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Recuperator} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={RecuperatorDeleteDialog} />
  </>
);

export default Routes;
