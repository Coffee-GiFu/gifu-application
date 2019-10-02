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
import { getEntity, updateEntity, createEntity, reset } from './recuperator.reducer';
import { IRecuperator } from 'app/shared/model/recuperator.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRecuperatorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IRecuperatorUpdateState {
  isNew: boolean;
  location: ILocation;
}

export class RecuperatorUpdate extends React.Component<IRecuperatorUpdateProps, IRecuperatorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      location: this.props.recuperatorEntity.location,
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

  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { recuperatorEntity } = this.props;
      const entity = {
        ...recuperatorEntity,
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
    this.props.history.push('/entity/recuperator');
  };

  render() {
    const { recuperatorEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="gifuApp.recuperator.home.createOrEditLabel">
              <Translate contentKey="gifuApp.recuperator.home.createOrEditLabel">Create or edit a Recuperator</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : recuperatorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="recuperator-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="recuperator-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="recuperator-name">
                    <Translate contentKey="gifuApp.recuperator.name">Name</Translate>
                  </Label>
                  <AvField
                    id="recuperator-name"
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
                  <Label id="phoneNumberLabel" for="recuperator-phoneNumber">
                    <Translate contentKey="gifuApp.recuperator.phoneNumber">Phone Number</Translate>
                  </Label>
                  <AvField
                    id="recuperator-phoneNumber"
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
                  <Label for="recuperator-location">
                    <Translate contentKey="gifuApp.recuperator.location">Location</Translate>
                  </Label>
                  {/*<AvInput id="recuperator-location" type="select" className="form-control" name="locationId">*/}
                  {/*  <option value="" key="0" />*/}
                  {/*  {locations*/}
                  {/*    ? locations.map(otherEntity => (*/}
                  {/*        <option value={otherEntity.id} key={otherEntity.id}>*/}
                  {/*          {otherEntity.city}*/}
                  {/*        </option>*/}
                  {/*      ))*/}
                  {/*    : null}*/}
                  {/*</AvInput>*/}
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/recuperator" replace color="info">
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
  recuperatorEntity: storeState.recuperator.entity,
  loading: storeState.recuperator.loading,
  updating: storeState.recuperator.updating,
  updateSuccess: storeState.recuperator.updateSuccess
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
)(RecuperatorUpdate);
