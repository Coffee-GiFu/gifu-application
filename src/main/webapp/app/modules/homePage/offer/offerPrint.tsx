import React from 'react';
import {connect} from 'react-redux';
import {Translate} from 'react-jhipster';

import {IRootState} from 'app/shared/reducers';
import {
  searchAvailableOffer,
  searchCreatedOffer,
  searchAvailableOfferCold
} from '../../../entities/offer/offer.reducer';
import OfferCard from 'app/shared/layout/offer/offerCard';
import OfferCardAdd from 'app/shared/layout/offer/offerCardAdd';

interface IofferPrint {
    isCompagny: boolean;
    isAssos: boolean;
    showModal: boolean;
    coldFilter: boolean;
    openCreate: Function;
    selectOffer: Function;
}
export interface IOfferPrintProps extends IofferPrint, StatePropsS, DispatchPropsS {}

export class OfferPrint extends React.Component<IOfferPrintProps> {
  componentDidMount() {
    if(this.props.isAssos){
      this.props.searchAvailableOffer();
    }else{
      this.props.searchCreatedOffer();
    }
  }
  componentDidUpdate(prevProps, prevState) {
    if (prevProps.coldFilter !== this.props.coldFilter||prevProps.showModal !== this.props.showModal) {
      if(this.props.isAssos){
        if(this.props.coldFilter){
          this.props.searchAvailableOfferCold()
        } else {
          this.props.searchAvailableOffer();
        }
      }else{
        this.props.searchCreatedOffer();
      }
    }
  }
  render() {
    const { offerListPrint } = this.props;
    return (
      <div className="offerPrintBody">
        {
          
          offerListPrint && offerListPrint.length === 0 ? (
            <div className="alert alert-warning">
              <Translate contentKey="gifuApp.offer.home.notFound">No Offers found</Translate>
            </div>
          ):("")
        }
        {
          (this.props.isCompagny)?(
            <OfferCardAdd openCreate={this.props.openCreate}/>
          ):("")
        }   
        {
          offerListPrint && offerListPrint.length > 0 ? (
            offerListPrint.map((off,index) => {
              return <OfferCard key={index} offer={off} 
                handleClick={()=>{this.props.selectOffer(off)}} />;
            })
          ):("")
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
  searchAvailableOfferCold,
  searchCreatedOffer
};

type StatePropsS = ReturnType<typeof mapStateToPropsS>;
type DispatchPropsS = typeof mapDispatchToPropsS;

export default connect(
  mapStateToPropsS,
  mapDispatchToPropsS
)(OfferPrint);
