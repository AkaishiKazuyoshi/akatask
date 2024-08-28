import { describe, expect, it, vitest } from "vitest";
import { fireEvent, render, screen } from "@testing-library/react";
import { userEvent } from "@testing-library/user-event";
import LoginPage from "./LoginPage.tsx";

describe("App.tsx", () => {
  it("render loginPage", () => {
    render(<LoginPage />);

    expect(screen.getByText("ID"));
    expect(screen.getByPlaceholderText("sample@mail.com"));
    expect(screen.getByText("Password"));
    expect(screen.getByPlaceholderText("password"));
    expect(
      screen.getByRole("button", {
        name: "Login",
      }),
    );
  });

  it("given email, password wrote when click login then request login via axios", async () => {
    render(<LoginPage />);
    const form = loginForm();
    form.onsubmit = vitest.fn();

    await userEvent.type(
      screen.getByPlaceholderText("sample@mail.com"),
      "akaishi@mail.com",
    );
    await userEvent.type(screen.getByPlaceholderText("password"), "1234567");
    fireEvent.submit(screen.getByRole("button", { name: "Login" }));

    expect(form).toHaveAttribute("method", "post");
    expect(form).toHaveAttribute("action", "http://localhost:8080/api/login");
    expect(form).toHaveFormValues({
      id: "akaishi@mail.com",
      password: "1234567",
    });
    expect(form.onsubmit).toHaveBeenCalled();
  });
});

const loginForm = () =>
  screen.getByRole("form", {
    name: "login-form",
  });
