import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './organisation.reducer';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrganisationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Organisation extends React.Component<IOrganisationProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { organisationList, match } = this.props;
    return (
      <div>
        <h2 id="organisation-heading">
          <Translate contentKey="gifuApp.organisation.home.title">Organisations</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gifuApp.organisation.home.createLabel">Create a new Organisation</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {organisationList && organisationList.length > 0 ? (
            <Table responsive aria-describedby="organisation-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.name">Name</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.phoneNumber">Phone Number</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.contactMail">Contact Mail</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.logo">Logo</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.description">Description</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.identificationCode">Identification Code</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.type">Type</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.organisation.location">Location</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {organisationList.map((organisation, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${organisation.id}`} color="link" size="sm">
                        {organisation.id}
                      </Button>
                    </td>
                    <td>{organisation.name}</td>
                    <td>{organisation.phoneNumber}</td>
                    <td>{organisation.contactMail}</td>
                    <td>{organisation.logo}</td>
                    <td>{organisation.description}</td>
                    <td>{organisation.identificationCode}</td>
                    <td>{organisation.type}</td>
                    <td>
                      {organisation.locationCity ? <Link to={`location/${organisation.locationId}`}>{organisation.locationCity}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${organisation.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${organisation.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${organisation.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="gifuApp.organisation.home.notFound">No Organisations found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ organisation }: IRootState) => ({
  organisationList: organisation.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Organisation);
