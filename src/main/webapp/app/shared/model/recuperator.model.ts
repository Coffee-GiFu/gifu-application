export interface IRecuperator {
  id?: number;
  name?: string;
  phoneNumber?: string;
  locationCity?: string;
  locationId?: number;
  associationName?: string;
  associationId?: number;
}

export const defaultValue: Readonly<IRecuperator> = {};
