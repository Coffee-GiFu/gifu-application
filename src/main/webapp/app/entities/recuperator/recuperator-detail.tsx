import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './recuperator.reducer';
import { IRecuperator } from 'app/shared/model/recuperator.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRecuperatorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class RecuperatorDetail extends React.Component<IRecuperatorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { recuperatorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="gifuApp.recuperator.detail.title">Recuperator</Translate> [<b>{recuperatorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="gifuApp.recuperator.name">Name</Translate>
              </span>
            </dt>
            <dd>{recuperatorEntity.name}</dd>
            <dt>
              <span id="phoneNumber">
                <Translate contentKey="gifuApp.recuperator.phoneNumber">Phone Number</Translate>
              </span>
            </dt>
            <dd>{recuperatorEntity.phoneNumber}</dd>
          </dl>
          <Button tag={Link} to="/entity/recuperator" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/recuperator/${recuperatorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ recuperator }: IRootState) => ({
  recuperatorEntity: recuperator.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RecuperatorDetail);
