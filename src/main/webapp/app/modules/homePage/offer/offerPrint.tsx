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
export interface IOfferPrintProps extends IofferPrint, StatePropsS, DispatchPropsS {}

export class OfferPrint extends React.Component<IOfferPrintProps> {
  componentDidMount() {
    this.props.searchAvailableOffer();
  }
  componentDidUpdate(prevProps, prevState) {
    window.console.log(prevProps)
    if (prevProps.coldFilter !== this.props.coldFilter||prevProps.showModal !== this.props.showModal) {
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

const mapStateToPropsS = ({ offer }: IRootState) => ({
  offerListPrint: offer.entities
});

const mapDispatchToPropsS = {
  searchAvailableOffer,
  searchAvailableOfferCold
};

type StatePropsS = ReturnType<typeof mapStateToPropsS>;
type DispatchPropsS = typeof mapDispatchToPropsS;

export default connect(
  mapStateToPropsS,
  mapDispatchToPropsS
)(OfferPrint);
