import './App.css'
import axios from "axios";

function App() {

  return (
    <>
      <div>ID</div>
      <input placeholder={'sample@mail.com'} />
      <div>Password</div>
      <input placeholder={'password'} />
      <button onClick={
        () => {
          axios.post('http://localhost:8080/api/login', {
            id: 'akaishi@mail.com',
            password: '1234567'
          })
        }
      }>Login</button>
    </>
  )
}

export default App
