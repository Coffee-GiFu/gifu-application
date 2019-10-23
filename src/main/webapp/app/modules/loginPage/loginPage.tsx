import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Redirect, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { login } from 'app/shared/reducers/authentication';
import LoginForm from './loginForm';
import './loginPage.scss';
import { Button } from 'reactstrap';
import RegisterModal from './register/register';
import { translate } from 'react-jhipster/lib/src/language/translate';
import Checkbox from 'app/shared/layout/checkbox/checkbox';
import { OfferCard } from 'app/shared/layout/offer/offerCard';
import OfferCardAdd from 'app/shared/layout/offer/offerCardAdd';

export interface ILoginProps extends StateProps, DispatchProps, RouteComponentProps<{}> {}

export const LoginPage = (props: ILoginProps) => {
  const [show, setShow] = useState(false);

  const [isEntreprise, setIsEntreprise] = useState(false);

  const handleShowEntreprise = () => {
    setShow(true);
    setIsEntreprise(true);
  };
  const handleShowAssociation = () => {
    setShow(true);
    setIsEntreprise(false);
  };

  const handleLogin = (username, password, rememberMe = false) => props.login(username, password, rememberMe);

  const { location, isAuthenticated } = props;
  const { from } = location.state || { from: { pathname: '/', search: location.search } };
  if (isAuthenticated) {
    return <Redirect to={from} />;
  }
  return (
    <div className="LoginPage">
      <RegisterModal showModal={show} handleClose={setShow} isEntreprise={isEntreprise} />
        <div className="LoginForm"> 
          <div className="createAccount entrepriseIco" onClick={handleShowEntreprise}>
            <p>
              {translate('register.type.base')}<br/>
              {translate('register.type.entreprise')}
            </p>
          </div>
        </div>
        <div className="LoginForm">
          <LoginForm handleLogin={handleLogin} loginError={props.loginError} />
        </div>
        <div className="LoginForm">
          <div className="createAccount associationIco" onClick={handleShowAssociation}>
            <p>
              {translate('register.type.base')}<br/>
              {translate('register.type.association')}
            </p>
          </div>
        </div>
    </div>
  );
};

const mapStateToProps = ({ authentication }: IRootState) => ({
  isAuthenticated: authentication.isAuthenticated,
  loginError: authentication.loginError,
  showModal: authentication.showModalLogin
});

const mapDispatchToProps = { login };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginPage);
