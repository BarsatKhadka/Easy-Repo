import { Login } from "./components/Login"
import {Home} from "./components/Home"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import {useState} from "react"
import ProtectedRoutes from "./ProtectedRoutes/ProtectedRoutes"


function App() {

  const[authenticated , setAuthenticated] = useState<boolean>(false)
  const[userDetails, setUserDetails] = useState<any>("")
  
  return (
    <>
    <Router>
      <Routes>
      <Route path = "/" element = {<Home/>}/>
      <Route path = "/login" element = {<Login/>} />

    <Route element = {<ProtectedRoutes/>}>
    <Route path = "/dashboard" element = {<DashBoard/>}/>
    </Route>


    </Routes>
    </Router>
   
  
    
    </>
  )
}

export default App
