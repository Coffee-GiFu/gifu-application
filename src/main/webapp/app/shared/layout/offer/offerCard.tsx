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
          <h3 title="Cat Corp." >Cat Corp.</h3>
          <h5 title="Alfortville - 94140" >Alfortville - 94140</h5>
        </div>
      </div>
      <div className="styledOfferCardBody">
        <p>{offer.description}</p>
        <div className="dateGroup">
          <span>30/10/2017 17h35</span>
          <span>31/10/2017 17h35</span>
        </div>
      </div>
      {
        offer.isCold && (<img className="styledOfferCardIsCold" src="../../../../content/images/snowflake.png" />)
      }
    </div>
  )
}
export default OfferCard;