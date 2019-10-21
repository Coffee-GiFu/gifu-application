export interface IOrganisation {
  id?: number;
  name?: string;
  phoneNumber?: string;
  contactMail?: string;
  logo?: string;
  description?: string;
  identificationCode?: string;
  type?: string;
  locationCity?: string;
  locationId?: number;
}

export const defaultValue: Readonly<IOrganisation> = {};
