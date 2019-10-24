import './homePage.scss';

import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import { Redirect } from 'react-router';
import  OfferPrint  from './offer/offerPrint'
import Checkbox from 'app/shared/layout/checkbox/checkbox';
import { IOffer } from 'app/shared/model/offer.model';
import OfferFormModal from './offerForm/offerForm';

export type IHomeProp = StateProps;

export const HomePage = (props: IHomeProp) => {
  const { account } = props;
  const [isNew, setIsNew] = useState(false);
  const [refresh, setrefresh] = useState(false);
  const [show, setShow] = useState(false);
  const [offer, setOffer] = useState<IOffer>();
  const [coldFilter, setcoldFilter] = useState(false);
  if(!(account && account.login)){
    return (<Redirect to="/login" />);
  }
  const openEdit = function (off:IOffer) {
    setShow(true);
    setOffer(off);
    setIsNew(false);
  }
  const openCreate = function () {
    setShow(true);
    setOffer(null);
    setIsNew(true);
  }
  
  return (
    <Row>
      <OfferFormModal showModal={show} handleClose={setShow} isNew={isNew} offer={offer}/>
      <Col md="12">
        {
          account.authorities.includes("ROLE_ASSOCIATION")?(
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
          ):("")
        }
        <OfferPrint showModal={show} openCreate={openCreate} refresh={refresh}
        coldFilter={coldFilter} isCompagny={account.authorities.includes("ROLE_COMPANY")}
        selectOffer={openEdit} isAssos={account.authorities.includes("ROLE_ASSOCIATION")}/>
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
