import React from 'react';
import './checkbox.scss';

export interface ICheckboxProps {
    name: string;
    defaultValue: boolean;
}
  
export const Checkbox = (props: ICheckboxProps) => {
    return(
        <div className={"fakebox fakebox-" + props.name}>
            <input type="checkbox" id={props.name} name={props.name} defaultChecked={props.defaultValue}/>
            <label htmlFor={props.name}/>
        </div>
    );
}
export default Checkbox;