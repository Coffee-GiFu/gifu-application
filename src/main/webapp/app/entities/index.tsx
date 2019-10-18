import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Location from './location';
import Recuperator from './recuperator';
import Offer from './offer';
import Organisation from './organisation';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}/recuperator`} component={Recuperator} />
      <ErrorBoundaryRoute path={`${match.url}/offer`} component={Offer} />
      <ErrorBoundaryRoute path={`${match.url}/organisation`} component={Organisation} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
