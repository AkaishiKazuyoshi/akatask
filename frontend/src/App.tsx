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
          // Content-Type: application/json
          // axios.post('/api/login', {
          //   id: mailTextRef.current?.value,
          //   password: passwordTextRef.current?.value
          // })
          axios.post('http://localhost:8080/api/login', { // this is not effected from proxy
            id: mailTextRef.current?.value,
            password: passwordTextRef.current?.value
          })
        }
      }>Login</button>
    </>
  )
}

export default App
