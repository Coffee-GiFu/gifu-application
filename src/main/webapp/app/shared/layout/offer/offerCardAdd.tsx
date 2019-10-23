import React from 'react';
import './offerCardStyle.scss'

export interface OfferCardAddProps {
    handleClick: Function
}
export const OfferCardAdd = ({ handleClick }: OfferCardAddProps) => {
  const pictures = [];
  const autoPlay = true;
  return (    
    <div className="styledOfferCardAdd" onClick={handleClick()} >
        <div className="circle">
            <span className="plus">+</span>
        </div>
    </div>
  )
}
export default OfferCardAdd;