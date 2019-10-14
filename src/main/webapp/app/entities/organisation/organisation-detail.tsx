import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './organisation.reducer';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganisationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OrganisationDetail extends React.Component<IOrganisationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { organisationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="gifuApp.organisation.detail.title">Organisation</Translate> [<b>{organisationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="gifuApp.organisation.name">Name</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.name}</dd>
            <dt>
              <span id="phoneNumber">
                <Translate contentKey="gifuApp.organisation.phoneNumber">Phone Number</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.phoneNumber}</dd>
            <dt>
              <span id="contactMail">
                <Translate contentKey="gifuApp.organisation.contactMail">Contact Mail</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.contactMail}</dd>
            <dt>
              <span id="logo">
                <Translate contentKey="gifuApp.organisation.logo">Logo</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.logo}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="gifuApp.organisation.description">Description</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.description}</dd>
            <dt>
              <span id="identificationCode">
                <Translate contentKey="gifuApp.organisation.identificationCode">Identification Code</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.identificationCode}</dd>
            <dt>
              <span id="type">
                <Translate contentKey="gifuApp.organisation.type">Type</Translate>
              </span>
            </dt>
            <dd>{organisationEntity.type}</dd>
            <dt>
              <Translate contentKey="gifuApp.organisation.location">Location</Translate>
            </dt>
            <dd>{organisationEntity.locationCity ? organisationEntity.locationCity : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/organisation" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/organisation/${organisationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ organisation }: IRootState) => ({
  organisationEntity: organisation.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrganisationDetail);
