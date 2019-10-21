import { Moment } from 'moment';
import { IRecuperator } from 'app/shared/model/recuperator.model';

export interface IOffer {
  id?: number;
  description?: string;
  isCold?: boolean;
  availabilityBegin?: Moment;
  availabilityEnd?: Moment;
  title?: string;
  selectedRecuperator?: number;
  locationId?: number;
  recuperators?: IRecuperator[];
  organisationName?: string;
  organisationId?: number;
}

export const defaultValue: Readonly<IOffer> = {
  isCold: false
};
