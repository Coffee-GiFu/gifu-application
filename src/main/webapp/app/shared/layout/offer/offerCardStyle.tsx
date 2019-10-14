import styled from 'styled-components'

export const StyledOfferCardBody  = styled.div`
  position: relative;
  top: -6.7rem;
  padding: 0 0.5rem;
  width: 15.5rem;
  h6{
    font-size: 0.6rem;
    line-height: 0.8rem;
    margin-bottom: 0.1rem;
    text-align: center;
    width: 50%;
    display: inline-block;
    border-bottom: solid 0.02rem;
  }
  p{
    margin: 0;
    font-size: 0.8rem;
    line-height: 0.9rem;
    max-height: 8rem;
    top: -1rem;
    position: relative;
    height: 100%;
    overflow: hidden;
    text-overflow: ellipsis
    display: block;
    display: -webkit-box;
    max-width: 100%;
    -webkit-line-clamp: 9;
    -webkit-box-orient: vertical;
  }
`
export const StyledOfferCard  = styled.div`
  border-color: rgb(0,0,0);
  color: rgb(0,0,0);
  border-radius: 1rem;
  background: transparent;
  border-style: solid;
  border-width: 4px;
  cursor: pointer;
  display: inline-grid;
  height: 21rem;
  font-size: 4rem;
  margin: 0 2rem 2rem 0;
  outline: none;
  width: 16rem;
  .carousel{
    height: 11.7rem;
    border-radius: 0.6rem 0.6rem 0 0;
    border-bottom: solid 0.1rem;
  }
  .slider-wrapper{
    height: 11.7rem;
  }
`
export const StyledOfferCardIsCold  = styled.img`
  width: 2rem;
  position: relative;
  top: -11.8rem;
  right: -0.2rem;
  filter: drop-shadow(1px 1px 0 black) drop-shadow(0px 1px 0 black);
`
export const StyledOfferCardOrganisation  = styled.div`
  border-radius: 1rem;
  background-color: #ffffff5e;
  font-size: 0;
  position: relative;
  width: 14.9rem;
  top: -11.3rem;
  right: -0.3rem;
  h5, h6{
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
  h5{
    font-size: 1rem;
    line-height: 1.5rem;
    margin-bottom: 0;
  }
  h6{
    font-size: 0.6rem;
    line-height: 0.8rem;
    margin-bottom: 0.1rem;
    width: 6rem;
    display: inline-block;
  }
  div{
    padding-left: 0.5rem
    font-size: x-small;
    width: 12.9rem;
    margin-left: 1.9rem;
    display: inline-block;
  }
  img {
    border-radius: 100%;
    width: 2rem !important;
    height: 2rem !important;
    display: inline-block;
    position: absolute;
    top: 0.3rem;
    left: 0.2rem;
  }
`
