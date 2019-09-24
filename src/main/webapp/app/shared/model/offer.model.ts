import { Moment } from 'moment';

export interface IOffer {
  id?: number;
  description?: string;
  isCold?: boolean;
  availabilityBegin?: Moment;
  availabilityEnd?: Moment;
  title?: string;
  locationId?: number;
  recuperatorId?: number;
}

export const defaultValue: Readonly<IOffer> = {
  isCold: false
};
