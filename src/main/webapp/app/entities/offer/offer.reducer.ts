import axios from 'axios';
import { ICrudDeleteAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { FAILURE, REQUEST, SUCCESS } from 'app/shared/reducers/action-type.util';

import { defaultValue, IOffer } from 'app/shared/model/offer.model';

export const ACTION_TYPES = {
  FETCH_OFFER_LIST: 'offer/FETCH_OFFER_LIST',
  FETCH_OFFER: 'offer/FETCH_OFFER',
  CREATE_OFFER: 'offer/CREATE_OFFER',
  UPDATE_OFFER: 'offer/UPDATE_OFFER',
  DELETE_OFFER: 'offer/DELETE_OFFER',
  RESET: 'offer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOffer>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type OfferState = Readonly<typeof initialState>;

// Reducer

export default (state: OfferState = initialState, action): OfferState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OFFER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OFFER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_OFFER):
    case REQUEST(ACTION_TYPES.UPDATE_OFFER):
    case REQUEST(ACTION_TYPES.DELETE_OFFER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_OFFER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OFFER):
    case FAILURE(ACTION_TYPES.CREATE_OFFER):
    case FAILURE(ACTION_TYPES.UPDATE_OFFER):
    case FAILURE(ACTION_TYPES.DELETE_OFFER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_OFFER):
    case SUCCESS(ACTION_TYPES.UPDATE_OFFER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_OFFER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/offers';

// Actions

export const getEntities: ICrudGetAllAction<IOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFER_LIST,
  payload: axios.get<IOffer>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IOffer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OFFER,
    payload: axios.get<IOffer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOffer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OFFER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOffer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OFFER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOffer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OFFER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const searchChosenOffer: ICrudGetAllAction<IOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFER_LIST,
  payload: axios.get<IOffer>(`${apiUrl}/selected`)
});

export const searchCreatedOffer: ICrudGetAllAction<IOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFER_LIST,
  payload: axios.get<IOffer>(`${apiUrl}/create`)
});

export const searchAvailableOfferCold: ICrudGetAllAction<IOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFER_LIST,
  payload: axios.get<IOffer>(`${apiUrl}/available/true`)
});
export const searchAvailableOffer: ICrudGetAllAction<IOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFER_LIST,
  payload: axios.get<IOffer>(`${apiUrl}/available/false`)
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
