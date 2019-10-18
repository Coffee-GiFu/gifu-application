export interface IRecuperator {
  id?: number;
  name?: string;
  phoneNumber?: string;
  associationName?: string;
  associationId?: number;
}

export const defaultValue: Readonly<IRecuperator> = {};
