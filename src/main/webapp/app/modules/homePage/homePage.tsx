import './homePage.scss';

import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import { Redirect } from 'react-router';
import OfferCardAdd from 'app/shared/layout/offer/offerCardAdd';
import  OfferPrint  from './offer/offerPrint'

export type IHomeProp = StateProps;

export const HomePage = (props: IHomeProp) => {
  const { account } = props;
  const [show, setShow] = useState(false);
  if(!(account && account.login)){
    return (<Redirect to="/login" />);
  }
  return (
    <Row>
      <Col md="12">
        <div>
          <Alert color="success">
            <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
              You are logged in as user {account.login}.
            </Translate>
          </Alert>
        </div>
        <div>
          {
            (account.authorities.includes("ROLE_COMPANY"))?(
              <OfferCardAdd handleClick={()=>{window.console.log("createoffer")}}/>
            ):("")
          }          
          <OfferPrint showModal={show} handleClose={setShow}/>
        </div>
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
