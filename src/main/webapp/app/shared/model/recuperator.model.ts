import { IOrganisation } from './organisation.model';

export interface IRecuperator {
  id?: number;
  name?: string;
  phoneNumber?: string;
  associationName?: IOrganisation;
  associationId?: number;
}

export const defaultValue: Readonly<IRecuperator> = {};
