import './homePage.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';

import { OfferCard } from '../../shared/layout/offer/offerCard';
import { IOffer } from '../../shared/model/offer.model';

export type IHomeProp = StateProps;

export const HomePage = (props: IHomeProp) => {
  const { account } = props;
  const fnc = (id)=>{window.console.log(id)};
  const offs= [{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null},{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null},{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null},{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null},{id: 21,description: `eeesddsf e 
  sdf ds fds eee`,isCold: true,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}, {id: 24,description: `eeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eeeeeesddsf e 
  sdf ds fds eee`,isCold: false,availabilityBegin: null,availabilityEnd: null,title: "titre",location: null,recuperator: null}];
  return (
    <Row>
      <Col md="12">
        <h2>
          <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
        </h2>
        <p className="lead">
          <Translate contentKey="home.subtitle">This is your homepage</Translate>
        </p>
          <div>
            <Alert color="success">
              <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                You are logged in as user {account.login}.
              </Translate>
            </Alert>
          </div>
        {offs.map((off,index) => {
          return <OfferCard key={index} offer={off} handleClick={fnc} />;
        })}
      </Col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(HomePage);
