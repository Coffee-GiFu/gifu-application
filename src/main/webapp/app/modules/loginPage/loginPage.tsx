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
  const offs= [{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null},{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf eukhfu dsfdsh fez h fuhezufh  ezufufze fuiezfueufy gezyfgu dsfdsh fez h fuhezufh  ezufufze fuiezfueufy gezyfgez ifgezigfezi gf ziyfg eig i 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}];
  return (
    <div>
      
      {offs.map((off,index) => {
          return <OfferCard key={index} offer={off} handleClick={(id)=>{window.console.log(id)}} />;
        })}
        <OfferCardAdd handleClick={()=>{window.console.log("lol")}}/>
        <div className="OfferCardAdd"/>
      <RegisterModal showModal={show} handleClose={setShow} isEntreprise={isEntreprise} />
      <div className="LoginPage">
        <div className="LoginForm"> 
          <div className="createAccount entrepriseIco btn btn-primary" onClick={handleShowEntreprise}>
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
          <div className="createAccount associationIco btn btn-primary" onClick={handleShowAssociation}>
            <p>
              {translate('register.type.base')}<br/>
              {translate('register.type.association')}
            </p>
          </div>
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
