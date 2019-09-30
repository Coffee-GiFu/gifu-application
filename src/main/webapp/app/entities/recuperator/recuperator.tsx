import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './recuperator.reducer';
import { IRecuperator } from 'app/shared/model/recuperator.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRecuperatorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Recuperator extends React.Component<IRecuperatorProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { recuperatorList, match } = this.props;
    return (
      <div>
        <h2 id="recuperator-heading">
          <Translate contentKey="gifuApp.recuperator.home.title">Recuperators</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gifuApp.recuperator.home.createLabel">Create a new Recuperator</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {recuperatorList && recuperatorList.length > 0 ? (
            <Table responsive aria-describedby="recuperator-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.recuperator.name">Name</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.recuperator.phoneNumber">Phone Number</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.recuperator.location">Location</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {recuperatorList.map((recuperator, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${recuperator.id}`} color="link" size="sm">
                        {recuperator.id}
                      </Button>
                    </td>
                    <td>{recuperator.name}</td>
                    <td>{recuperator.phoneNumber}</td>
                    <td>
                      {recuperator.location ? <Link to={`location/${recuperator.location.id}`}>{recuperator.location.city}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${recuperator.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${recuperator.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${recuperator.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="gifuApp.recuperator.home.notFound">No Recuperators found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ recuperator }: IRootState) => ({
  recuperatorList: recuperator.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Recuperator);
