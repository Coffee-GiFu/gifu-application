import { Moment } from 'moment';
import { ILocation } from 'app/shared/model/location.model';
import { IRecuperator } from 'app/shared/model/recuperator.model';

export interface IOffer {
  id?: number;
  description?: string;
  isCold?: boolean;
  availabilityBegin?: Moment;
  availabilityEnd?: Moment;
  title?: string;
  location?: ILocation;
  recuperator?: IRecuperator;
}

export const defaultValue: Readonly<IOffer> = {
  isCold: false
};
