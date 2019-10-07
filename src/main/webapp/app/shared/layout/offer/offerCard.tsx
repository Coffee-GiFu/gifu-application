
import React from 'react'

import { OfferCarousel } from "./offerCarousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import '../../../../node_modules/@mdi/font/css/materialdesignicons.min.css'
import { StyledOfferCard } from './offerCardStyle'
import { IOffer } from '../../model/offer.model'

export interface OfferCardProps {
    offer: IOffer
    handleClick: (id: number) => void
}
export const OfferCard = ({ offer, handleClick }: OfferCardProps) => {
  const className = `mdi mdi`
  offer.isCold;
  offer.title;
  offer.description;
  const pictures = [];
  return (    
    <StyledOfferCard
      onClick={() => { handleClick(offer.id) }}
    >
    <OfferCarousel pictures={pictures}/>
        
    </StyledOfferCard>
  )
}
