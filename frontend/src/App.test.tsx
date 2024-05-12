import { expect, it, describe, vi } from 'vitest';
import {render, screen} from '@testing-library/react'
import App from "./App.tsx";
import axios from "axios";
import { userEvent } from '@testing-library/user-event'

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

  it('given email, password wrote when click login then request login via axios', async () => {
    const spyAxiosPost = vi.spyOn(axios, 'post').mockResolvedValue(null)
    render(<App/>)

    await userEvent.type(screen.getByPlaceholderText('sample@mail.com'), 'akaishi@mail.com')
    await userEvent.type(screen.getByPlaceholderText('password'), '1234567')
    await userEvent.click(screen.getByRole('button', { name: 'Login' }))

    expect(spyAxiosPost).toHaveBeenCalledWith('http://localhost:8080/api/login', {
      id: 'akaishi@mail.com',
      password: '1234567'
    })
  })
})