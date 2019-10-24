import React from 'react';
import './checkbox.scss';

export interface ICheckboxProps {
    name: string;
    defaultValue: boolean;
    action?: Function;
}
  
export const Checkbox = (props: ICheckboxProps) => {
    const action = props.action;

    const  maj = (event) => {
        if(action){
            action(event.target.checked);
        }
    }
    return(
        <div className={"fakebox fakebox-" + props.name}>
            <input type="checkbox" onChange={maj} id={props.name} name={props.name} defaultChecked={props.defaultValue}/>
            <label htmlFor={props.name}/>
        </div>
    );
}
export default Checkbox;