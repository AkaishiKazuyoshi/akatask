import { describe, expect, it, vi } from "vitest";
import MainPage from "./MainPage.tsx";
import { render, screen, waitFor } from "@testing-library/react";
import axios from "axios";

const mockNavigate = vi.fn();
vi.mock("react-router-dom", () => ({
  ...vi.importActual("react-router-dom"),
  useNavigate: () => mockNavigate,
}));

const makeMockTodoRepository = () => {
  return {
    getTodos: vi.fn<[], Promise<Todo[]>>(),
  };
};

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

  it("show todo list when axios todo list was responsed", async () => {
    vi.spyOn(axios, "get").mockResolvedValue(undefined);
    const mockRepository = makeMockTodoRepository();
    mockRepository.getTodos.mockResolvedValue([
      { id: 1, title: "title1" },
      { id: 2, title: "title2" },
    ]);

    render(<MainPage repository={mockRepository} />);

    expect(mockRepository.getTodos).toHaveBeenCalled();
    expect(await screen.findByText("title1")).toBeInTheDocument();
    expect(screen.getByText("title2")).toBeInTheDocument();
  });
});
