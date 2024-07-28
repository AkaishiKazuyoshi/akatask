import {describe, expect, it, vi, vitest} from "vitest";
import MainPage from "./MainPage.tsx";
import {render, screen} from "@testing-library/react";
import axios from "axios";

// mocking react-router-dom modules
const mockNavigate = vi.fn()
vi.mock('react-router-dom', () => ({
  ...vi.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}))

describe('MainPage.tsx', () => {
  it('render MainPage', ()=> {
    render(<MainPage/>)


  })

  it('user can see MainPage when user is authorized', () => {
    const spyAxiosGet = vi.spyOn(axios, 'get').mockResolvedValue(undefined)
    render(
      <MainPage/>
    )

    expect(spyAxiosGet).toHaveBeenCalledWith('/api/auth/me')
    expect(screen.getByText('main page')).toBeInTheDocument()
  })

  it('redirect to login page when user is not authorized', () => {
    const spyAxiosGet = vi.spyOn(axios, 'get').mockResolvedValue(undefined)
    render(<MainPage/>)

    expect(spyAxiosGet).toHaveBeenCalledWith('/api/auth/me')
    expect(screen.getByTestId(("LoginPage"))).toBeInTheDocument()
  })
})