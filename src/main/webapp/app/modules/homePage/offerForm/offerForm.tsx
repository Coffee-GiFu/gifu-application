import React, { useState, useEffect } from 'react';
import './offerForm.scss';
import { Translate, translate } from 'react-jhipster';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { Row, Col, Alert, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { IOffer } from 'app/shared/model/offer.model';
import OfferUpdate from './offer-update';
import OfferDetail from './offer-detail';
export interface IOfferFormModalProps {
  showModal: boolean;
  handleClose: Function;
  offer:IOffer;
  isNew:boolean;
  consultation:boolean;
}

export const OfferFormModal = (props: IOfferFormModalProps) => {
    const handleClose = ()=>props.handleClose(false);
    const offerId = (props.offer?props.offer.id:null);
    const consultation = props.consultation;
    return (
      <Modal isOpen={props.showModal} toggle={handleClose} 
        backdrop="static" autoFocus={false}>
          <ModalHeader toggle={handleClose}>
                {
                  consultation?(
                    <Translate contentKey="gifuApp.offer.home.vueLabel">Consult an Offer</Translate>
                  ):(
                    props.isNew?
                      <Translate contentKey="gifuApp.offer.home.createLabel">Create a new Offer</Translate>
                    :
                      <Translate contentKey="gifuApp.offer.home.editLabel">Edit an Offer</Translate>
                  )
                }
          </ModalHeader>
          <ModalBody>
            {
              consultation?(
                <OfferDetail id ={offerId}/>
              ):(
                <OfferUpdate id ={offerId} handleClose={handleClose} isNew={props.isNew}/>
              )
            }
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={handleClose} tabIndex="1" className="button-back">
              <Translate contentKey="entity.action.back">Cancel</Translate>
            </Button>
          </ModalFooter>
      </Modal>
    );
};

const mapStateToProps = ({ locale }: IRootState) => ({
  currentLocale: locale.currentLocale
});

export default OfferFormModal;
