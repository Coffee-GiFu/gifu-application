import React from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from '../../../entities/offer/offer.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface IOfferDetailProps extends IOfferDetailPropsCustom, StateProps, DispatchProps {}
export interface IOfferDetailPropsCustom {
  id: number;
}
export class OfferDetail extends React.Component<IOfferDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.id);
  }

  render() {
    const { offerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <dl className="jh-entity-details">
            <dt>
              <span id="description">
                <Translate contentKey="gifuApp.offer.description">Description</Translate>:
              </span>
            </dt><br/>
            <dd>{offerEntity.description}</dd><br/>
            <dt>
              <span id="isCold">
                <Translate contentKey="gifuApp.offer.isCold">Is Cold</Translate>:
              </span>
            </dt>
            <dd>{offerEntity.isCold ? 'Oui' : 'Non'}</dd><br/>
            <dt>
              <span id="availabilityBegin">
                <Translate contentKey="gifuApp.offer.availabilityBegin">Availability Begin</Translate>:
              </span>
            </dt>
            <dd>
              <TextFormat value={offerEntity.availabilityBegin} type="date" format={APP_DATE_FORMAT} />
            </dd><br/>
            <dt>
              <span id="availabilityEnd">
                <Translate contentKey="gifuApp.offer.availabilityEnd">Availability End</Translate>:
              </span>
            </dt>
            <dd>
              <TextFormat value={offerEntity.availabilityEnd} type="date" format={APP_DATE_FORMAT} />
            </dd><br/>
            <dt>
              <span id="title">
                <Translate contentKey="gifuApp.offer.title">Title</Translate>:
              </span>
            </dt>
            <dd>{offerEntity.title}</dd><br/>
            <dt>
              <Translate contentKey="gifuApp.offer.location">Location</Translate>:
            </dt>
            <dd>
              {offerEntity.locationDTO && offerEntity.locationDTO.city? offerEntity.locationDTO.city : ''}
              -
              {offerEntity.locationDTO && offerEntity.locationDTO.postalCode? offerEntity.locationDTO.postalCode : ''}
            </dd><br/>
            <dt>
              <Translate contentKey="gifuApp.offer.organisation">Organisation</Translate>:
            </dt>
            <dd>
            {offerEntity.enterprise ? offerEntity.enterprise.name : ''} 
            {' '}({offerEntity.enterprise ? offerEntity.enterprise.phoneNumber : ''})
            </dd>
          </dl>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ offer }: IRootState) => ({
  offerEntity: offer.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OfferDetail);
