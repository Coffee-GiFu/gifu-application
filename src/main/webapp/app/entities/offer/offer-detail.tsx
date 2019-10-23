import React from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './offer.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface IOfferDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OfferDetail extends React.Component<IOfferDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { offerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="gifuApp.offer.detail.title">Offer</Translate> [<b>{offerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="description">
                <Translate contentKey="gifuApp.offer.description">Description</Translate>
              </span>
            </dt>
            <dd>{offerEntity.description}</dd>
            <dt>
              <span id="isCold">
                <Translate contentKey="gifuApp.offer.isCold">Is Cold</Translate>
              </span>
            </dt>
            <dd>{offerEntity.isCold ? 'true' : 'false'}</dd>
            <dt>
              <span id="availabilityBegin">
                <Translate contentKey="gifuApp.offer.availabilityBegin">Availability Begin</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={offerEntity.availabilityBegin} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="availabilityEnd">
                <Translate contentKey="gifuApp.offer.availabilityEnd">Availability End</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={offerEntity.availabilityEnd} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="title">
                <Translate contentKey="gifuApp.offer.title">Title</Translate>
              </span>
            </dt>
            <dd>{offerEntity.title}</dd>
            <dt>
              <Translate contentKey="gifuApp.offer.location">Location</Translate>
            </dt>
            <dd>{offerEntity.locationDTO && offerEntity.locationDTO.postalCode? offerEntity.locationDTO.postalCode : ''}</dd>
            <dt>
              <Translate contentKey="gifuApp.offer.recuperators">Recuperators</Translate>
            </dt>
            <dd>
              {offerEntity.recuperatorDTOs
                ? offerEntity.recuperatorDTOs.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.name}</a>
                      {i === offerEntity.recuperatorDTOs.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="gifuApp.offer.organisation">Organisation</Translate>
            </dt>
            <dd>{offerEntity.enterprise.name ? offerEntity.enterprise.name : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/offer" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/offer/${offerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
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
