import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { HomePage } from '../homePage/homePage';
import { LoginPage } from '../loginPage/loginPage';

import { OfferCard } from '../../shared/layout/offer/offerCard';
import { IOffer } from '../../shared/model/offer.model';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;
  const fnc = (id)=>{window.console.log(id)};
  return (account && account.login ? HomePage(props) : LoginPage(props));
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
