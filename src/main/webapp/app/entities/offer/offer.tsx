import React from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './offer.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface IOfferProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Offer extends React.Component<IOfferProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { offerList, match } = this.props;
    return (
      <div>
        <h2 id="offer-heading">
          <Translate contentKey="gifuApp.offer.home.title">Offers</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gifuApp.offer.home.createLabel">Create a new Offer</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {offerList && offerList.length > 0 ? (
            <Table responsive aria-describedby="offer-heading">
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.description">Description</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.isCold">Is Cold</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.availabilityBegin">Availability Begin</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.availabilityEnd">Availability End</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.title">Title</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.selectedRecuperator">Selected Recuperator</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.location">Location</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.recuperators">Recuperators</Translate>
                  </th>
                  <th>
                    <Translate contentKey="gifuApp.offer.organisation">Organisation</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {offerList.map((offer, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${offer.id}`} color="link" size="sm">
                        {offer.id}
                      </Button>
                    </td>
                    <td>{offer.description}</td>
                    <td>{offer.isCold ? 'true' : 'false'}</td>
                    <td>
                      <TextFormat type="date" value={offer.availabilityBegin} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={offer.availabilityEnd} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{offer.title}</td>
                    <td>{offer.selectedRecuperator}</td>
                    <td>{offer.locationId ? <Link to={`location/${offer.locationId}`}>{offer.locationId}</Link> : ''}</td>
                    <td>
                      {offer.recuperators
                        ? offer.recuperators.map((val, j) => (
                            <span key={j}>
                              <Link to={`recuperator/${val.id}`}>{val.name}</Link>
                              {j === offer.recuperators.length - 1 ? '' : ', '}
                            </span>
                          ))
                        : null}
                    </td>
                    <td>
                      {offer.organisationName ? <Link to={`organisation/${offer.organisationId}`}>{offer.organisationName}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${offer.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${offer.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${offer.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="gifuApp.offer.home.notFound">No Offers found</Translate>
            </div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ offer }: IRootState) => ({
  offerList: offer.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Offer);
