import React from 'react';
import { OfferCarousel } from "./offerCarousel";
import './offerCardStyle.scss'
import { IOffer } from '../../model/offer.model';
import moment from 'moment';

export interface OfferCardProps {
    offer: IOffer
    handleClick: (id: number) => void
}
export const OfferCard = ({ offer, handleClick }: OfferCardProps) => {
  const pictures = [];
  const autoPlay = true;
  
  return (    
    <div className="styledOfferCard"
      onClick={() => { handleClick(offer.id) }}
    >
    <OfferCarousel pictures={pictures} autoPlay={autoPlay}/>
      <div className="styledOfferCardOrganisation">
        <img src="../../../content/images/default.png" />
        <div>
          <h3 title={offer.enterprise.name} >{offer.enterprise.name}</h3>
          <h5 title={offer.locationDTO.city+" - "+offer.locationDTO.postalCode} >{offer.locationDTO.city} - {offer.locationDTO.postalCode}</h5>
        </div>
      </div>
      <div className="styledOfferCardBody">
        <p>{offer.description}</p>
        <div className="dateGroup">
          <span>{moment(offer.availabilityBegin).format("DD/MM/YYYY HH:mm")}</span>
          <span>{moment(offer.availabilityEnd).format("DD/MM/YYYY HH:mm")}</span>
        </div>
      </div>
      {
        offer.isCold && (<img className="styledOfferCardIsCold" src="../../../../content/images/snowflake.png" />)
      }
    </div>
  )
}
export default OfferCard;