import React from 'react';
import { Translate, translate } from 'react-jhipster';
import { Button, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Link } from 'react-router-dom';
import Checkbox from 'app/shared/layout/checkbox/checkbox';

export interface ILoginFormProps {
  loginError: boolean;
  handleLogin: Function;
}

class LoginForm extends React.Component<ILoginFormProps> {
  handleSubmit = (event, errors, { username, password, rememberMe }) => {
    const { handleLogin } = this.props;
    handleLogin(username, password, rememberMe);
  };

  render() {
    const { loginError } = this.props;

    return (
      <AvForm onSubmit={this.handleSubmit} className="card">
            <Row>
              <Col md="12">
                {loginError ? (
                  <Alert color="danger">
                    <Translate contentKey="login.messages.error.authentication">
                      <strong>Failed to sign in!</strong> Please check your credentials and try again.
                    </Translate>
                  </Alert>
                ) : null}
              </Col>
              <Col md="12">
                <AvField
                  name="username"
                  label={translate('global.form.username.label')}
                  placeholder={translate('global.form.username.placeholder')}
                  required
                  className="input"
                  errorMessage="Username cannot be empty!"
                  autoFocus
                />
                <AvField
                  name="password"
                  type="password"
                  label={translate('login.form.password')}
                  placeholder={translate('login.form.password.placeholder')}
                  required
                  className="input"
                  errorMessage="Password cannot be empty!"
                />
                <Link className="forgotPassword" to="/account/reset/request">
                  <Translate contentKey="login.password.forgot">Did you forget your password?</Translate>
                </Link>
              </Col>
            </Row>
            <button type="submit" className="button">
              <Translate contentKey="login.form.button">Sign in</Translate>
            </button>
        </AvForm>
    );
  }
}

export default LoginForm;
