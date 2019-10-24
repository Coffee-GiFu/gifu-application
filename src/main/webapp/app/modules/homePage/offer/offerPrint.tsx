import React from 'react';
import {connect} from 'react-redux';
import {Translate} from 'react-jhipster';

import {IRootState} from 'app/shared/reducers';
import {
  getEntities,
  searchAvailableOffer,
  searchChosenOffer,
  searchCreatedOffer,
  searchAvailableOfferCold
} from '../../../entities/offer/offer.reducer';
import OfferCard from 'app/shared/layout/offer/offerCard';
import OfferCardAdd from 'app/shared/layout/offer/offerCardAdd';

interface IofferPrint {
    isAllow: boolean;
    showModal: boolean;
    coldFilter: boolean;
    openCreate: Function;
    selectOffer: Function;
}
export interface IOfferPrintProps extends IofferPrint, StateProps, DispatchProps {}

export class OfferPrint extends React.Component<IOfferPrintProps> {
  componentDidMount() {
    this.props.searchAvailableOffer();
  }
  componentDidUpdate(prevProps, prevState) {
    if (prevProps.coldFilter !== this.props.coldFilter) {
      if(this.props.coldFilter){
        this.props.searchAvailableOfferCold()
      } else {
        this.props.searchAvailableOffer();
      }
    }
  }
  render() {
    const { offerListPrint } = this.props;
    return (
      <div className="offerPrintBody">
        {
            (this.props.isAllow)?(
              <OfferCardAdd openCreate={this.props.openCreate}/>
            ):("")
          }   
        {
          offerListPrint && offerListPrint.length > 0 ? (
            offerListPrint.map((off,index) => {
              return <OfferCard key={index} offer={off} 
                handleClick={()=>{this.props.selectOffer(off)}} />;
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
  offerListPrint: offer.entities
});

const mapDispatchToProps = {
  searchAvailableOffer,
  searchCreatedOffer,
  searchChosenOffer,
  searchAvailableOfferCold
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OfferPrint);
