import { ILocation } from 'app/shared/model/location.model';

export interface IRecuperator {
  id?: number;
  name?: string;
  phoneNumber?: string;
  location?: ILocation;
}

export const defaultValue: Readonly<IRecuperator> = {};
