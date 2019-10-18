import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/location">
      <Translate contentKey="global.menu.entities.location" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/recuperator">
      <Translate contentKey="global.menu.entities.recuperator" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/offer">
      <Translate contentKey="global.menu.entities.offer" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/organisation">
      <Translate contentKey="global.menu.entities.organisation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/recuperator">
      <Translate contentKey="global.menu.entities.recuperator" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
