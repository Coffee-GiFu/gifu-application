import React from 'react';
import {connect} from 'react-redux';
import {Translate} from 'react-jhipster';

import {IRootState} from 'app/shared/reducers';
import {
  getEntities,
  searchAvailableOffer,
  searchChosenOffer,
  searchCreatedOffer
} from '../../../entities/offer/offer.reducer';
import OfferCard from 'app/shared/layout/offer/offerCard';
import OfferCardAdd from 'app/shared/layout/offer/offerCardAdd';

interface IofferPrint {
    isAllow: boolean;
    showModal: boolean;
    handleClose: Function;
}
export interface IOfferPrintProps extends IofferPrint, StateProps, DispatchProps {}

export class OfferPrint extends React.Component<IOfferPrintProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { offerList } = this.props;
    window.console.log(offerList[0]);
    return (
      <div className="offerPrintBody">
        {
            (this.props.isAllow)?(
              <OfferCardAdd handleClick={()=>{window.console.log("createoffer")}}/>
            ):("")
          }   
        {
          offerList && offerList.length > 0 ? (
            offerList.map((off,index) => {
              return <OfferCard key={index} offer={off} handleClick={(id)=>{window.console.log(id)}} />;
            })
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="gifuApp.offer.home.notFound">No Offers found</Translate>
            </div>
          )
        }
      </div>
    );
  }
}

const mapStateToProps = ({ offer }: IRootState) => ({
  offerList: offer.entities
});

const mapDispatchToProps = {
  getEntities,
  searchAvailableOffer,
  searchCreatedOffer,
  searchChosenOffer
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OfferPrint);
