export interface ILocation {
  id?: number;
  streetAddress?: string;
  postalCode?: string;
  city?: string;
}

export const defaultValue: Readonly<ILocation> = {};
