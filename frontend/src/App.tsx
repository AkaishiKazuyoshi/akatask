import './App.css'
import LoginPage from "./LoginPage.tsx";
import {Route, Routes} from "react-router-dom";
import MainPage from "./MainPage.tsx";

function App() {
  return (
    <Routes>
      <Route path={'/'} element={<LoginPage/>}/>
      <Route path={'/main'} element={<MainPage/>}/>
    </Routes>)
}

export default App
