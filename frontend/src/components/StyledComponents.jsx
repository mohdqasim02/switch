import styled from "styled-components";

export const PageContainer = styled.div`
    padding: 1.5rem;
    ${(props) => props.blurred && "filter: blur(0.5px);"}
`;

export const ToggleBox = styled.li`
    padding: 1rem;
    border: 1px solid #ddd;
    border-radius: 0.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

export const Switch = styled.div`
    position: relative;
    width: 60px;
    height: 28px;
    background: #b3b3b3;
    border-radius: 32px;
    padding: 4px;
    transition: 300ms all;

    &:before {
        transition: 300ms all;
        content: "";
        position: absolute;
        width: 28px;
        height: 28px;
        border-radius: 35px;
        top: 50%;
        left: 4px;
        border-color: black;
        border-width: 1px;
        background: white;
        transform: translate(0, -50%);
    }
`;

export const Input = styled.input`
  display: none;

  &:checked + ${Switch} {
    background: dodgerblue;

    &:before {
      transform: translate(32px, -50%);
    }
  }
`;

export const ModalOverlay = styled.div`
    position: fixed;
    inset: 0;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 10;
`;

export const ModalBox = styled.div`
    background-color: white;
    padding: 2rem;
    border-radius: 0.75rem;
    width: 100%;
    max-width: 500px;
    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.2);
`;

export const CommentBox = styled.textarea`
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ccc;
    border-radius: 0.5rem;
    resize: none;
    margin-top: 0.5rem;
`;

export const Button = styled.button`
    padding: 0.5rem 1.25rem;
    border-radius: 0.5rem;
    font-weight: 500;
    cursor: pointer;
    ${(props) =>
            props.variant === "confirm"
                    ? "background-color: #2563eb; color: white;"
                    : "background-color: #e5e7eb; color: black;"}
`;

export const CreateButton = styled.button`
  background-color: #2563eb;
  color: white;
  padding: 0.6rem 1.2rem;
  font-weight: 500;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: background 0.2s ease-in;

  &:hover {
    background-color: #1d4ed8;
  }
`;
