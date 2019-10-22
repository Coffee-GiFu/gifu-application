import { Moment } from 'moment';
import { IRecuperator } from 'app/shared/model/recuperator.model';
import { IOrganisation } from './organisation.model';
import { ILocation } from './location.model';

export interface IOffer {
  id?: number;
  description?: string;
  isCold?: boolean;
  availabilityBegin?: Moment;
  availabilityEnd?: Moment;
  title?: string;
  locationDTO?: ILocation;
  recuperatorDTOs?: IRecuperator[];
  enterprise?: IOrganisation;
}

export const defaultValue: Readonly<IOffer> = {
  isCold: false
};
