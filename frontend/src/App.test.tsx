import { describe, expect, it, vitest } from "vitest";
import { render, screen } from "@testing-library/react";
import App from "./App.tsx";
import { MemoryRouter } from "react-router-dom";

vitest.mock("./LoginPage", () => ({
  default: () => <div data-testid="LoginPage" />,
}));
vitest.mock("./MainPage", () => ({
  default: () => <div data-testid="MainPage" />,
}));

describe("App.tsx", () => {
  it("can see login page at first time", () => {
    render(
      <MemoryRouter initialEntries={["/"]}>
        <App />
      </MemoryRouter>,
    );

    expect(screen.getByTestId("LoginPage")).toBeInTheDocument();
  });

  it("can see main page when /main address access", () => {
    render(
      <MemoryRouter initialEntries={["/main"]}>
        <App />
      </MemoryRouter>,
    );

    expect(screen.getByTestId("MainPage")).toBeInTheDocument();
  });
});
