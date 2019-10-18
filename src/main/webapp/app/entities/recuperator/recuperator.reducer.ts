import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRecuperator, defaultValue } from 'app/shared/model/recuperator.model';

export const ACTION_TYPES = {
  FETCH_RECUPERATOR_LIST: 'recuperator/FETCH_RECUPERATOR_LIST',
  FETCH_RECUPERATOR: 'recuperator/FETCH_RECUPERATOR',
  CREATE_RECUPERATOR: 'recuperator/CREATE_RECUPERATOR',
  UPDATE_RECUPERATOR: 'recuperator/UPDATE_RECUPERATOR',
  DELETE_RECUPERATOR: 'recuperator/DELETE_RECUPERATOR',
  RESET: 'recuperator/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRecuperator>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type RecuperatorState = Readonly<typeof initialState>;

// Reducer

export default (state: RecuperatorState = initialState, action): RecuperatorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RECUPERATOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RECUPERATOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RECUPERATOR):
    case REQUEST(ACTION_TYPES.UPDATE_RECUPERATOR):
    case REQUEST(ACTION_TYPES.DELETE_RECUPERATOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RECUPERATOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RECUPERATOR):
    case FAILURE(ACTION_TYPES.CREATE_RECUPERATOR):
    case FAILURE(ACTION_TYPES.UPDATE_RECUPERATOR):
    case FAILURE(ACTION_TYPES.DELETE_RECUPERATOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RECUPERATOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RECUPERATOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RECUPERATOR):
    case SUCCESS(ACTION_TYPES.UPDATE_RECUPERATOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RECUPERATOR):
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

const apiUrl = 'api/recuperators';

// Actions

export const getEntities: ICrudGetAllAction<IRecuperator> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RECUPERATOR_LIST,
  payload: axios.get<IRecuperator>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IRecuperator> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RECUPERATOR,
    payload: axios.get<IRecuperator>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRecuperator> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RECUPERATOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRecuperator> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RECUPERATOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRecuperator> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RECUPERATOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
