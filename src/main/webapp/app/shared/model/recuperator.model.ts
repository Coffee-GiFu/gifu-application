export interface IRecuperator {
  id?: number;
  name?: string;
  phoneNumber?: string;
  locationCity?: string;
  locationId?: number;
}

export const defaultValue: Readonly<IRecuperator> = {};
