import styled from 'styled-components'

export const StyledOfferCardWrapper  = styled.section`
  display: flex;
  flex-wrap: wrap;
  margin: 2rem 0 0 2rem;
`
export const StyledOfferCard  = styled.button`
  border-color: ${props => (props.selected
    ? props.theme.colors.yellow
    : props.theme.colors.white)};
  color: ${props => props.theme.colors.white};

  background: transparent;
  border-style: solid;
  border-width: 4px;
  cursor: pointer;
  display: inline-block;
  height: 8rem;
  font-size: 4rem;
  margin: 0 2rem 2rem 0;
  outline: none;
  transition: border-color ease 150ms;
  width: 8rem;
  .mdi {
    transition: opacity ease 150ms;
  }
  &:hover .mdi {
    opacity: 0.5;
  }
`
