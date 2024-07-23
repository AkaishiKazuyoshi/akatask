import {describe, expect, it, vi, vitest} from 'vitest';
import {fireEvent, render, screen} from '@testing-library/react'
import App from "./App.tsx";
import axios from "axios";
import {userEvent} from '@testing-library/user-event'

describe('App.tsx', () => {
  it('render loginPage', () => {
    render(<App/>)

    expect(screen.getByText('ID'))
    expect(screen.getByPlaceholderText('sample@mail.com'))
    expect(screen.getByText('Password'))
    expect(screen.getByPlaceholderText('password'))
    expect(screen.getByRole('button', {
      name: 'Login'
    }))
  });

  // https://junhyunny.github.io/react/jest/how-to-test-form-submit-event-in-react/
  it('given email, password wrote when click login then request login via axios', async () => {
    const form = loginForm();
    form.onsubmit = vitest.fn();
    render(<App/>)

    await userEvent.type(screen.getByPlaceholderText('sample@mail.com'), 'akaishi@mail.com')
    await userEvent.type(screen.getByPlaceholderText('password'), '1234567')
    fireEvent.submit(screen.getByRole("button", {name: "Login"}))

    //make test
  })
})

const loginForm = () =>
  screen.getByRole("form", {
    name: "form",
  });

const nameInput = () => screen.getByPlaceholderText("username");

const passwordInput = () => screen.getByPlaceholderText("password");

const loginButton = () =>
  screen.getByRole("button", {
    name: "login",
  });