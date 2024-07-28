import {useEffect} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const MainPage = () => {
    const navigate = useNavigate()

    useEffect(() => {
      axios.get('/api/auth/me')
        .catch(() => {
          navigate('/')
          }
        )
    }, []);

  return <div>main page</div>
}

  export default MainPage