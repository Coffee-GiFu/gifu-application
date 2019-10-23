import React from 'react';
import { Translate, translate } from 'react-jhipster';
import { Col, Label, Row } from 'reactstrap';
import { AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';

export interface LocationPageProps {
    isNew: boolean;
}

export const LocationPage = (isNew: boolean) => {
    return (
        <React.Fragment>
            <Row className="justify-content-center">
                <Col>
                    <h2 id="gifuApp.location.home.createOrEditLabel">
                        <Translate contentKey="gifuApp.location.home.createOrEditLabel">Create or edit a Location</Translate> Offre
                  </h2>
                </Col>
            </Row>
            <Row className="justify-content-center">
                <Col>
                    {!isNew ? (
                        <AvGroup>
                            <Label for="location-id">
                                <Translate contentKey="global.field.id">ID</Translate>
                            </Label>
                            <AvInput id="location-id" type="text" className="form-control" name="locationDTO.id" required readOnly />
                        </AvGroup>
                    ) : null}
                    <AvGroup>
                        <Label id="streetAddressLabel" for="location-streetAddress">
                            <Translate contentKey="gifuApp.location.streetAddress">Street Address</Translate>
                        </Label>
                        <AvField
                            id="location-streetAddress"
                            type="text"
                            name="locationDTO.streetAddress"
                            validate={{
                                required: { value: true, errorMessage: translate('entity.validation.required') },
                                minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                                maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                            }}
                        />
                    </AvGroup>
                    <AvGroup>
                        <Label id="postalCodeLabel" for="location-postalCode">
                            <Translate contentKey="gifuApp.location.postalCode">Postal Code</Translate>
                        </Label>
                        <AvField
                            id="location-postalCode"
                            type="text"
                            name="locationDTO.postalCode"
                            validate={{
                                required: { value: true, errorMessage: translate('entity.validation.required') },
                                minLength: { value: 5, errorMessage: translate('entity.validation.minlength', { min: 5 }) },
                                maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                            }}
                        />
                    </AvGroup>
                    <AvGroup>
                        <Label id="cityLabel" for="location-city">
                            <Translate contentKey="gifuApp.location.city">City</Translate>
                        </Label>
                        <AvField
                            id="location-city"
                            type="text"
                            name="locationDTO.city"
                            validate={{
                                required: { value: true, errorMessage: translate('entity.validation.required') },
                                minLength: { value: 2, errorMessage: translate('entity.validation.minlength', { min: 2 }) },
                                maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                            }}
                        />
                    </AvGroup>
                </Col>
            </Row>
        </React.Fragment>
    );
}

export default LocationPage;