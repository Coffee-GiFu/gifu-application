import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Label, Row } from 'reactstrap';
import { AvFeedback, AvField, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { getEntities as getRecuperators } from 'app/entities/recuperator/recuperator.reducer';
import { getEntities as getOrganisations } from 'app/entities/organisation/organisation.reducer';
import { createEntity, getEntity, reset, updateEntity } from './offer.reducer';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { LocationPage } from '../../modules/locationPage/locationPage';

export interface IOfferUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> { }

export interface IOfferUpdateState {
  isNew: boolean;
  idsrecuperators: any[];
  locationId: string;
  organisationId: string;
}


export class OfferUpdate extends React.Component<IOfferUpdateProps, IOfferUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsrecuperators: [],
      locationId: '0',
      organisationId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getLocations();
    this.props.getRecuperators();
    this.props.getOrganisations();
  }

  saveEntity = (event, errors, values) => {
    window.console.log(this.props);
    window.console.log(event);
    values.availabilityBegin = convertDateTimeToServer(values.availabilityBegin);
    values.availabilityEnd = convertDateTimeToServer(values.availabilityEnd);
    window.console.log(this.props.getOrganisations());
    window.console.log(this.props.getRecuperators());
    if (errors.length === 0) {
      window.console.log(this.props.getOrganisations());
      window.console.log(this.props.getRecuperators());
      const { offerEntity } = this.props;
      window.console.log("----");
      window.console.log(offerEntity);
      const entity = {
        ...offerEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/offer');
  };

  render() {
    
    window.console.log("slslslslsslsls");
    window.console.log(this.props);
    const { offerEntity, locations, recuperators, organisations, loading, updating } = this.props;
    const { isNew } = this.state;
    window.console.log("wwwwwlsls");
    window.console.log(recuperators);
    window.console.log("wwwwwlsls");
    window.console.log(recuperators);
    return (
      <div>
        <Row className="justify-content-center">
          <Col>
            {loading ? (
              <p>Loading...</p>
            ) : (
                <AvForm model={isNew ? {} : offerEntity} onSubmit={this.saveEntity}>
                  <Row>
                    <Col>
                      <Row className="justify-content-center">
                        <Col>
                          <h2 id="gifuApp.offer.home.createOrEditLabel">
                            <Translate contentKey="gifuApp.offer.home.createOrEditLabel">Create or edit a Offer</Translate>
                          </h2>
                        </Col>
                      </Row>
                      {!isNew ? (
                        <AvGroup>
                          <Label for="offer-id">
                            <Translate contentKey="global.field.id">ID</Translate>
                          </Label>
                          <AvInput id="offer-id" type="text" className="form-control" name="id" required readOnly />
                        </AvGroup>
                      ) : null}
                      <AvGroup>
                        <Label id="descriptionLabel" for="offer-description">
                          <Translate contentKey="gifuApp.offer.description">Description</Translate>
                        </Label>
                        <AvField
                          id="offer-description"
                          type="text"
                          name="description"
                          validate={{
                            required: { value: true, errorMessage: translate('entity.validation.required') },
                            minLength: { value: 10, errorMessage: translate('entity.validation.minlength', { min: 10 }) },
                            maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                          }}
                        />
                      </AvGroup>
                      <AvGroup>
                        <Label id="isColdLabel" check>
                          <AvInput id="offer-isCold" type="checkbox" className="form-control" name="isCold" />
                          <Translate contentKey="gifuApp.offer.isCold">Is Cold</Translate>
                        </Label>
                      </AvGroup>
                      <AvGroup>
                        <Label id="availabilityBeginLabel" for="offer-availabilityBegin">
                          <Translate contentKey="gifuApp.offer.availabilityBegin">Availability Begin</Translate>
                        </Label>
                        <AvInput
                          id="offer-availabilityBegin"
                          type="datetime-local"
                          className="form-control"
                          name="availabilityBegin"
                          placeholder={'YYYY-MM-DD HH:mm'}
                          value={isNew ? null : convertDateTimeFromServer(this.props.offerEntity.availabilityBegin)}
                          validate={{
                            required: { value: true, errorMessage: translate('entity.validation.required') }
                          }}
                        />
                      </AvGroup>
                      <AvGroup>
                        <Label id="availabilityEndLabel" for="offer-availabilityEnd">
                          <Translate contentKey="gifuApp.offer.availabilityEnd">Availability End</Translate>
                        </Label>
                        <AvInput
                          id="offer-availabilityEnd"
                          type="datetime-local"
                          className="form-control"
                          name="availabilityEnd"
                          placeholder={'YYYY-MM-DD HH:mm'}
                          value={isNew ? null : convertDateTimeFromServer(this.props.offerEntity.availabilityEnd)}
                          validate={{
                            required: { value: true, errorMessage: translate('entity.validation.required') }
                          }}
                        />
                      </AvGroup>
                      <AvGroup>
                        <Label id="titleLabel" for="offer-title">
                          <Translate contentKey="gifuApp.offer.title">Title</Translate>
                        </Label>
                        <AvField
                          id="offer-title"
                          type="text"
                          name="title"
                          validate={{
                            required: { value: true, errorMessage: translate('entity.validation.required') },
                            minLength: { value: 10, errorMessage: translate('entity.validation.minlength', { min: 10 }) },
                            maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                          }}
                        />
                      </AvGroup>
                    </Col>
                    <Col>
                    {LocationPage(isNew)}
                    </Col>
                  </Row>
                  <Button tag={Link} id="cancel-save" to="/entity/offer" replace color="info">
                    <FontAwesomeIcon icon="arrow-left" />
                    &nbsp;
                  <span className="d-none d-md-inline">
                      <Translate contentKey="entity.action.back">Back</Translate>
                    </span>
                  </Button>
                  &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                    <FontAwesomeIcon icon="save" />
                    &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                  </Button>
                </AvForm>
              )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  locations: storeState.location.entities,
  recuperators: storeState.recuperator.entities,
  organisations: storeState.organisation.entities,
  offerEntity: storeState.offer.entity,
  loading: storeState.offer.loading,
  updating: storeState.offer.updating,
  updateSuccess: storeState.offer.updateSuccess
});

const mapDispatchToProps = {
  getLocations,
  getRecuperators,
  getOrganisations,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OfferUpdate);
