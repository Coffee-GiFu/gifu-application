import React, { useState, useEffect } from 'react';
import './offerForm.scss';
import { Translate, translate } from 'react-jhipster';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { Row, Col, Alert, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { IOffer } from 'app/shared/model/offer.model';
import OfferUpdate from './offer-update';
export interface IOfferFormModalProps {
  showModal: boolean;
  handleClose: Function;
  offer:IOffer;
  isNew:boolean;
}

export const OfferFormModal = (props: IOfferFormModalProps) => {
    const handleClose = ()=>props.handleClose(false);
    const offerId = (props.offer?props.offer.id:null);
    return (
      <Modal isOpen={props.showModal} toggle={handleClose} 
        backdrop="static" autoFocus={false}>
          <ModalHeader toggle={handleClose}>
                {
                    props.isNew?
                        <Translate contentKey="gifuApp.offer.home.createLabel">Create a new Offer</Translate>
                    :
                        <Translate contentKey="gifuApp.offer.home.editLabel">Edit an Offer</Translate>
                }
          </ModalHeader>
          <ModalBody>
              <OfferUpdate id ={offerId} handleClose={handleClose} isNew={props.isNew}/>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={handleClose} tabIndex="1">
              <Translate contentKey="entity.action.cancel">Cancel</Translate>
            </Button>
          </ModalFooter>
      </Modal>
    );
};

const mapStateToProps = ({ locale }: IRootState) => ({
  currentLocale: locale.currentLocale
});

export default OfferFormModal;
