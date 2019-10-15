import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Redirect, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { login } from 'app/shared/reducers/authentication';
import LoginForm from './loginForm';
import './loginPage.scss';
import { Button } from 'reactstrap';
import RegisterModal from './register/register';

export interface ILoginProps extends StateProps, DispatchProps, RouteComponentProps<{}> {}

export const LoginPage = (props: ILoginProps) => {
  const [show, setShow] = useState(false);

  const handleShow = () => {
    window.console.log(show,show.valueOf())
    setShow(true)
    window.console.log(show,show.valueOf())
  };

  const handleLogin = (username, password, rememberMe = false) => props.login(username, password, rememberMe);

  const { location, isAuthenticated } = props;
  const { from } = location.state || { from: { pathname: '/', search: location.search } };
  if (isAuthenticated) {
    return <Redirect to={from} />;
  }
  return (
    <div>
      <RegisterModal showModal={show} handleClose={setShow} />
      <div className="LoginPage">
        <div className="LoginForm">
          crea
        </div>
        <div className="LoginForm">
          <LoginForm handleLogin={handleLogin} loginError={props.loginError} />
        </div>
        <div className="LoginForm">        
          <Button variant="primary" onClick={handleShow}>
            Launch demo modal
          </Button>
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
