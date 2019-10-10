import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { getEntity, updateEntity, createEntity, reset } from './organisation.reducer';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrganisationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOrganisationUpdateState {
  isNew: boolean;
  locationId: string;
}

export class OrganisationUpdate extends React.Component<IOrganisationUpdateProps, IOrganisationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      locationId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { organisationEntity } = this.props;
      const entity = {
        ...organisationEntity,
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
    this.props.history.push('/entity/organisation');
  };

  render() {
    const { organisationEntity, locations, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="gifuApp.organisation.home.createOrEditLabel">
              <Translate contentKey="gifuApp.organisation.home.createOrEditLabel">Create or edit a Organisation</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : organisationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="organisation-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="organisation-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="organisation-name">
                    <Translate contentKey="gifuApp.organisation.name">Name</Translate>
                  </Label>
                  <AvField
                    id="organisation-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                      maxLength: { value: 140, errorMessage: translate('entity.validation.maxlength', { max: 140 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumberLabel" for="organisation-phoneNumber">
                    <Translate contentKey="gifuApp.organisation.phoneNumber">Phone Number</Translate>
                  </Label>
                  <AvField
                    id="organisation-phoneNumber"
                    type="text"
                    name="phoneNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      minLength: { value: 10, errorMessage: translate('entity.validation.minlength', { min: 10 }) },
                      maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactMailLabel" for="organisation-contactMail">
                    <Translate contentKey="gifuApp.organisation.contactMail">Contact Mail</Translate>
                  </Label>
                  <AvField
                    id="organisation-contactMail"
                    type="text"
                    name="contactMail"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      minLength: { value: 5, errorMessage: translate('entity.validation.minlength', { min: 5 }) },
                      maxLength: { value: 140, errorMessage: translate('entity.validation.maxlength', { max: 140 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="logoLabel" for="organisation-logo">
                    <Translate contentKey="gifuApp.organisation.logo">Logo</Translate>
                  </Label>
                  <AvField id="organisation-logo" type="text" name="logo" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="organisation-description">
                    <Translate contentKey="gifuApp.organisation.description">Description</Translate>
                  </Label>
                  <AvField
                    id="organisation-description"
                    type="text"
                    name="description"
                    validate={{
                      minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="identificationCodeLabel" for="organisation-identificationCode">
                    <Translate contentKey="gifuApp.organisation.identificationCode">Identification Code</Translate>
                  </Label>
                  <AvField
                    id="organisation-identificationCode"
                    type="text"
                    name="identificationCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      minLength: { value: 10, errorMessage: translate('entity.validation.minlength', { min: 10 }) },
                      maxLength: { value: 14, errorMessage: translate('entity.validation.maxlength', { max: 14 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLabel" for="organisation-type">
                    <Translate contentKey="gifuApp.organisation.type">Type</Translate>
                  </Label>
                  <AvField
                    id="organisation-type"
                    type="text"
                    name="type"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="organisation-location">
                    <Translate contentKey="gifuApp.organisation.location">Location</Translate>
                  </Label>
                  <AvInput id="organisation-location" type="select" className="form-control" name="locationId">
                    <option value="" key="0" />
                    {locations
                      ? locations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.city}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/organisation" replace color="info">
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
  organisationEntity: storeState.organisation.entity,
  loading: storeState.organisation.loading,
  updating: storeState.organisation.updating,
  updateSuccess: storeState.organisation.updateSuccess
});

const mapDispatchToProps = {
  getLocations,
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
)(OrganisationUpdate);
