import React, { useState, useEffect } from 'react';
import { Translate, translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { Row, Col, Alert, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import { IRootState } from 'app/shared/reducers';
import { handleRegister, reset } from './register.reducer';
import { render } from 'react-dom';

export interface IRegisterModalProps {
  showModal: boolean;
  handleClose: Function;
  isEntreprise:boolean;
}

export const RegisterModal = (props: IRegisterModalProps) => {
  const [password, setPassword] = useState('');

  const handleValidSubmit = (event, values) => {
    event.preventDefault();
  };
  const updatePassword = event => setPassword(event.target.value);
  const handleClose = ()=>props.handleClose(false);

  const handleSubmit = (event, errors, { username, rememberMe }) => {
    const handleLogin = (a,b,c) => { };
    handleLogin(username, password, rememberMe);
  };
    return (
      <Modal isOpen={props.showModal} toggle={handleClose} backdrop="static" id="login-page" autoFocus={false}>
        <AvForm onSubmit={handleSubmit}>
          <ModalHeader id="login-title" toggle={handleClose}>            
            {translate('register.title')}
            {props.isEntreprise ? translate('register.type.entreprise') : translate('register.type.association')}
          </ModalHeader>
          <ModalBody>
            <Row className="justify-content-center">
                <Col md="8">
                <AvForm id="register-form" onValidSubmit={handleValidSubmit}>
                    <AvField
                    name="username"
                    label={translate('global.form.username.label')}
                    placeholder={translate('global.form.username.placeholder')}
                    validate={{
                        required: { value: true, errorMessage: translate('register.messages.validate.login.required') },
                        pattern: { value: '^[_.@A-Za-z0-9-]*$', errorMessage: translate('register.messages.validate.login.pattern') },
                        minLength: { value: 1, errorMessage: translate('register.messages.validate.login.minlength') },
                        maxLength: { value: 50, errorMessage: translate('register.messages.validate.login.maxlength') }
                    }}
                    />
                    <AvField
                    name="email"
                    label={translate('global.form.email.label')}
                    placeholder={translate('global.form.email.placeholder')}
                    type="email"
                    validate={{
                        required: { value: true, errorMessage: translate('global.messages.validate.email.required') },
                        minLength: { value: 5, errorMessage: translate('global.messages.validate.email.minlength') },
                        maxLength: { value: 254, errorMessage: translate('global.messages.validate.email.maxlength') }
                    }}
                    />
                    <AvField
                    name="firstPassword"
                    label={translate('global.form.newpassword.label')}
                    placeholder={translate('global.form.newpassword.placeholder')}
                    type="password"
                    onChange={updatePassword}
                    validate={{
                        required: { value: true, errorMessage: translate('global.messages.validate.newpassword.required') },
                        minLength: { value: 4, errorMessage: translate('global.messages.validate.newpassword.minlength') },
                        maxLength: { value: 50, errorMessage: translate('global.messages.validate.newpassword.maxlength') }
                    }}
                    />
                    <PasswordStrengthBar password={password} />
                    <AvField
                    name="secondPassword"
                    label={translate('global.form.confirmpassword.label')}
                    placeholder={translate('global.form.confirmpassword.placeholder')}
                    type="password"
                    validate={{
                        required: { value: true, errorMessage: translate('global.messages.validate.confirmpassword.required') },
                        minLength: { value: 4, errorMessage: translate('global.messages.validate.confirmpassword.minlength') },
                        maxLength: { value: 50, errorMessage: translate('global.messages.validate.confirmpassword.maxlength') },
                        match: { value: 'firstPassword', errorMessage: translate('global.messages.error.dontmatch') }
                    }}
                    />
                    <Button id="register-submit" color="primary" type="submit">
                    <Translate contentKey="register.form.button">Register</Translate>
                    </Button>
                </AvForm>
                <p>&nbsp;</p>
                <Alert color="warning">
                    <span>
                    <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>
                    </span>
                    <a className="alert-link">
                    <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
                    </a>
                    <span>
                    <Translate contentKey="global.messages.info.authenticated.suffix">
                        , you can try the default accounts:
                        <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                        <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
                    </Translate>
                    </span>
                </Alert>
                </Col>
            </Row>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={handleClose} tabIndex="1">
              <Translate contentKey="entity.action.cancel">Cancel</Translate>
            </Button>{' '}
            <Button color="primary" type="submit">
              <Translate contentKey="login.form.button">Sign in</Translate>
            </Button>
          </ModalFooter>
        </AvForm>
      </Modal>
    );
};

const mapStateToProps = ({ locale }: IRootState) => ({
  currentLocale: locale.currentLocale
});

const mapDispatchToProps = { handleRegister, reset };
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default RegisterModal;
