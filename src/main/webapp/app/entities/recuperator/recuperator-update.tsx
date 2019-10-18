import React from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getLocations} from 'app/entities/location/location.reducer';
import {getEntities as getOrganisations} from 'app/entities/organisation/organisation.reducer';
import {createEntity, getEntity, reset, getEntities, updateEntity} from './recuperator.reducer';

export interface IRecuperatorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IRecuperatorUpdateState {
  isNew: boolean;
  locationId: string;
  associationId: string;
}

export class RecuperatorUpdate extends React.Component<IRecuperatorUpdateProps, IRecuperatorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      locationId: '0',
      associationId: '0',
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
    this.props.getOrganisations();
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
    const { recuperatorEntity, locations, organisations, loading, updating } = this.props;
    const { isNew } = this.state;
    window.console.log(locations);
    window.console.log(organisations);
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
  locations : storeState.location.entities,
  organisations: storeState.organisation.entities,
  recuperatorEntity: storeState.recuperator.entity,
  loading: storeState.recuperator.loading,
  updating: storeState.recuperator.updating,
  updateSuccess: storeState.recuperator.updateSuccess
});

const mapDispatchToProps = {
  getLocations,
  getOrganisations,
  getEntity,
  getEntities,
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
