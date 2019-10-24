import './homePage.scss';

import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import { Redirect } from 'react-router';
import  OfferPrint  from './offer/offerPrint'
import Checkbox from 'app/shared/layout/checkbox/checkbox';

export type IHomeProp = StateProps;

export const HomePage = (props: IHomeProp) => {
  const { account } = props;
  const [show, setShow] = useState(false);
  const [coldFilter, setcoldFilter] = useState(false);
  if(!(account && account.login)){
    return (<Redirect to="/login" />);
  }
  
  window.console.log(coldFilter,show);
  return (
    <Row>
      <Col md="12">
        <div>
          <div className="filterBar">
            <label className="filterLabel" htmlFor="iscold">
              <div className="filterBlock">
                <Checkbox name="iscold" defaultValue={coldFilter} action={setcoldFilter}/>
                <span className="filterLabel">
                  <Translate contentKey="home.filter.cold">
                    Without cold storage.
                  </Translate>
                </span>
              </div>
            </label>
          </div>
        </div>    
        <OfferPrint showModal={show} handleClose={setShow} coldFilter={coldFilter} isAllow={account.authorities.includes("ROLE_COMPANY")}/>
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
