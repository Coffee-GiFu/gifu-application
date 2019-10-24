import React from 'react';
import './offerCardStyle.scss'

export interface OfferCardAddProps {
  openCreate: Function
}
export const OfferCardAdd = ({ openCreate }: OfferCardAddProps) => {
  const pictures = [];
  const autoPlay = true;
  const fnc=()=>{openCreate();}
  return (    
    <div className="styledOfferCardAdd" onClick={fnc} >
        <div className="circle">
            <span className="plus">+</span>
        </div>
    </div>
  )
}
export default OfferCardAdd;