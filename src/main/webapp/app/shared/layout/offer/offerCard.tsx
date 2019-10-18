import React from 'react';
import { OfferCarousel } from "./offerCarousel";
import './offerCardStyle.scss'
import { IOffer } from '../../model/offer.model';

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
        <img src="http://lorempixel.com/output/cats-q-c-640-480-1.jpg" />
        <div>
          <h5 title="Cat Corp." >Cat Corp.</h5>
          <h6 title="1 rue du bout du monde" >1 rue du bout du monde</h6>
          <h6 title="Alfortville - 94140" >Alfortville - 94140</h6>
        </div>
      </div>
      <div className="styledOfferCardBody">
        <h6>30/10/2017 17h35</h6>
        <h6>31/10/2017 17h35</h6>
        <p>{offer.description}</p>
      </div>
      {
        offer.isCold && (<img className="styledOfferCardIsCold" src="../../../../content/images/snowflake.png" />)
      }
    </div>
  )
}
export default OfferCard;