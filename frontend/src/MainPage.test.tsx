import { describe, expect, it, vi } from "vitest";
import MainPage from "./MainPage.tsx";
import { render, screen, waitFor } from "@testing-library/react";
import axios from "axios";

const mockNavigate = vi.fn();
vi.mock("react-router-dom", () => ({
  ...vi.importActual("react-router-dom"),
  useNavigate: () => mockNavigate,
}));

describe("MainPage.tsx", () => {
  it("user can see MainPage when user is authorized", () => {
    const spyAxiosGet = vi.spyOn(axios, "get").mockResolvedValue(undefined);
    render(<MainPage />);

    expect(spyAxiosGet).toHaveBeenCalledWith("/api/auth/me");
    expect(screen.getByText("main page")).toBeInTheDocument();
  });

  it("redirect to login page when user is not authorized", async () => {
    const spyAxiosGet = vi.spyOn(axios, "get").mockRejectedValue(undefined);
    render(<MainPage />);

    expect(spyAxiosGet).toHaveBeenCalledWith("/api/auth/me");
    await waitFor(() => {
      expect(mockNavigate).toHaveBeenCalledWith("/");
    });
  });
});
