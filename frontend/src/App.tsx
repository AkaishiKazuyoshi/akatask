import './App.css'
import axios from "axios";
import {useRef} from "react";

function App() {
  const mailTextRef = useRef<HTMLInputElement>(null)
  const passwordTextRef = useRef<HTMLInputElement>(null)

  return (
    <>
      <div>ID</div>
      <input placeholder={'sample@mail.com'} ref={mailTextRef}/>
      <div>Password</div>
      <input placeholder={'password'} ref={passwordTextRef}/>
      <button onClick={
        () => {
          // application/json request
          axios.post('http://localhost:8080/api/login', {
            id: mailTextRef.current?.value,
            password: passwordTextRef.current?.value
          })
        }
      }>Login</button>
    </>
  )
}

export default App
