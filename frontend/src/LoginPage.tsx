import { useRef } from "react";

const LoginPage = () => {
  const mailTextRef = useRef<HTMLInputElement>(null);
  const passwordTextRef = useRef<HTMLInputElement>(null);

  return (
    <form
      action="http://localhost:8080/api/login"
      method="post"
      aria-label="login-form"
    >
      <div>ID</div>
      <input name="id" placeholder={"sample@mail.com"} ref={mailTextRef} />
      <div>Password</div>
      <input name="password" placeholder={"password"} ref={passwordTextRef} />
      <button>Login</button>
    </form>
  );
};

export default LoginPage;
